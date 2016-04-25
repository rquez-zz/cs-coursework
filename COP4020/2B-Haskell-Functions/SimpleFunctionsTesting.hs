module SimpleFunctionsTesting where

import Test.HUnit

import SimpleFunctions

-- tests for a) filterFirst

test_a0 =
  TestCase (assertEqual "filterFirst odd [1,2,3,4,5]" [1,3,4,5] (filterFirst odd [1,2,3,4,5]))

test_a1 =
  TestCase (assertEqual "filterFirst odd [1,3,4,5]" [1,3,5] (filterFirst odd [1,3,4,5]))

-- tests for b) filterLast

test_b0 =
  TestCase (assertEqual "filterLast even [1,2,3,4,5]" [1,2,3,4] (filterLast even [1,2,3,4,5]))

test_b1 =
  TestCase (assertEqual "filterLast even [1,2,3,4]" [1,2,4] (filterLast even [1,2,3,4]))

-- c) tests for split

test_c0 =
  TestCase (assertEqual "split ['a'..'f']" (['a','c','e'],['b','d','f']) (split ['a'..'f']))

test_c1 =
  TestCase (assertEqual "split ['a'..'g']" (['a','c','e','g'],['b','d','f']) (split ['a'..'g']))

test_c2 =
  TestCase (assertEqual "split [1.0]" ([1.0],[]) (split [1.0]))

test_c3 =
  TestCase (assertEqual "split []" (([],[])::([Int],[Int])) (split ([]::[Int])))

-- d) tests for interleave

test_d0 =
  TestCase (assertEqual "interleave ([1,3,5],[2,4])" ([1..5]) (interleave ([1,3,5],[2,4])))

test_d1 =
  TestCase (assertEqual "interleave ([],[2,4,6])" ([2,4,6]) (interleave ([],[2,4,6])))

test_d2 =
  TestCase (assertEqual "interleave ([1,2,4,6,8,9],[3,5,7,10])" ([1,3,2,5,4,7,6,10,8,9]) (interleave ([1,2,4,6,8,9],[3,5,7,10])))

-- e) tests for merge

test_e0 =
  TestCase (assertEqual "merge ([1,2,4,6,8,9],[3,5,7,10])" ([1..10]) (merge (([1,2,4,6,8,9],[3,5,7,10]))))

test_e1 =
  TestCase (assertEqual "merge ([],[3,5,7,10])" ([3,5,7,10]) (merge (([],[3,5,7,10]))))

-- f) tests for mergesort

test_f0 =
  TestCase (assertEqual "mergeSort [8,10,2,3,1,5,0]" ([0,1,2,3,5,8,10]) (mergeSort [8,10,2,3,1,5,0]))

test_f1 =
  TestCase (assertEqual "mergeSort [42]" ([42]) (mergeSort [42]))

test_f2 =
  TestCase (assertEqual "mergeSort []" ([]::[Char]) (mergeSort []::[Char]))


-- group tests

tests = TestList [
  TestLabel "a)" test_a0,
  TestLabel "a)" test_a1,
  TestLabel "b)" test_b0,
  TestLabel "b)" test_b1,
  TestLabel "c)" test_c0,
  TestLabel "c)" test_c1,
  TestLabel "c)" test_c2,
  TestLabel "c)" test_c3,
  TestLabel "d)" test_d0,
  TestLabel "d)" test_d1,
  TestLabel "d)" test_d2,
  TestLabel "e)" test_e0,
  TestLabel "e)" test_e1,
  TestLabel "f)" test_f0,
  TestLabel "f)" test_f1,
  TestLabel "f)" test_f2
  ]

runTests = runTestTT tests
