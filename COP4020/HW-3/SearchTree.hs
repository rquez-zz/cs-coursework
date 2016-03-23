-- Based on Haskell - the craft of functional programming
-- section 16.7 Search tree
-- This implements a binary search tree with a minimal API
--
-- Check out http://algs4.cs.princeton.edu/32bst/ for an
-- implementation in Java with a much more extensive API

module SearchTree
 (Tree(..),   -- Nil | Node
  nil,        -- Tree a
  isNil,      -- Tree a -> Bool
  isNode,     -- Tree a -> Bool
  leftSub,    -- Tree a -> Tree a
  rightSub,   -- Tree a -> Tree a
  treeVal,    -- Tree a -> a
  insTree,    -- Ord a => a -> Tree a -> Tree a
  delete,     -- Ord a => a -> Tree a -> Tree a
  minTree,    -- Ord a => Tree a -> Maybe a
  successor,  -- Ord a => a -> Tree a -> Maybe a
  closest,    -- Int -> Tree Int -> Int
 ) where

import Data.Maybe

data Tree a = Nil | Node a (Tree a) (Tree a)
  deriving (Eq) -- not deriving show because I wrote my own show function

nil :: Tree a
nil = Nil

insTree :: Ord a => a -> Tree a -> Tree a

insTree val Nil = (Node val Nil Nil)
insTree val (Node v t1 t2)
  | v == val  = Node v t1 t2
  | (val > v) = Node v t1 (insTree val t2)
  | (val < v) = Node v (insTree val t1) t2

delete :: Ord a => a -> Tree a -> Tree a

delete val Nil = Nil
delete val (Node v t1 t2)
  | (val > v) = Node v t1 (delete val t2)
  | (val < v) = Node v (delete val t1) t2
  | isNil t2  = t1
  | isNil t1  = t2
  | otherwise = join t1 t2

minTree :: Ord a => Tree a -> Maybe a
minTree Nil   = Nothing
minTree (Node v t1 _)
  | isNil t1  = Just v
  | otherwise = minTree t1

join :: (Ord a) => Tree a -> Tree a -> Tree a
join t1 t2 =
  Node mini t1 newt
    where
      (Just mini) = minTree t2
      newt        = delete mini t2

isNil :: Tree a -> Bool
isNil Nil = True
isNil _   = False

isNode :: Tree a -> Bool
isNode Nil          = False
isNode (Node _ _ _) = True

leftSub :: Tree a -> Tree a
leftSub Nil           = error "leftSub"
leftSub (Node _ t1 _) = t1

rightSub :: Tree a -> Tree a
rightSub Nil           = error "rightSub"
rightSub (Node _ _ t2) = t2

treeVal :: Tree a -> a
treeVal Nil          = error "treeVal"
treeVal (Node v _ _) = v

treeVal' :: Tree a -> a
treeVal' Nil          = error "treeVal alt"
treeVal' (Node v _ _) = v

-- add your solutions here

successor :: Ord a => a -> Tree a -> Maybe a
successor _ Nil = Nothing
successor x t
    | node == Nil           = successor x (insTree x t)
    | right /= Nil          = minTree right
    | otherwise             = findBigParent x t
    where   right   = rightSub (node)
            node    = findNode x t

predecessor :: Ord a => a -> Tree a -> Maybe a
predecessor _ Nil = Nothing
predecessor x t
    | node == Nil           = predecessor x (insTree x t)
    | left /= Nil           = maxTree left
    | otherwise             = findSmallParent x t
    where   left = leftSub (node)
            node = findNode x t

maxTree :: Ord a => Tree a -> Maybe a
maxTree Nil   = Nothing
maxTree (Node v _ r)
  | isNil r   = Just v
  | otherwise = maxTree r

findNode :: Ord a => a -> Tree a -> Tree a
findNode _ Nil = Nil
findNode x (Node a left right)
    | x > a     = findNode x right
    | x < a     = findNode x left
    | x == a    = Node a left right

findBigParent :: Ord a => a -> Tree a -> Maybe a
findBigParent x t
    | p /= Nil      = if (v p) > x  then Just (v p)
                                    else (findBigParent (v p) t)
    | otherwise     = Nothing
    where p = (findParent x t)
          v = (\s -> treeVal s)

findSmallParent :: Ord a => a -> Tree a -> Maybe a
findSmallParent x t
    | p /= Nil      = if (v p) < x  then Just (v p)
                                    else (findSmallParent (v p) t)
    | otherwise     = Nothing
    where p = (findParent x t)
          v = (\s -> treeVal s)

findParent :: Ord a => a -> Tree a -> Tree a
findParent _ Nil                    = Nil
findParent x (Node a left right)
    | isChild left || isChild right = Node a left right
    | x > a                         = findParent x right
    | x < a                         = findParent x left
    | otherwise                     = Nil
    where t = (\s -> treeVal' s)
          isChild = (\s -> s /= Nil && t s == x)

closest :: Int -> Tree Int -> Int
closest x t
    | node == Nil       = x + min
    | otherwise         = x
    where node = findNode x t
          newT = insTree x t
          min = minimumDiff (getDifferences (findNode x newT) newT)

minimumDiff :: [Int] -> Int
minimumDiff [x] = x
minimumDiff (x:xs)
    | (abs x) == (abs min)  = max x min
    | (abs x) < (abs min)   = x
    | otherwise             = min
        where min = minimumDiff xs

getDifferences :: Tree Int -> Tree Int -> [Int]
getDifferences (Node a left right) t  = filter (\x -> x /= 0) dx
    where p  =  findParent a t
          v  = (\x -> treeVal x)
          s  = successor a t
          d  = predecessor a t
          vS = (if s /= Nothing  then ((fromJust s) - a) else 0)
          vD = (if d /= Nothing  then ((fromJust d) - a) else 0)
          vP = (if p /= Nil      then ((v p) - a)        else 0)
          vR = (if right /= Nil  then ((v right) - a)    else 0)
          vL = (if left /= Nil   then ((v left) - a)     else 0)
          dx = (vD:vS:vP:vR:vL:[])

-- code to display trees

pairEntriesWithDepth :: Tree a -> Int -> [(Maybe a, Int)]

pairEntriesWithDepth Nil depth                 = [(Nothing, depth)]
pairEntriesWithDepth (Node x left right) depth =
  (Just x,depth):(pairEntriesWithDepth left (depth + 1) ++ pairEntriesWithDepth right (depth + 1))

instance (Show a) => Show (Tree a) where
  show tree = init $ unlines [replicate d '.' ++ (show' n) | (n,d) <- pairEntriesWithDepth tree 0]
    where
      show' Nothing   = "nil"
      show' (Just x)  = show x
