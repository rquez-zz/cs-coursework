-- $Id: FloatTesting.hs,v 1.3 2013/02/22 16:05:21 leavens Exp leavens $
module FloatTesting where
import Testing

withinMaker :: (RealFloat a) => a -> a -> a -> Bool
withinMaker eps x y = abs(x - y) < eps

relativeMaker eps x y = abs(x - y) < eps*abs(y)

(~=~) :: (RealFloat a, Tolerance a) => a -> a -> Bool
(~=~) = withinMaker hwTolerance
(~~~) :: (RealFloat a, Tolerance a) => a -> a -> Bool
(~~~) = relativeMaker hwTolerance

withinTest :: (Show a, RealFloat a, Tolerance a) => 
                 a -> String -> a -> TestCase a
withinTest = gTest (~=~)

vecWithin :: (Show a, RealFloat a, Tolerance a) => 
                 [a] -> String -> [a] -> TestCase [a]
vecWithin = gTest (\xs ys -> length xs == length ys 
                             && all (uncurry (~=~)) (zip xs ys))

class (RealFloat a) => Tolerance a where
  hwTolerance :: a

instance Tolerance Float where
  hwTolerance = 1.0e-5

instance Tolerance Double where
  hwTolerance = 1.0e-9

