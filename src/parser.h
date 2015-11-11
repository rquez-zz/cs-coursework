#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#include "symbol.h"
#include "token.h"

int parse(const char* symbolTablePath, token* tokens, symbol* symbolTable);
void program(token* tokens, symbol* symbolTable);
void block(token* tokens, symbol* symbolTable);
void constant(token* tokens, symbol* symbolTable);
void variable(token* tokens, symbol* symbolTable);
void procedure(token* tokens, symbol* symbolTable);
void statement(token* tokens, symbol* symbolTable);
