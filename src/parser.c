#include "parser.h"

int parse(const char* symbolTablePath, token* tokens, symbol* symbolTablePtr) {

    program(tokens, symbolTablePtr, 0);
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
void program(token* tokens, symbol* symbolTable, int level) {
    block(&tokens, symbolTable, level);
    if (tokens->type != periodsym) {
        fprintf(stderr, "[PARSER-ERROR] '.' expected \n");
    }
}

/* block := [constant] [variable] {procedure} [statement] */
void block(token** tokens, symbol* symbolTable, int level) {

    if ((*tokens)->type == constsym) {
        constant(tokens, symbolTable, level);
    }

    if ((*tokens)->type == varsym) {
        variable(tokens, symbolTable, level);
    }

    if((*tokens)->type == procsym) {
        procedure(tokens, symbolTable, level);
    }

    statement(tokens, symbolTable);
}

/* constant := constsym identsym equalsym numbersym {commasym identsym equalsym numbersym} semicolonsym*/
void constant(token** tokens, symbol* symbolTable, int level) {

    do {
        *tokens = (*tokens)->next;
        if ((*tokens)->type != identsym) {
            fprintf(stderr, "[PARSER-ERROR] identifier expected \n");
        }
        char* lexeme = (*tokens)->lexeme;

        *tokens = (*tokens)->next;
        if ((*tokens)->type != equalsym) {
            fprintf(stderr, "[PARSER-ERROR] '=' expected \n");
        }

        *tokens = (*tokens)->next;
        if ((*tokens)->type != numbersym) {
            fprintf(stderr, "[PARSER-ERROR] number expected \n");
        }

        addToSymbolTable(&symbolTable, lexeme, 1, (*tokens)->value, level, 0);

        *tokens = (*tokens)->next;

    } while((*tokens)->type == commasym);

    if ((*tokens)->type != semicolonsym) {
        fprintf(stderr, "[PARSER-ERROR] ';' expected \n");
    }

    *tokens = (*tokens)->next;
}

/* variable := varsym identsym {commasym identsym} semicolonsym */
void variable(token** tokens, symbol* symbolTable, int level) {

    do {
        *tokens = (*tokens)->next;
        if ((*tokens)->type != identsym) {
            fprintf(stderr, "[PARSER-ERROR] identifier expected \n");
        }

        addToSymbolTable(&symbolTable, (*tokens)->lexeme, 2, 0, level, 0);

        *tokens = (*tokens)->next;
    } while ((*tokens)->type == commasym);

    if ((*tokens)->type != semicolonsym) {
        fprintf(stderr, "[PARSER-ERROR] ';' expected \n");
    }

    *tokens = (*tokens)->next;
}

/* procedure := procsym identsym semicolonsym block semicolonsym */
void procedure(token** tokens, symbol* symbolTable, int level) {

    *tokens = (*tokens)->next;
    if ((*tokens)->type != identsym) {
        fprintf(stderr, "[PARSER-ERROR] identifier expected \n");
    }

    addToSymbolTable(&symbolTable, (*tokens)->lexeme, 3, 0, level, 0);

    *tokens = (*tokens)->next;
    if ((*tokens)->type != semicolonsym) {
        fprintf(stderr, "[PARSER-ERROR] ';' expected \n");
    }

    *tokens = (*tokens)->next;
    block(tokens, symbolTable, ++level);

    if ((*tokens)->type != semicolonsym) {
        fprintf(stderr, "[PARSER-ERROR] ';' expected \n");
    }

    *tokens = (*tokens)->next;
}

/*
 * statement :=     ident becomesym expression
 *              |   callsym identsym
 *              |   beginsym statement {semicolonsym statement} endsym
 *              |   ifsym condition thensym statement [elsesym statement]
 *              |   whilesym condition dosym statement
 *              |   readsym identsym
 *              |   writesym identsym
 *              |   e
 */
void statement(token** tokens, symbol* symbolTable) {

    switch((*tokens)->type) {

        // ident becomesym expression
        case identsym:
            *tokens = (*tokens)->next;
            if ((*tokens)->type != becomesym) {
                fprintf(stderr, "[PARSER-ERROR] ':=' expected\n");
            }
            *tokens = (*tokens)->next;

            expression(tokens, symbolTable);

            break;

        // callsym identsym
        case callsym:
            *tokens = (*tokens)->next;
            if ((*tokens)->type != identsym) {
                fprintf(stderr, "[PARSER-ERROR] 'identifier' expected\n");
            }
            *tokens = (*tokens)->next;
            break;

        // beginsym statement {semicolonsym statement} endsym
        case beginsym:
            *tokens = (*tokens)->next;

            statement(tokens, symbolTable);

            while ((*tokens)->type == semicolonsym) {
                *tokens = (*tokens)->next;
                statement(tokens,symbolTable);
            }

            if ((*tokens)->type != endsym) {
                fprintf(stderr, "[PARSER-ERROR] 'end' expected\n");
            }
            *tokens = (*tokens)->next;
            break;

        // ifsym condition thensym statement [elsesym statement]
        case ifsym:
            *tokens = (*tokens)->next;

            condition(tokens, symbolTable);

            if ((*tokens)->type != thensym) {
                fprintf(stderr, "[PARSER-ERROR] 'then' expected\n");
            }
            *tokens = (*tokens)->next;

            statement(tokens,symbolTable);

            if ((*tokens)->type == elsesym) {
                *tokens = (*tokens)->next;
                statement(tokens,symbolTable);
            }
            break;

        // whilesym condition dosym statement
        case whilesym:
            *tokens = (*tokens)->next;

            condition(tokens, symbolTable);

            if ((*tokens)->type != dosym) {
                fprintf(stderr, "[PARSER-ERROR] 'do' expected\n");
            }
            *tokens = (*tokens)->next;

            statement(tokens,symbolTable);

            break;

        // readsym identsym
        case readsym:
            *tokens = (*tokens)->next;
            if ((*tokens)->type != identsym) {
                fprintf(stderr, "[PARSER-ERROR] 'identifier' expected\n");
            }
            *tokens = (*tokens)->next;
            break;

        // writesym identsym
        case writesym:
            *tokens = (*tokens)->next;
            if ((*tokens)->type != identsym) {
                fprintf(stderr, "[PARSER-ERROR] 'identifier' expected\n");
            }
            *tokens = (*tokens)->next;
            break;

        // empty string case
        default:
            break;

}
