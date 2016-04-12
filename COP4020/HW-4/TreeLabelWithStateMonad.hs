module TreeLabelWithStateMonad where

import Store
import Control.Monad.State
import Data.Maybe
import qualified Data.Map.Strict as Map

-- label element

labelValue :: Ord a => a -> State (Store a Int) Int
labelValue k = do
    modify (putNext k)
    gets (getValue k)

putNext :: Ord a => a -> (Store a Int) -> (Store a Int)
putNext k s
    | g == -1       = insertStore k n s
    | otherwise     = s
    where   g = getValue k s
            n = nextValue s

nextValue :: (Store a Int) -> Int
nextValue (Store m)
    | Map.size m == 0   = 0
    | otherwise         = (maximum (map snd (Map.toList m))) + 1

getValue :: Ord a => a -> (Store a Int) -> Int
getValue k s = fromMaybe (-1) (lookupStore k s)

-- label tree

data Tree a = Nil | Node a (Tree a) (Tree a) deriving (Show,Eq)

labelTree :: Ord a => Tree a -> State (Store a Int) (Tree Int)
labelTree Nil = do
  return Nil
labelTree (Node val left right) = do
  labeledValue <- labelValue val
  labeledLeft  <- labelTree left
  labeledRight <- labelTree right
  return (Node labeledValue labeledLeft labeledRight)

getLabeledTree :: Ord a => Tree a -> Tree Int
getLabeledTree tree = fst $ runState (labelTree tree) (emptyStore)
