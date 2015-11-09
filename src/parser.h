#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#include "symbol.h"
#include "token.h"

int parse(const char* symbolTablePath, token* tokens, symbol* symbolTable);
