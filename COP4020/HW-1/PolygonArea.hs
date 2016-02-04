module PolygonArea where

det                         :: (Double, Double) -> (Double, Double) -> Double
det (x1, y1) (x2, y2)       = (x1 * y2) - (x2 * y1)
computeArea          :: [(Double, Double)] -> Double
computeArea []       = error "Can't calculate area of a polygon without vertices."
computeArea [x]      = error "Can't calculate area of a polygon with only one vertex."
computeArea xs       = 0.5 * sum(zipWith (det) (xs) ((tail xs) ++ [(head xs)]))
