% contains

test1 :-
  insertList(nil, [6,2,3,1,4,5], T),
  contains(T, 1).

test2 :-
  insertList(nil, [6,2,3,1,4,5], T),
  \+ contains(T, 0).

test3 :-
  insertList(nil, [], T),
  \+ contains(T, 99).

test4 :-
  insertList(nil, [3,4,5,1,2,0], T),
  contains(T, 0).

% minimum

test5 :-
  insertList(nil, [6,2,3,1,4,5], T),
  minimum(T, 1).

test6 :-
  insertList(nil, [6,2,3,4,5], T),
  minimum(T, 2).

test7 :-
  insertList(nil, [], T),
  \+ minimum(T, 99).

test8 :-
  insertList(nil, [3,4,5,1,2,0], T),
  maximum(T, 5).

% maximum

test9 :-
  insertList(nil, [6,2,3,1,4,5], T),
  maximum(T, 6).

test10 :-
  insertList(nil, [8,2,3,4,5], T),
  maximum(T, 8).

test11 :-
  insertList(nil, [], T),
  \+ maximum(T, 33).

test12 :-
  insertList(nil, [3,4,5,1,9,0], T),
  maximum(T, 9).

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

testBst :- testList([
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
  test12]), !.


