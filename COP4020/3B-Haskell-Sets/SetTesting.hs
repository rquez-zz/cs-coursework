module SetTesting where

import System.Random
import Set
import Data.Set
import Data.List

checkDiff xs ys =
  diff (makeSet xs) (makeSet ys) == makeSet ( toList ( difference (fromList xs) (fromList ys) ) )

checkSymDiff xs ys =  
  symDiff (makeSet xs) (makeSet ys) ==
  makeSet ( toList ( Data.Set.difference ( Data.Set.union (fromList xs) (fromList ys) ) ( Data.Set.intersection (fromList xs) (fromList ys) ) ) )

main =
  do
    g <- getStdGen
    let randomList = (randomRs (0::Int, 10) g)
    let (as, randomList') = splitAt 5 randomList
    let (bs, _)           = splitAt 5 randomList'
    putStrLn $ "as = " ++ show as
    putStrLn $ "bs = " ++ show bs
    if checkDiff as bs
       then putStrLn "diff passed"
       else putStrLn "diff failed"

    if checkSymDiff as bs
       then putStrLn "symDiff passed"
       else putStrLn "symDiff failed"


