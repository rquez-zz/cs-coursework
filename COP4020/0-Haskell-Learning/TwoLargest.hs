module TwoLargestInClass where

getTwoLargest :: (Ord a) => [a] -> (a,a)

-- Error cases
getTwoLargest []            = error "empty list"
getTwoLargest [_]           = error "list contains only one element"
getTwoLargest (x0:x1:xs)    =
    if x0 >= x1
        then getTwoLargest_iter (x0,x1) xs
        else getTwoLargest_iter (x1,x0) xs

update :: (Ord a) => (a,a) -> a -> (a,a)
update (x0,x1) y =
    if y >= x0
        then (y, x0)
        else if y >= x1
            then (x0, y)
            else (x0, x1)

getTwoLargest_iter :: (Ord a) => (a,a) -> [a] -> (a,a)
getTwoLargest_iter (x0,x1) [] = (x0,x1)
getTwoLargest_iter (x0,x1) (y:ys) = getTwoLargest_iter (update (x0,x1) y) ys
