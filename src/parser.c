#include "parser.h"

int parse(const char* symbolTablePath, token* tokens, symbol* symbolTablePtr) {

    // Get Token
    // Parse Token
    // Enter Token into Symbol Table

    return 0;
}

/* program := block periodsym */
void program(token* tokens, symbol* symbolTable) {
}

/* block := [constant] [variable] {procedure} [statement] */
void block(token* tokens, symbol* symbolTable) {
}

/* constant := constsym identsym equalsym numbersym {commasym identsym equalsym numbersym} semicolonsym*/
void constant(token* tokens, symbol* symbolTable) {

}

void variable(token* tokens, symbol* symbolTable) {

}

void procedure(token* tokens, symbol* symbolTable) {

}

void statement(token* tokens, symbol* symbolTable) {

}
