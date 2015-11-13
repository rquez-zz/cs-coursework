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

/* Returns 1 if ident is a const
 * Returns 2 if ident is a var
 * Returns 3 if ident is a proc
 * Returns 0 if ident is not in symbol table
 */
int lookupIdentifier(char* name, symbol** symbolTable) {

    int indexCon = hashToken(name, 1) % MAX_SYMBOL_TABLE_SIZE;
    int indexVar = hashToken(name, 2) % MAX_SYMBOL_TABLE_SIZE;
    int indexPro = hashToken(name, 3) % MAX_SYMBOL_TABLE_SIZE;

    if (strcmp((*symbolTable)[indexCon].name, name) == 0)
        return 1;
    if (strcmp((*symbolTable)[indexVar].name, name) == 0)
        return 2;
    if (strcmp((*symbolTable)[indexPro].name, name) == 0)
        return 3;

    return 0;
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

    while ((*tokens)->type == procsym) {
        procedure(tokens, symbolTable, level);
    }

    statement(tokens, symbolTable);
}

/* constant := constsym identsym equalsym numbersym {commasym identsym equalsym numbersym} semicolonsym*/
void constant(token** tokens, symbol* symbolTable, int level) {

    do {
        *tokens = (*tokens)->next;
        if ((*tokens)->type != identsym) {
            fprintf(stderr, "[PARSER-ERROR] 'const' must be followed by an identifier.\n");
        }
        char* lexeme = (*tokens)->lexeme;

        *tokens = (*tokens)->next;
        if ((*tokens)->type != equalsym) {
            if ((*tokens)->type == becomesym) {
                fprintf(stderr, "[PARSER-ERROR] Use = instead of ':='.\n");
            } else {
                fprintf(stderr, "[PARSER-ERROR] '=' expected \n");
            }
        }

        *tokens = (*tokens)->next;
        if ((*tokens)->type != numbersym) {
            fprintf(stderr, "[PARSER-ERROR] '=' must be followed by a number.\n");
        }

        addToSymbolTable(&symbolTable, lexeme, 1, (*tokens)->value, level, 0);

        *tokens = (*tokens)->next;

    } while((*tokens)->type == commasym);

    if ((*tokens)->type != semicolonsym) {
        fprintf(stderr, "[PARSER-ERROR] ';' missing.\n");
    }

    *tokens = (*tokens)->next;
}

/* variable := varsym identsym {commasym identsym} semicolonsym */
void variable(token** tokens, symbol* symbolTable, int level) {

    do {
        *tokens = (*tokens)->next;
        if ((*tokens)->type != identsym) {
            fprintf(stderr, "[PARSER-ERROR] 'var' must be followed by an identifier.\n");
        }

        addToSymbolTable(&symbolTable, (*tokens)->lexeme, 2, 0, level, 0);

        *tokens = (*tokens)->next;
    } while ((*tokens)->type == commasym);

    if ((*tokens)->type != semicolonsym) {
        fprintf(stderr, "[PARSER-ERROR] ';' missing.\n");
    }

    *tokens = (*tokens)->next;
}

