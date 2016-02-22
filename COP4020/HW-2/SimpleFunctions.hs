module SimpleFunctions where

filterFirst                         ::  (a -> Bool) -> [a] -> [a]
filterFirst _ []                    = []
filterFirst func (x:xs)             = if func x then x:(filterFirst func xs) else xs

filterLast                          ::  (a -> Bool) -> [a] -> [a]
filterLast func xs                  = reverse (filterFirst func (reverse xs))

split                               ::  [a] -> ([a],[a])
split []                            =   ([], [])
split [x]                           =   ([x], [])
split (x:y:xys)                     =   (x:xs, y:ys)
                                        where (xs, ys) = split xys

interleave                          ::  ([a],[a]) -> [a]
interleave ([],[])                  =   []
interleave (a,[])                   =   a
interleave ([], a)                  =   a
interleave ((x:xs),(y:ys))          =   (x:y:[]) ++ interleave (xs, ys)

merge                               ::  (Ord a) => ([a],[a]) -> [a]
merge ([],[])                       =   []
merge (a, [])                       =   a
merge ([], a)                       =   a
merge ((x:xs),(y:ys))               =   if x < y    then x:(merge (xs,(y:ys)))
                                                    else y:(merge ((x:xs),ys))

mergeSort                           ::  (Ord a) => [a] -> [a]
mergeSort []                        =   []
mergeSort [a]                       =   [a]
mergeSort xs                        =   merge (mergeSort a, mergeSort b)
                                        where   a = fst (split xs)
                                                b = snd (split xs)
