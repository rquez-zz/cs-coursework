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
    int i = 0;
    for (i = 0; i < MAX_SYMBOL_TABLE_SIZE; i++) {
        fprintf(symTblPtr, "%s\t%d\t%d\t%d\t%d\n", symbolTable[i].name, symbolTable[i].kind, symbolTable[i].value, symbolTable[i].level, symbolTable[i].address);
    }
}

/* Adds token to symbol table */
void addToSymbolTable(symbol** symbolTable, char* name, int kind, int value, int level, int address) {
    int index = hashToken(name, kind) % MAX_SYMBOL_TABLE_SIZE;

    // Detect collision
    if ((*symbolTable)[index].kind != 0) {
        linearProbe(&index, symbolTable);
    }

    strcpy((*symbolTable)[index].name,name);
    (*symbolTable)[index].kind = kind;
    (*symbolTable)[index].value = value;
    (*symbolTable)[index].level = level;
    (*symbolTable)[index].address = address;
}

/* Hash function that hashs on name, kind and a prime */
int hashToken(char* name, int kind) {
    int sum = 0;
    for (; *name; name++) {
        sum += *name;
    }
    return (sum + kind) * 71;
}

/* Linear probing collision resolution */
void linearProbe(int* index, symbol** symbolTable) {
    // symbol's kind field will either be 1, 2 or 3
    while ((*symbolTable[*index % MAX_SYMBOL_TABLE_SIZE]).kind != 0) {
        *index += 1;
    }
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

/* variable := varsym identsym {commasym identsym} semicolonsym */
void variable(token* tokens, symbol* symbolTable) {

    if (tokens->type != varsym) {
        // TODO: throw error
    }

    tokens = tokens->next;
    do {
        if (tokens->type != identsym) {
            // TODO: throw error
        }

        addToSymbolTable(&symbolTable, tokens->lexeme, 2, 0, 0, 0);

        tokens = tokens->next;
    } while (tokens->type != commasym);

    if (tokens->type != semicolonsym) {
        // TODO: throw error
    }
}

void procedure(token* tokens, symbol* symbolTable) {

}

void statement(token* tokens, symbol* symbolTable) {

}
