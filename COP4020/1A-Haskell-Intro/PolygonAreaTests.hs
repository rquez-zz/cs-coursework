--- PolygonAreaTests v1.0
--- J. Jakes-Schauer

module PolgonAreaTests where
import PolygonArea
import Testing
import FloatTesting
  
type Vertex = (Double,Double)
type Polygon = [Vertex]




-- Vertices listed clockwise:
square :: Polygon
square = [(1.5,0),
          (3.5,0),
          (3.5,2),
          (1.5,2)] -- Area: 4

triangle :: Polygon
triangle = [(1.566,3),
            (0,0),
            (2,0)] -- Area: 3

lozenge :: Polygon
lozenge = [(0,1),
           (1,0),
           (2,1),
           (1,2)] -- Area: 2

trapezoid :: Polygon
trapezoid = [(0,-1),
             (5,-1),
             (2.3,6),
             (1.3,6)] -- Area: 21

-- Random quadrilateral:
quad :: Polygon
quad = [(0,0),
        (1,-2.5),
        (10.2,20),
        (1,30)] -- Area: 165.75?

pent :: Polygon
pent = [(0,0),
        (1,-2.5),
        (10.2,20),
        (5,15),
        (1,30)] -- 116.75

hex :: Polygon
hex = [(0,0),
       (1,-2.5),
       (10.2,20),
       (8,32.33),
       (5,13.6),
       (1,30)]
  
-- Approaches π as n grows.
circle :: Polygon
circle = makeCircle 1 1000
  
makeCircle r n = map makeVert [0..n-1] where
  makeVert i = let th = i*2.0*pi/n in
                (r*cos th, r*sin th) 


tests = exceptionTest (computeArea []) "computeArea []"
        +> exceptionTest (computeArea [(0x600d,0xf00d)]) "computeArea [(0x600d,0xf00d)]"
        +> withinTest (computeArea square) "~=~" 4
        +> withinTest (computeArea triangle) "~=~" 3
        +> withinTest (computeArea lozenge) "~=~" 2
        +> withinTest (computeArea trapezoid) "~=~" 21
        +> withinTest (computeArea quad) "~=~" 165.75
        +> withinTest (computeArea pent) "~=~" 116.75
        +> withinTest (computeArea hex) "~=~" 149.408
        +> withinTest (computeArea circle) "~=~" 3.1415719827794306 


main = do startTesting "PolygonAreaTests v1.2"
          doneTesting tests
