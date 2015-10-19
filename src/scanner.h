#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAX_SYMBOL_TABLE_SIZE 100

typedef enum {
    nulsym = 1,
    identsym,
    numbersym,
    plussym,
    minussym, 
    multsym,
    slashsym,
    oddsym,
    equalsym,
    neqsym,
    lessym,
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
    thensym,
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

typedef struct symbol {

    // const = 1, var = 2, proc = 3
	int kind;

    // Name of the Symbol
	char lexeme[12];

    // Value for constants
	int value;

    // L Level for variables and procedures
	int level;

    // M Address for variables and procedures
	int addr;

    token_type type;

    struct symbol* next;

} symbol;

FILE* openFile(const char* path, const char* op);
void append(char* string, char c);
FILE* getCleanInput(const char* inputPath, const char* outputPath);
void writeSymbolTokens(symbol* symbols, FILE* ofp);
token_type getReservedType(char* lexeme);
