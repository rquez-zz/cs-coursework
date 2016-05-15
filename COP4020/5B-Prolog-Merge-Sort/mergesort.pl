% implement yourself

% merge/3
merge([], [], []).
merge([], L, L).
merge(L, [], L).
merge([H1|T1], [H2|T2], L) :-
    H1 < H2 -> L = [H1|L2], merge(T1, [H2|T2], L2);
    H1 > H2 -> L = [H2|L2], merge([H1|T1], T2, L2);
    H1 == H2 -> L = [H1|L2], L2 = [H2|L3], merge(T1, T2, L3).

% split/3
split([], [], []).
split([], _, _).
split([H|T], A, B) :-
    A = [H|A2], split(T, B, A2).

% mergeSort/2
mergeSort([], []).
mergeSort([H], [H]).
mergeSort(X,L) :-
    split(X, L1, L2),
    mergeSort(L1, M1),
    mergeSort(L2, M2),
    merge(M1, M2, L).

