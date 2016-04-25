-- I hate having to set it up this way, with duplicate test files, but there's no other easy solution to the problem of importing Tree.
module TreeLabelTesting where

import Test.HUnit

import qualified TreeLabelWithoutStateMonadTesting as WO (tests)
import qualified TreeLabelWithStateMonadTesting as W (tests)

main = runTestTT $ TestList [
  WO.tests,
  W.tests
  ]
