#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAX_SYMBOL_SIZE 100

typedef struct symbol {

    // const = 1, var = 2, proc = 3
	int kind;

    // Name of the Symbol
	char name[12];

    // Value for constants
	int val;

    // L Level for variables and procedures
	int level;

    // M Address for variables and procedures
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

FILE* openFile(const char* path, const char* op);
void append(char* string, char c);
