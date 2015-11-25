#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#include "scanner.h"
#include "parser.h"

#define INPUT_PATH "input/input.txt"
#define CLEANINPUT_PATH "output/cleaninput.txt"
#define LEXEMETABLE_PATH "output/lexemetable.txt"
#define TOKENLIST_PATH "output/tokenlist.txt"
#define SYMBOLTABLE_PATH "output/symboltable.txt"
#define MCODE_PATH "output/mcode.txt"
#define ACODE_PATH "output/acode.txt"
#define STACKTRACE_PATH "output/stacktrace.txt"

#define TOKENLIST_SWITCH "-t"
#define SYMBOLTABLE_SWITCH "-s"
#define MCODE_SWITCH "-m"
#define ACODE_SWITCH "-a"
#define STACKTRACE_SWITCH "-s"

void printTokenList(token* tokens);
void printSymbolTable(symbol* symbolTable);
void printMachineCode(instruction* instructions);
void printDisassembledCode();
void printStackTrace();
