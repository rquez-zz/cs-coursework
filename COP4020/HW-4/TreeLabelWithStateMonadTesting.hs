module TreeLabelWithStateMonadTesting where
import Test.HUnit
import TreeLabelWithStateMonad

leaf v = Node v Nil Nil

example_tree_0 = Node "Haskell" Nil Nil
example_tree_1 = Node "Haskell" (Node "is" Nil Nil) (Node "fun" Nil Nil)
example_tree_2 = Node 'a' (Node 'a' Nil Nil) (Node 'b' (Node 'a' Nil Nil) (Node 'd' Nil Nil))

examples = "Examples" ~: TestList [
              getLabeledTree (Nil :: Tree String) ~?= Nil,
              getLabeledTree example_tree_0 ~?= Node 0 Nil Nil,
              getLabeledTree example_tree_1 ~?= Node 0 (Node 1 Nil Nil) (Node 2 Nil Nil),
              getLabeledTree example_tree_2 ~?= Node 0 (Node 0 Nil Nil) (Node 1 (Node 0 Nil Nil) (Node 2 Nil Nil))
             ]

data Tintin = Tintin
            | Haddock
            | Calculus
            | Snowy
            | Thomson
            | Thompson
            | Castafiore
              deriving (Eq,Show,Ord)

tttesttree = (Node Tintin
              (Node Haddock
               (Node Tintin
                (Node Thomson
                 (Node Snowy
                  (Node Haddock (leaf Thompson) (leaf Thomson))
                  (Node Calculus (leaf Castafiore) (leaf Haddock)))
                 (Node Calculus (leaf Tintin) (leaf Snowy)))
                 (Node Haddock
                  (Node Castafiore
                          (Node Thomson
                                (Node Snowy
                                      (Node Haddock (leaf Thomson) (leaf Thompson))
                                      (Node Calculus (leaf Castafiore) (leaf Haddock)))
                                (Node Calculus (leaf Tintin) (leaf Snowy)))
                           (leaf Castafiore))
                  (Node Calculus
                   (Node Haddock
                    (Node Tintin (leaf Tintin) (leaf Tintin))
                    (leaf Castafiore))
                   (Node Snowy (leaf Haddock) (leaf Thomson)))))
               (Node Castafiore
                (Node Thomson
                 (leaf Castafiore)
                 (Node Calculus (leaf Tintin) (leaf Snowy)))
                (leaf Thompson)))
              (Node Thomson
               (Node Snowy
                (Node Tintin
                 (Node Thomson
                  (Node Snowy
                   (Node Haddock (leaf Thompson) (leaf Thomson))
                   (Node Calculus (leaf Castafiore) (leaf Haddock)))
                  (Node Calculus (leaf Tintin) (leaf Snowy)))
                 (Node Castafiore
                  (Node Thomson
                   (Node Snowy
                    (Node Haddock (leaf Thomson) (leaf Thompson))
                    (Node Calculus (leaf Castafiore) (leaf Haddock)))
                   (Node Calculus (leaf Tintin) (leaf Snowy)))
                  (leaf Tintin)))  
                (Node Calculus (leaf Castafiore) (leaf Haddock)))
               (Node Calculus (leaf Tintin) (leaf Snowy))))


tttest = "Tintin test" ~: getLabeledTree tttesttree ~?= (Node 0 (Node 1 (Node 0 (Node 2 (Node 3 (Node 1 (Node 4 Nil Nil) (Node 2 Nil Nil)) (Node 5 (Node 6 Nil Nil) (Node 1 Nil Nil))) (Node 5 (Node 0 Nil Nil) (Node 3 Nil Nil))) (Node 1 (Node 6 (Node 2 (Node 3 (Node 1 (Node 2 Nil Nil) (Node 4 Nil Nil)) (Node 5 (Node 6 Nil Nil) (Node 1 Nil Nil))) (Node 5 (Node 0 Nil Nil) (Node 3 Nil Nil))) (Node 6 Nil Nil)) (Node 5 (Node 1 (Node 0 (Node 0 Nil Nil) (Node 0 Nil Nil)) (Node 6 Nil Nil)) (Node 3 (Node 1 Nil Nil) (Node 2 Nil Nil))))) (Node 6 (Node 2 (Node 6 Nil Nil) (Node 5 (Node 0 Nil Nil) (Node 3 Nil Nil))) (Node 4 Nil Nil))) (Node 2 (Node 3 (Node 0 (Node 2 (Node 3 (Node 1 (Node 4 Nil Nil) (Node 2 Nil Nil)) (Node 5 (Node 6 Nil Nil) (Node 1 Nil Nil))) (Node 5 (Node 0 Nil Nil) (Node 3 Nil Nil))) (Node 6 (Node 2 (Node 3 (Node 1 (Node 2 Nil Nil) (Node 4 Nil Nil)) (Node 5 (Node 6 Nil Nil) (Node 1 Nil Nil))) (Node 5 (Node 0 Nil Nil) (Node 3 Nil Nil))) (Node 0 Nil Nil))) (Node 5 (Node 6 Nil Nil) (Node 1 Nil Nil))) (Node 5 (Node 0 Nil Nil) (Node 3 Nil Nil))))

tests = "WITH State monad" ~: TestList [
  examples,
  tttest
  ]


main = runTestTT tests
