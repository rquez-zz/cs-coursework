#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int opcode;
    int lex;
    int param;
} instruction;

int stack(FILE* inputPtr, FILE* outputPtr);
void read(FILE* inputPtr, instruction* instructions);
char* buildInstructionsString(instruction* instrutions);
char* buildTraceLine(int prevPC,
        instruction* IR, int PC, int BP, int SP, int* stack);
void execute(instruction* IR, int* PC, int* SP, int* BP, int* halt, int* stack);
char* getOpcodeName(int opcode);
