module RegularExpressions where

import Prelude hiding ((<*>))
import Data.Char
import Data.List
import Data.Maybe

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
number              = (\s -> a s && b s || s == "0")
                        where
                                a = (\s -> (head s) /= '0')
                                b = (\s -> all (isDigit) s)

fractional          :: RegExp
fractional          = (\s ->
                                c s &&
                                ((a l 1 s && b head l s) && (a r 2 s && b last r s) ||
                                a' l 1 s && (a r 2 s && b last r s) ||
                                a' r 2 s && (a l 1 s && b head l s) ||
                                a' l 1 s && a' r 2 s)
                        ) where a = (\x y s-> length (x s) > y)
                                a' = (\x y s -> not (a x y s))
                                b = (\f x s -> f (x s) /= '0')
                                c = (\s -> elem '.' s)
                                l = (\s -> fst (p s))
                                r = (\s -> snd (p s))
                                p = (\s -> splitAt (fromJust (elemIndex '.' s)) s)
