module SearchTreeTesting where

import SearchTree

checkSuccessor :: Ord a => a -> [a] -> Bool
checkSuccessor y xs = successor' y xs == successor y t
  where t = foldr insTree Nil xs

checkClosest :: Int -> [Int] -> Bool
checkClosest _ [] = error "list empty"
checkClosest y xs = closest' y xs == closest y t
  where t = foldr insTree Nil xs

successor' :: Ord a => a -> [a] -> Maybe a

successor' elem xs = foldr (update_successor elem) Nothing xs

-- successor' and update_successor are based on the _iter method
--
-- elem is the value for which we want to find the successor (that is the smallest element larger than elem)
-- y is our current best candidate
-- x is a new value that we have to examine to see if it is better than y 
update_successor :: Ord a => a -> a -> Maybe a -> Maybe a
update_successor elem x Nothing =
  if elem < x then (Just x) else Nothing
update_successor elem x (Just y) =
  if elem < x && x < y then (Just x) else (Just y)

closest' :: Int -> [Int] -> Int
closest' elem xs = foldr1 (update_closest elem) xs

-- closest'  and update_closest are also based on the _iter method
--
-- elem is the value for which we want to find the closest value in the list
-- y is our current best candidate
-- x is a new value that we have to examine to see if it is better than y
update_closest :: Int -> Int -> Int -> Int

update_closest elem x y 
    | abs (elem - x) < abs (elem - y) = x
    | abs (elem - x) > abs (elem - y) = y
    | otherwise = min x y  -- In the case of a tie, favor the smaller entry
        
