#include <stdio.h>
#include "symbol.h"
#include "token.h"

int generate(const char* mcodePath, token* tokens, symbol* symbolTable);
int buildCode(char* instruction, int level, int address);
