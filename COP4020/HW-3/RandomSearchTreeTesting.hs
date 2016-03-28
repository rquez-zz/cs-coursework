module RandomSearchTreeTesting where

import System.Random
import Criterion.Main
-- you have to make sure that the Haskell package Criterion
-- is installed on your system
-- use cabal to install it
-- https://www.haskell.org/cabal/
-- https://hackage.haskell.org/package/criterion-1.1.1.0/docs/Criterion-Main.html

import Data.List

import SearchTree
import SearchTreeTesting

runningtime :: IO ()
runningtime =
  do
    g <- getStdGen
    let randomList = randomRs (0::Int,2^15) g

    let ((a:as), randomList') = splitAt (2^12) randomList
    let treeA  = foldl' (flip insTree) Nil as
    
    let ((b:bs), randomList'') = splitAt (2^13) randomList'
    let treeB  = foldl' (flip insTree) Nil bs

    let ((c:cs), _) = splitAt (2^14) randomList''
    let treeC  = foldl' (flip insTree) Nil cs

    defaultMain [
                  -- successor
                  bgroup "list successor" [
                    bench "a)" $ whnf (successor' a) as
                  , bench "b)" $ whnf (successor' b) bs
                  , bench "c)" $ whnf (successor' c) cs
                  ]
                , bgroup "tree successor" [
                    bench "a)" $ whnf (successor a) treeA
                  , bench "b)" $ whnf (successor b) treeB
                  , bench "c)" $ whnf (successor c) treeC
                  ]
                -- closest
                , bgroup "list closest" [
                    bench "a)" $ whnf (closest' a) as
                  , bench "b)" $ whnf (closest' b) bs
                  , bench "c)" $ whnf (closest' c) cs
                  ]
                , bgroup "tree closest" [
                    bench "a)" $ whnf (closest a) treeA
                  , bench "b)" $ whnf (closest b) treeB
                  , bench "c)" $ whnf (closest c) treeC
                  ]
                ]

correctness :: IO ()
correctness =
  do
    g <- getStdGen
    let randomList = randomRs (0::Int,2^15) g

    let ((a:as), randomList') = splitAt (2^12) randomList
    let treeA  = foldl' (flip insTree) Nil as
    
    let ((b:bs), randomList'') = splitAt (2^13) randomList'
    let treeB  = foldl' (flip insTree) Nil bs

    let ((c:cs), _) = splitAt (2^14) randomList''
    let treeC  = foldl' (flip insTree) Nil cs
    
    if (successor a treeA) /= (successor' a as)
       then putStrLn "a) tree successor failed"
       else putStrLn "a) tree successor passes"

    if (successor b treeB) /= (successor' b bs)
       then putStrLn "b) tree successor failed"
       else putStrLn "b) tree successor passes"

    if (successor c treeC) /= (successor' c cs)
       then putStrLn "c) tree successor failed"
       else putStrLn "c) tree successor passes"

    if (closest a treeA) /= (closest' a as)
       then putStrLn "a) tree closest failed"
       else putStrLn "a) tree closest passes"

    if (closest b treeB) /= (closest' b bs)
       then putStrLn "b) tree closest failed"
       else putStrLn "b) tree closest passes"

    if (closest c treeC) /= (closest' c cs)
       then putStrLn "c) tree closest failed"
       else putStrLn "c) tree closest passes"
    
main =
  do
    correctness
    runningtime
