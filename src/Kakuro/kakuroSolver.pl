:- use_module(library(clpfd)).

% Entradas: No recibe parametros
% Salidas: Devuelve una lista
% soluciona el kakuro #1
solucionador1(Vars):- 
    Vars=[X1,X2,X3,X4,X5,X6,X7,X8,X9,X10,X11,X12,X13,X14,X15,X16,X17,X18,X19,X20,X21,X22,
        X23,X24,X25,X26,X27,X28,X29,X30,X31,X32,X33,X34,X35,X36,X37,X38,X39,X40,X41,X42,X43,X44], 
    Vars ins 1..9,
    verificarSuma([X1,X2],4),
    verificarSuma([X3,X4],6),
    verificarSuma([X5, X6, X7],8),
    verificarSuma([X8,X9,X10],6),
    verificarSuma([X11,X12,X13,X14],10),
    verificarSuma([X15,X16, X17],7),
    verificarSuma([X18,X19],3),
    verificarSuma([X20,X21,X22],6),
    verificarSuma([X23,X24,X25],6),
    verificarSuma([X26,X27],3),
    verificarSuma([X28,X29,X30],24),
    verificarSuma([X31,X32,X33,X34],10),
    verificarSuma([X35,X36,X37],23),
    verificarSuma([X38,X39,X40],6),
    verificarSuma([X41,X42],8),
    verificarSuma([X43,X44],12),
    verificarSuma([X1,X5,X12,X19],10),
    verificarSuma([X2,X6,X13],6),
    verificarSuma([X3,X8,X15,X22],11),
    verificarSuma([X4,X9,X16],7),
    verificarSuma([X7,X14,X20,X24],11),
    verificarSuma([X10,X17],3),
    verificarSuma([X11,X18],3),
    verificarSuma([X21,X25,X31,X38],10),
    verificarSuma([X23,X30,X37,X42],17),
    verificarSuma([X26,X33,X40,X44],15),
    verificarSuma([X27,X34],4),
    verificarSuma([X28,X35],16),
    verificarSuma([X29,X36,X41],23),
    verificarSuma([X32,X39,X43],6),
    labeling([], Vars),
    !.

% Entradas: No recibe parametros
% Salidas: Devuelve una lista
% soluciona el kakuro #2
solucionador2(Vars):- 
    Vars=[X1,X2,X3,X4,X5,X6,X7,X8,X9,X10,X11,X12,X13,X14,X15,X16,X17,X18,X19,X20,X21,X22,
        X23,X24,X25,X26,X27,X28,X29,X30,X31,X32,X33,X34,X35,X36,X37,X38,X39,X40,X41,X42,X43,X44], 
    Vars ins 1..9,
    verificarSuma([X1,X2],4),
    verificarSuma([X3,X4,X5,X6],11),
    verificarSuma([X7, X8, X9,X10,X11,X12,X13],28),
    verificarSuma([X14,X15],16),
    verificarSuma([X16,X17],6),
    verificarSuma([X18,X19],15),
    verificarSuma([X20,X21,X22],16),
    verificarSuma([X23,X24,X25],23),
    verificarSuma([X26,X27],17),
    verificarSuma([X28,X29],17),
    verificarSuma([X30,X31],17),
    verificarSuma([X32,X33,X34,X35,X36,X37,X38],37),
    verificarSuma([X39,X40,X41,X42],29),
    verificarSuma([X43,X44],10),
    verificarSuma([X1,X7],4),
    verificarSuma([X2,X8],7),
    verificarSuma([X3,X10,X15],20),
    verificarSuma([X4,X11],3),
    verificarSuma([X5,X12],4),
    verificarSuma([X6,X13,X16,X21,X27],28),
    verificarSuma([X9,X14,X19,X25],29),
    verificarSuma([X17,X22],5),
    verificarSuma([X18,X24,X29,X32,X39],29),
    verificarSuma([X20,X26,X31,X36],25),
    verificarSuma([X23,X28],17),
    verificarSuma([X30,X35,X42],24),
    verificarSuma([X33,X40],12),
    verificarSuma([X34,X41],14),
    verificarSuma([X37,X43],16),
    verificarSuma([X38,X44],9),
    labeling([], Vars),
    !.

