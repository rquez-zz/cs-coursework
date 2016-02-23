module RegularExpressions where

import Prelude hiding ((<*>))
import Data.Char

type RegExp         = String -> Bool

epsilon             :: RegExp
epsilon             = (== "")

char                :: Char ->  RegExp
char ch             = (== [ch])

(|||)               :: RegExp -> RegExp -> RegExp
(|||) e1 e2         =  \s -> e1 s || e2 s

-- splits "fun" ~~> [("","fun"),("f","un"),("fu","n"),("fun","")]
splits              :: [a] -> [([a],[a])]
splits xs           = map (flip splitAt xs) [0..length xs]

(<*>)               :: RegExp -> RegExp -> RegExp
(<*>) e1 e2         = \s -> or [ e1 prefix && e2 suffix | (prefix,suffix) <- splits s]

(<**>)              :: RegExp -> RegExp -> RegExp
(<**>) e1 e2        = \s -> or [ e1 prefix && e2 suffix | (prefix,suffix) <- drop 1 (splits s)]

star                :: RegExp -> RegExp
star e              = epsilon ||| (e <**> star e)

option              :: RegExp -> RegExp
option e            = epsilon ||| e

plus                :: RegExp -> RegExp
plus e              = e ||| (e <**> star e)

number              :: RegExp
number              = (\s -> (/= '0') (head s) && all (isDigit) s)

fractional          :: RegExp
fractional          =   (\s-> (hasDot s && hasNoZerosBegin s && hasNoZerosEnd s) || (hasNoZerosBegin s && endsInDotZero s))
                        where   hasDot = (\s -> any (== '.') s)
                                hasNoZerosBegin = (\s -> (head s) /= '0')
                                hasNoZerosEnd = (\s -> (last s) /= '0')
                                endsInDotZero = (\s -> (drop ((length s) - 2) s) == ".0")
