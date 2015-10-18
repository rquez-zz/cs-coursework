#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAX_SYMBOL_SIZE 100

typedef struct symbol {
	int kind;
	char name[12];
	int val;
	int level;
	int addr;
} symbol;

typedef enum {
	nulsym = 1, 
    identsym, 
    numbersym, 
    plussym, 
    minussym, 
    multsym,
	slashsym, 
    oddsym, 
    eqsym, 
    neqsym, 
    lesssym, 
    leqsym, 
    gtrsym,
	geqsym, 
    lparentsym, 
    rparentsym, 
    commasym, 
    semicolonsym,
	periodsym, 
    becomessym, 
    beginsym, 
    endsym, 
    ifsym, 
    tnesym,
	whilesym, 
    dosym, 
    callsym, 
    constsym, 
    varsym, 
    procsym, 
    writesym,
	redsym, 
    elseym
} token_type;