% Entradas: No recibe parametros
% Salidas: Devuelve una lista
% soluciona el kakuro #3
solucionador3(Vars):- 
    Vars=[X1,X2,X3,X4,X5,X6,X7,X8,X9,X10,X11,X12,X13,X14,X15,X16,X17,X18,X19,X20,X21,X22,
        X23,X24,X25,X26,X27,X28,X29,X30,X31,X32,X33,X34,X35,X36,X37,X38,X39,X40,X41,X42,X43,X44], 
    Vars ins 1..9,
    verificarSuma([X1,X2],3),
    verificarSuma([X3,X4],3),
    verificarSuma([X5,X6,X7,X8,X9,X10],22),
    verificarSuma([X11,X12],11),
    verificarSuma([X13,X14],4),
    verificarSuma([X15,X16],4),
    verificarSuma([X17,X18,X19,X20,X21,X22],39),
    verificarSuma([X23,X24,X25,X26,X27,X28],28),
    verificarSuma([X29,X30],9),
    verificarSuma([X31,X32],11),
    verificarSuma([X33,X34],8),
    verificarSuma([X35,X36,X37,X38,X39,X40],28),
    verificarSuma([X41,X42],7),
    verificarSuma([X43,X44],16),
    verificarSuma([X1,X5],3),
    verificarSuma([X2,X6,X13,X20,X24,X31,X38],38),
    verificarSuma([X3,X8],3),
    verificarSuma([X4,X9,X15],10),
    verificarSuma([X7,X14,X21,X25,X32,X39,X43],31),
    verificarSuma([X10,X16],4),
    verificarSuma([X11,X17],6),
    verificarSuma([X12,X18],16),
    verificarSuma([X19,X23],11),
    verificarSuma([X22,X26],17),
    verificarSuma([X27,X33],4),
    verificarSuma([X28,X34],9),
    verificarSuma([X29,X35],16),
    verificarSuma([X30,X36,X41],7),
    verificarSuma([X37,X42],9),
    verificarSuma([X40,X44],9),
    labeling([], Vars),
    !.

% Entradas: No recibe parametros
% Salidas: Devuelve una lista
% soluciona el kakuro #4
solucionador4(Vars):- 
    Vars=[X1,X2,X3,X4,X5,X6,X7,X8,X9,X10,X11,X12,X13,X14,X15,X16,X17,X18,X19,X20,X21,X22,
        X23,X24,X25,X26,X27,X28,X29,X30,X31,X32,X33,X34,X35,X36,X37,X38,X39,X40,X41,X42,X43,X44], 
    Vars ins 1..9,
    verificarSuma([X1,X2],4),
    verificarSuma([X3,X4,X5,X6],19),
    verificarSuma([X7,X8,X9,X10,X11,X12,X13],32),
    verificarSuma([X14,X15],16),
    verificarSuma([X16,X17],3),
    verificarSuma([X18,X19],3),
    verificarSuma([X20,X21,X22],11),
    verificarSuma([X23,X24,X25],11),
    verificarSuma([X26,X27],16),
    verificarSuma([X28,X29],4),
    verificarSuma([X30,X31],13),
    verificarSuma([X32,X33,X34,X35,X36,X37,X38],34),
    verificarSuma([X39,X40,X41,X42],28),
    verificarSuma([X43,X44],16),
    verificarSuma([X1,X7],3),
    verificarSuma([X2,X8,X14,X19,X24,X28,X32,X39],36),
    verificarSuma([X9,X15],10),
    verificarSuma([X3,X10],16),
    verificarSuma([X4,X11],5),
    verificarSuma([X5,X12,X16,X20],17),
    verificarSuma([X6,X13,X17,X21,X26,X31,X37,X43],37),
    verificarSuma([X9,X15],10),
    verificarSuma([X18,X23],3),
    verificarSuma([X22,X27],13),
    verificarSuma([X25,X29,X33,X40],11),
    verificarSuma([X30,X36],9),
    verificarSuma([X34,X41],16),
    verificarSuma([X35,X42],17),
    verificarSuma([X38,X44],17),
    labeling([], Vars),
    !.

% Entradas: Una lista y un entero
% Salidas: Devuelve un Booleano
% verifica que la suma de los datos introducidos, sea igual a la del kakuro
verificarSuma(L,Sum):- 
    sum(L, #=, Sum),
    all_different(L).

% Entradas: Dos listas 
% Salidas: Booleano
% verifica si dos listas son iguales
esSolucion(L1, L2):- L1 == L2.

% Entradas: Una lista 
% Salidas: Devulve la cantidad de ceros que existen en la lista
% cuenta la cantidad de celdas vacias de la solucion del usuario
celdasVacias(L,N) :-
    include(=(0), L, L2), length(L2, N).

% Entradas: Dos listas y un entero
% Salidas: devuelve un entero
% devuelve la cantidad de elementos distintos en las listas
obtenerErrores([], [], 0).
obtenerErrores([X|Xs], [Y|Ys], Contador):- X\=Y, Y\=0, obtenerErrores(Xs,Ys,ContadorAux), Contador is ContadorAux+1, !.
obtenerErrores([X|Xs],[Y|Ys], Contador):-(X==Y; Y==0), obtenerErrores(Xs,Ys,Contador), !.

% Entradas: Una lista y un entero
% Salidas: Un entero
% Devuelve el elemento de la lista ingresado como indice 
obtenerPorIndice([X], 0, X).
obtenerPorIndice([H|_], 0, H):- !.
obtenerPorIndice([_|T], I, E) :- NuevoIndice is I-1, obtenerPorIndice(T, NuevoIndice, E), !.



