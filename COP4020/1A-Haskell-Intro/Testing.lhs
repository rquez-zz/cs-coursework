$Id: Testing.lhs,v 1.8 12 February 2016$
Created by G. Leavens
Modified J. Jakes-Schauer with the (+>) test-chaining operator.
TODO: Probably there's an easier implementation, using functors...  
  Also added 'exceptionTest' type.

> module Testing where
> import Control.Exception
  
 import Data.List (foldl')

The type constructor TestCase is a representation of tests in Haskell.
To make a TestCase, you write, for example:
     eqTest (1 + 2) "==" 3
     gTest subset s1 "`subset`" s2 
For eqTest, the first argument is the Haskell code for the test,
the second is printed as a connective (although == is used), and
the third is data that gives the expected result of the test.
For gTest, the first argument is a function, 
which is used to compare the result of the result of the second argument 
with the fourth argument (the expected result).
and the third argument is printed as a connective.
    
> data TestCase a =
>       Test (a -> a -> Bool) a String a

The following is convenient for making test cases that use equality.

> eqTest :: (Show a, Eq a) => a -> String -> a -> TestCase a
> eqTest = Test (==)

> gTest :: (Show a) => (a -> a -> Bool) -> a -> String -> a -> TestCase a
> gTest = Test

The following are for making assertions

> assertTrue :: Bool -> (TestCase Bool)
> assertTrue code = eqTest code "==" True

> assertFalse :: Bool -> (TestCase Bool)
> assertFalse code = eqTest code "==" False

For running a single test case, use the following.
The number returned is the number of test cases that failed.
For example, you can write
  run_test (eqTest (1 + 2) "==" 3)
    >>= (\i -> putStrLn ((show i) ++ " errors"))
and this will run the test.

> run_test :: (Show a) => TestCase a -> IO Integer
> run_test (Test comp code connective expected) =
>   do if result
>      then do { putStrLn (show code) }
>      else do { putStrLn (failure ++ (show code))}
>      putStr arrow
>      putStrLn (show expected)
>      return (if result then 0 else 1)
>    where failure = "FAILURE: "
>          arrow   = "      " ++ connective ++ " "
>          result = code `comp` expected

The following will run an entire list of tests.
For example, you can write
   run_tests [eqTest (1 + 2) "==" 3,
              eqTest (1 + 2) "==" 4]

> run_tests :: (Show a) => [TestCase a] -> IO ()
> run_tests ts = 
>   do errs <- run_test_list 0 ts
>      doneTesting errs

A version of run_tests with more labeling.

> dotests :: (Show a) => String -> [TestCase a] -> IO ()
> dotests name ts =
>   do startTesting name
>      run_tests ts

A way to run a list of tests

> run_test_list :: (Show a) => Integer -> [TestCase a] -> IO Integer
> run_test_list errs_so_far [] =
>   do return errs_so_far
> run_test_list errs_so_far (t:ts) =
>   do err_count <- run_test t
>      run_test_list (errs_so_far + err_count) ts

A combining form for running 2 lists of testcases (of different types)

> composeTests :: (Show a, Show b)
>                => [TestCase a] -> [TestCase b] -> Integer -> IO Integer
> composeTests tas tbs count = 
>     do ac <- run_test_list count tas
>        bc <- run_test_list ac tbs
>        return bc

Automation of testing for 2 lists of testcases (of different types)

> dotests2 :: (Show a, Show b) => String -> [TestCase a] -> [TestCase b] -> IO ()
> dotests2 name tas tbs =
>   do startTesting name
>      errs <- composeTests tas tbs 0
>      doneTesting errs

To be able to create tests interactively, need an instance of Show for TestCase

> instance (Show a) => Show (TestCase a) where
>   show (Test _ _ connective expected) = 
>             "(Test (" ++ connective ++ ") <code> \"" ++ connective ++ "\" "
>              ++ (show expected) ++ ")"

Print a newline and a message that testing is beginning.

> startTesting :: String -> IO ()
> startTesting name = 
>    do putChar '\n'
>       putStrLn ("Testing " ++ name ++ "...")

> doneTesting :: (Testable a) => a -> IO ()
> doneTesting tests =
>   do  fails <- num_failed tests
>       putStr "Finished with "
>       putStr (show fails)
>       putChar ' '
>       putStr (case fails of
>                   1 -> "failure!"
>                   _ -> "failures!")
>       putChar '\n'

 newtype TestResult = TR (IO Integer)


> class Testable a where
>   num_failed :: a -> IO Integer


(Previously I had (Show a, Testable a) in the signature here; that was wrong.  --J.)

> instance (Show a) => Testable (TestCase a) where
>   num_failed = run_test

> instance (Testable a) => Testable [a] where
>   num_failed = foldr (\ x acc -> num_failed x >>= (\ nx -> acc >>= (\ nacc -> return (nacc + nx)))) (return 0)

  

> instance (Testable a) => Testable (IO a) where
>    num_failed m = m >>= num_failed -- The second num_failed is of type (a -> IO Integer).

> infixl 2 +>
> (+>) :: (Testable a, Testable b) => a -> b -> IO Integer
> x +> y = num_failed x >>= (\ nx -> num_failed y >>= (\ny -> return (nx + ny)))
  
  
> instance Testable Integer where
>   num_failed x = return x

> data ExceptionTest a = ExceptionTest a String

> instance Testable (ExceptionTest a) where
>   num_failed (ExceptionTest ex str) = let doc = "Trying '" ++ str ++ "' to see if it throws an exception..." in
>                                        do putStrLn doc
>                                           b <- excepted ex
>                                           if b then do { putStrLn "No error, because: Error." }
>                                             else do { putStrLn "FAILURE: No error!" }
>                                           return (if b then 0 else 1)

Implementation func for exceptionTest; you shouldn't need to use this directly.  My solution seems like a bit of a hack...
  
> excepted :: a -> IO Bool
> excepted ex = do result <- try (evaluate ex)
>                  return $ case result of 
>                            Left (SomeException _) -> True
>                            Right _ -> False


Here's a fun new one: The test fails if passed an expression that does *not* raise an exception!  

> exceptionTest :: a -> String -> ExceptionTest a
> exceptionTest = ExceptionTest 

