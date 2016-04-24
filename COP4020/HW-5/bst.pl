% binary search tree

% insert

insert(nil, V, node(V, nil, nil)).

insert(node(V, Left, Right), V, node(V, Left, Right)).

insert(node(V, Left, Right), W, node(V, LeftNew, Right)) :-
  W =< V,
  insert(Left, W, LeftNew).

insert(node(V, Left, Right), W, node(V, Left, RightNew)) :-
  W > V,
  insert(Right, W, RightNew).

% insertList

insertList(T, [], T).
insertList(T1, [H | Tail], T) :-
   insert(T1, H, T2),
   insertList(T2, Tail, T).

% implement yourself:
isNotEmpty(node(V, _, _)) :- V \= nil.

isEmpty(nil).
isEmpty(node(nil, nil, nil)).

% contains/2
contains(node(V, _, _), V).
contains(node(V, Left, _), X) :- V > X, contains(Left, X).
contains(node(V, _, Right), X) :- V < X, contains(Right, X).

% maximum/2
maximum(node(_, _, Right), X) :-
    isNotEmpty(Right),
    maximum(Right, X).
maximum(node(V, _, Right), V) :-
    isEmpty(Right).

% minimum/2
minimum(node(_, Left, _), X) :-
    isNotEmpty(Left),
    minimum(Left, X).
minimum(node(V, Left, _), V) :-
    isEmpty(Left).

