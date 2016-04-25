module Store where

import qualified Data.Map.Strict as Map

-- abstract data type Store a b
-- keys are of type a
-- values are of type b 

newtype Store a b = Store (Map.Map a b) deriving (Show,Eq)

emptyStore :: Ord a => Store a b
emptyStore = (Store Map.empty)

insertStore :: Ord a => a -> b -> Store a b -> Store a b
insertStore k v (Store sto) = Store (Map.insert k v sto)

lookupStore :: Ord a => a -> Store a b -> Maybe b
lookupStore k (Store sto) = Map.lookup k sto

-- create new label for Store a Int
-- keys are of type a
-- values must be of type Int !

createNewLabel :: Ord a => Store a Int -> Int
createNewLabel (Store sto) = Map.size sto 
