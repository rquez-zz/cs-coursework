module TreeLabelWithoutStateMonad where

import Store
import Data.Maybe
import qualified Data.Map.Strict as Map

-- label element

labelValue :: Ord a => a -> (Store a Int) -> (Int, Store a Int)
labelValue k s
        | g == Nothing  = labelValue k p
        | otherwise     = (fromJust g, s)
        where   g = get k s
                p = put k (nextValue s) s

get :: Ord a => a -> (Store a Int) -> Maybe Int
get k s = lookupStore k s

put :: Ord a => a -> Int -> (Store a Int) -> (Store a Int)
put k v s = insertStore k v s

nextValue :: (Store a Int) -> Int
nextValue (Store m)
    | Map.size m == 0   = 0
    | otherwise         = (maximum (map snd (Map.toList m))) + 1

-- label tree

data Tree a = Nil | Node a (Tree a) (Tree a) deriving (Show,Eq)

labelTree :: Ord a => Tree a -> (Store a Int) -> (Tree Int, Store a Int)
labelTree Nil ls = (Nil, ls)

labelTree (Node val left right) ls =
  (Node labeledValue labeledLeft labeledRight, ls''')
    where (labeledValue, ls')   = labelValue val   ls
          (labeledLeft,  ls'')  = labelTree  left  ls'
          (labeledRight, ls''') = labelTree  right ls''

getLabeledTree :: Ord a => Tree a -> Tree Int
getLabeledTree tree = fst $ labelTree tree emptyStore
