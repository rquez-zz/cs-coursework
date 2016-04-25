module RegularExpressionsTesting where

import Prelude hiding ((<*>))

import Test.HUnit

import RegularExpressions

-- tests for option

test_option_0 =
  TestCase (assertEqual "option test 0" (True) ( option (char 'a') "" ) )

test_option_1 =
  TestCase (assertEqual "option test 1" (True) ( option (char 'a') "a" ) )

test_option_2 =
  TestCase (assertEqual "option test 2" (True) ( option (char 'a' <*> char 'b') "ab" ) )

test_option_3 =
  TestCase (assertEqual "option test 3" (False) ( option (char 'b') "bb" ) )

-- tests for plus

test_plus_0 =
  TestCase (assertEqual "plus test 0" (False) ( plus (char 'a') "" ) )
            
test_plus_1 =
  TestCase (assertEqual "plus test 1" (True) ( plus (char 'a' ||| char 'b') "b" ) )

test_plus_2 =
  TestCase (assertEqual "plus test 2" (True) ( plus (char 'a' ||| char 'b') "ba" ) )

test_plus_3 =
  TestCase (assertEqual "plus test 3" (True) ( plus (char 'a' <*> char 'b') "ababab" ) )
 
-- tests for number (string of digits)

test_number_0 =
  TestCase (assertEqual "number \"0123\"" (False) (number "0123"))

test_number_1 =
  TestCase (assertEqual "number \"123\"" (True) (number "123"))

-- added after Tuesday's class: a single 0 is a valid number
test_number_2 =
  TestCase (assertEqual "number \"0\"" (True) (number "0"))

-- test for fractionalNumber

test_frac_0 =
  TestCase (assertEqual "fractional \"01.23\"" (False) (fractional "01.23"))

test_frac_1 =
  TestCase (assertEqual "fractional \"1.23\"" (True) (fractional "1.23"))

test_frac_2 =
  TestCase (assertEqual "fractional \"1.230\"" (False) (fractional "1.230"))

test_frac_3 =
  TestCase (assertEqual "fractional \"1.0\"" (True) (fractional "1.0"))

test_frac_4 =
  TestCase (assertEqual "fractional \"1020.000123\"" (True) (fractional "1020.000123"))

-- added after Tuesday's class: a fractional number can start with a single 0
test_frac_5 =
  TestCase (assertEqual "fractional \"0.123\"" (True) (fractional "0.123"))

test_frac_6 =
  TestCase (assertEqual "fractional \"0.0\"" (True) (fractional "0.0"))


-- group tests

tests = TestList [
  TestLabel "option" test_option_0,
  TestLabel "option" test_option_1,
  TestLabel "option" test_option_2,
  TestLabel "option" test_option_3,
--
  TestLabel "plus" test_plus_0,
  TestLabel "plus" test_plus_1,
  TestLabel "plus" test_plus_2,
  TestLabel "plus" test_plus_3,
--
  TestLabel "number" test_number_0,
  TestLabel "number" test_number_1,
  TestLabel "number" test_number_2,
--
  TestLabel "fractional number" test_frac_0,
  TestLabel "fractional number" test_frac_1,
  TestLabel "fractional number" test_frac_2,
  TestLabel "fractional number" test_frac_3,
  TestLabel "fractional number" test_frac_4,
  TestLabel "fractional number" test_frac_5,
  TestLabel "fractional number" test_frac_6
  ]

runTests = runTestTT tests
