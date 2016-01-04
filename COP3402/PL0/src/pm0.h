#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "instruction.h"
#include "symbol.h"

#define BASE(l, bp) (bp - l*6)

#define MAX_STACK_HEIGHT 200
#define MAX_CODE_LENGTH 500
#define MAX_LINE_LENGTH 10
#define MAX_LEXI_LEVEL 3

FILE* openFile(const char* path, const char* op);
int stack(const char* acodePath, const char* stackTracePath, instruction* mcode, symbol* symbolTable);
char* buildInstructionsString(instruction* instrutions);
char* buildTraceLine(int prevPC, instruction* IR, int PC, int BP, int SP, int* stack);
void execute(instruction* IR, int* PC, int* SP, int* BP, int* halt, int* stack, symbol** symbolTable);
char* getOpcodeName(int opcode);
char* stackString(int* stack, int SP, int BP);