/* procedure := procsym identsym semicolonsym block semicolonsym */
void procedure(token** tokens, symbol* symbolTable, int level) {

    *tokens = (*tokens)->next;
    if ((*tokens)->type != identsym) {
        fprintf(stderr, "[PARSER-ERROR] 'procedure' must be followed by an identifier. Incorrect symbol after procedure declaration.\n");
    }

    addToSymbolTable(&symbolTable, (*tokens)->lexeme, 3, 0, level, 0);

    *tokens = (*tokens)->next;
    if ((*tokens)->type != semicolonsym) {
        fprintf(stderr, "[PARSER-ERROR] ';' missing.\n");
    }

    *tokens = (*tokens)->next;
    block(tokens, symbolTable, ++level);

    if ((*tokens)->type != semicolonsym) {
        fprintf(stderr, "[PARSER-ERROR] ';' missing.\n");
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

            // This identifier can only be a variable
            switch(lookupIdentifier((*tokens)->lexeme, &symbolTable)) {
                case 0:
                    fprintf(stderr, "[PARSER-ERROR] '%s' Undeclared identifier.\n", (*tokens)->lexeme);
                    exit(EXIT_FAILURE);
                case 1:
                    fprintf(stderr, "[PARSER-ERROR] '%s' is a constant, assignment is not allowed.\n", (*tokens)->lexeme);
                    exit(EXIT_FAILURE);
                case 3:
                    fprintf(stderr, "[PARSER-ERROR] '%s' is a procedure, assignment is not allowed.\n", (*tokens)->lexeme);
                    exit(EXIT_FAILURE);
            }

            *tokens = (*tokens)->next;
            if ((*tokens)->type != becomesym) {
                fprintf(stderr, "[PARSER-ERROR] Identifier must be followed by ':='.\n");
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
                fprintf(stderr, "[PARSER-ERROR] Identifier must be followed by 'call'. expected\n");
                exit(EXIT_FAILURE);
            }

            // This identifier can only be a variable
            switch(lookupIdentifier((*tokens)->lexeme, &symbolTable)) {
                case 0:
                    fprintf(stderr, "[PARSER-ERROR] '%s' Undeclared identifier.\n", (*tokens)->lexeme);
                    exit(EXIT_FAILURE);
                case 1:
                    fprintf(stderr, "[PARSER-ERROR] Call of a constant is meaningless\n");
                    exit(EXIT_FAILURE);
                case 2:
                    fprintf(stderr, "[PARSER-ERROR] Call of a variable is meaningless\n");
                    exit(EXIT_FAILURE);
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

}

/* condition :=     oddsym expression
 *              |   expression rel-op expression
 */
void condition(token** tokens, symbol* symbolTable) {

    if ((*tokens)->type == oddsym) {

        (*tokens) = (*tokens)->next;
        expression(tokens, symbolTable);

    } else {

        expression(tokens, symbolTable);

        // relational operations between [9-14]
        if ((*tokens)->type < 9 || (*tokens)->type > 14) {
            fprintf(stderr, "[PARSER-ERROR] relational operator expected\n");
        }
        (*tokens) = (*tokens)->next;

        expression(tokens, symbolTable);
    }
}

/* expression := [plussym | minussym] term { (plussym | minussym) term } */
void expression(token** tokens, symbol* symbolTable) {

    if ((*tokens)->type == plussym || (*tokens)->type == minussym) {
        (*tokens) = (*tokens)->next;
    }

    term(tokens, symbolTable);

    while ((*tokens)->type == plussym || (*tokens)->type == minussym) {
        (*tokens) = (*tokens)->next;
        term(tokens, symbolTable);
    }
}

/* term := factor { (multsym | slashsym) factor } */
void term(token** tokens, symbol* symbolTable) {

    factor(tokens, symbolTable);

    while ((*tokens)->type == multsym || (*tokens)->type == slashsym) {
        (*tokens) = (*tokens)->next;
        factor(tokens, symbolTable);
    }
}

/* factor := identsym | numbersym | lparentsym expression rparentsym */
void factor(token** tokens, symbol* symbolTable) {

    switch((*tokens)->type) {

        case identsym:
            // This identifier can only be a variable or constant
            switch(lookupIdentifier((*tokens)->lexeme, &symbolTable)) {
                case 0:
                    fprintf(stderr, "[PARSER-ERROR] '%s' Undeclared identifier.\n", (*tokens)->lexeme);
                    exit(EXIT_FAILURE);
                case 3:
                    fprintf(stderr, "[PARSER-ERROR] '%s' is a procedure and can't be used in an expression.\n", (*tokens)->lexeme);
                    exit(EXIT_FAILURE);
            }

            (*tokens) = (*tokens)->next;
            break;

        case numbersym:
            (*tokens) = (*tokens)->next;
            break;

        case lparentsym:
            (*tokens) = (*tokens)->next;
            expression(tokens, symbolTable);
            if ((*tokens)->type != rparentsym) {
                fprintf(stderr, "[PARSER-ERROR] ')' expected \n");
            }
            (*tokens) = (*tokens)->next;
            break;

        default:
            fprintf(stderr, "[PARSER-ERROR] unexpected token %s\n", (*tokens)->lexeme);
            break;
    }
}
