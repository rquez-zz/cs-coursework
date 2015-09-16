#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int opcode;
    int lex;
    int param;
} instruction;

int execute(instruction* IR, int PC, int* halt);
int stack(FILE* inputPtr, FILE* outputPtr);
void read(FILE* inputPtr, instruction* instructions);
char* buildInstructionsString(instruction* instrutions);
char* buildTraceLine(int prevPC,
        instruction* IR, int PC, int BP, int SP, int* stack);
