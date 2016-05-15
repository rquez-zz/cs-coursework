module Chess where
import Data.Char
import Data.List

type File               =   Char  -- 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'
type Rank               =   Int   -- 1, 2 , 3, 4, 5, 6, 7, 8
type Position           =   (Char, Int)

data Color              =   Black | White
                            deriving (Eq, Show)

data Piece              =   King | Queen | Rook | Bishop | Knight | Pawn
                            deriving (Eq, Show)

isLegalPosition                         :: Position -> Bool
isLegalPosition p                       = elem p [ (x, y) | x <- ['a'..'h'], y <- [1..8] ]

isLegalMove                             :: Color -> Piece -> Position -> Position -> Bool
isLegalMove color piece from to         = elem to (getPossibleMoves color piece from) && isLegalPosition to

getPossibleMoves                        :: Color -> Piece -> Position -> [Position]
getPossibleMoves c King (x, y)          = [ (i, j) | i <- [x, goToChar x 1, goToChar x (-1)], j <- [y, y+1, y-1], (i, j) /= (x, y)]
getPossibleMoves c Rook (x, y)          = [ (i, j) | i <- ['a'..'h'], j <- [1..8], i == x || j == y]
getPossibleMoves c Bishop (x, y)        = intersect (getBishopMoves (x, y) [1..7]) [(i, j) | i <- ['a'..'h'], j <- [1..8]]
getPossibleMoves c Queen (x, y)         = (getPossibleMoves c Rook (x, y)) ++ (getPossibleMoves c Bishop (x, y))
getPossibleMoves c Knight (x, y)        = intersect (getKnightMoves (x, y)) [(i, j) | i <- ['a'..'h'], j <- [1..8]]
getPossibleMoves Black Pawn (x, y)      = if y == 7 then [(x, 6), (x, 5)] else [(x, y-1)]
getPossibleMoves White Pawn (x, y)      = if y == 2 then [(x, 3), (x, 4)] else [(x, y+1)]

goToChar                                :: Char -> Int -> Char
goToChar l n                            = chr ((ord l) + n)

getBishopMoves                          :: Position -> [Int] -> [Position]
getBishopMoves _ []                     = []
getBishopMoves (x, y) (k:ks)            = [ (i, j) | i <- [goToChar x k, goToChar x (k * (-1))], j <- [y + k, y - k] ] ++ getBishopMoves (x, y) ks

getKnightMoves                          :: Position -> [Position]
getKnightMoves  (x, y)                  = [ (i, j) | i <- [goToChar x 1, goToChar x (-1)], j <- [y+2, y-2]] ++ [ (i, j) | i <- [goToChar x 2, goToChar x (-2)], j <- [y+1, y-1]]
