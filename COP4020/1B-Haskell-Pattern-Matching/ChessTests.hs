--- ChessTests v1.0
--- J. Jakes-Schauer

module ChessTests where
import Chess
import Testing


pawn_tests = assertTrue (isLegalMove White Pawn ('a',7) ('a',8))
             +> assertTrue (isLegalMove Black Pawn ('b',7) ('b',5))
             +> assertFalse (isLegalMove Black Pawn ('c',7) ('c',8))

bishop_test = assertTrue (isLegalMove Black Bishop ('h',1) ('a',8))

knight_test = assertTrue (isLegalMove White Knight ('c',6) ('d',4))

rook_test = assertTrue (isLegalMove Black Rook ('d',1) ('d',7))

queen_tests = assertTrue (isLegalMove White Queen ('h',4) ('a',4))
             +> assertTrue (isLegalMove Black Queen ('c',8) ('a',6))

king_tests = assertTrue (isLegalMove Black King ('f',4) ('f',3))
             +> assertTrue (isLegalMove White King ('f',4) ('e',3))


main = do startTesting "ChessTests v1.0"
          putStrLn "Pawns:"
          doneTesting pawn_tests
          putStrLn "Bishop:"
          doneTesting bishop_test
          putStrLn "Knight:"
          doneTesting knight_test
          putStrLn "Rook:"
          doneTesting rook_test
          putStrLn "Queen:"
          doneTesting queen_tests
          putStrLn "King:"
          doneTesting king_tests
