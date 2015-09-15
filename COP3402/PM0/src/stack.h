#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int opcode;
    int lex;
    int param;
} instruction;

int stack(FILE* filePtr);
void read(FILE* filePtr, instruction* instructions);
int execute(instruction* instruction, int index);
