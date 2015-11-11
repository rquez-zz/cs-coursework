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
    block(tokens, symbolTable);
    if (tokens->type != periodsym) {
        // TODO: throw error
    }
}

/* block := [constant] [variable] {procedure} [statement] */
void block(token* tokens, symbol* symbolTable) {

    if (tokens->type == constsym) {
        constant(tokens, symbolTable);
    } else if (tokens->type == varsym) {
        variable(tokens, symbolTable);
    } else if(tokens->type == procsym) {
        procedure(tokens, symbolTable);
    } else {
        // TODO: throw error
    }

    statement(tokens, symbolTable);
}

/* constant := constsym identsym equalsym numbersym {commasym identsym equalsym numbersym} semicolonsym*/
void constant(token* tokens, symbol* symbolTable) {

    if (tokens->type != constsym) {
        // TODO: throw error
    }

    do {
        tokens = tokens->next;
        if (tokens->type != identsym) {
            // TODO: throw error
        }
        char* name = tokens->lexeme;

        tokens = tokens->next;
        if (tokens->type != equalsym) {
            // TODO: throw error
        }

        tokens = tokens->next;
        if (tokens->type != numbersym) {
            // TODO: throw error
        }
        int value = tokens->value;

        addToSymbolTable(&symbolTable, name, 1, value, 0, 0);

        tokens = tokens->next;

    } while(tokens->type == commasym);

    if (tokens->type != semicolonsym) {
        // TODO: throw error
    }

}

void variable(token* tokens, symbol* symbolTable) {

}

void procedure(token* tokens, symbol* symbolTable) {

}

void statement(token* tokens, symbol* symbolTable) {

}
