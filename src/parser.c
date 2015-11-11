#include "parser.h"

int parse(const char* symbolTablePath, token* tokens, symbol* symbolTablePtr) {

    program(tokens, symbolTablePtr);
    FILE* symTblPtr = openFileParser(symbolTablePath, "w");
    writeSymbolTable(symbolTablePtr, symTblPtr);

    return 0;
}

/* Opens a file and returns a FILE pointer */
FILE* openFileParser(const char* path, const char* op) {
    FILE* filePtr;
    filePtr = fopen(path, op);
    if(filePtr == NULL) {
        fprintf(stderr, "[PARSER-ERROR] Error opening %s\n", path);
        perror("");
        return NULL;
    }
    return filePtr;
}

/* Writes symbol table to file */
void writeSymbolTable(symbol* symbolTable, FILE* symTblPtr) {
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
