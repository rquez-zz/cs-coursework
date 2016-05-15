test1 :- 
  merge([], [1,2,3], L), 
  L = [1,2,3].

test2 :- 
  merge([1,3,5,10], [2,2,6,12], L), 
  L = [1, 2, 2, 3, 5, 6, 10, 12].

test3 :- 
 merge([1,10,99,100], [1,2,98, 101, 102], L),
 L = [1, 1, 2, 10, 98, 99, 100, 101, 102].

test4 :- 
  merge([10,20,30], [], L), 
  L = [10,20,30].

test5 :- split([],L, R),
  L = [],
  R = [].

test6 :- split([1,2],L, R),
  L = [1],
  R = [2].

test7 :- split([1,2,3],L, R),
  L = [1,3],
  R = [2].

test8 :- split([1,2,3,4],L1, L2),
 L1 = [1,3],
 L2 = [2,4]. 

test9 :- 
  mergeSort([], L),
  L = [].

test10 :- 
  mergeSort([1,10,99,100,1,2,98, 101, 102], L),
  L = [1, 1, 2, 10, 98, 99, 100, 101, 102]. 

test11 :- 
  mergeSort([10,9,8,7], L),
  L = [7,8,9,10].

test12 :-
  mergeSort([1,10,2,9,3,8,4,7,5,6], L),
  L = [1,2,3,4,5,6,7,8,9,10].

test13 :-
  split(Inp, [1,3],[2,4]),
  Inp = [1,2,3,4].

testList([]).

testList([Test | Tests]) :-
  call(Test),
  write(Test),
  write(' ok'),
  nl,
  testList(Tests).

testList([Test | Tests]) :-
  \+ call(Test),
  write(Test),
  write(' failed'),
  nl,
  testList(Tests).

testMergeSort :- testList([
  test1,
  test2,
  test3,
  test4,
  test5,
  test6,
  test7,
  test8,
  test9,
  test10,
  test11,
  test12,
  test13]), !.

