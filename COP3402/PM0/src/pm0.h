#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int opcode;
    int lex;
    int param;
} instruction;

FILE* openFile(const char* path, const char* op);
void stack(const char* inputPath, const char* outputPath);
void read(FILE* inputPtr, instruction* instructions);
char* buildInstructionsString(instruction* instrutions);
char* buildTraceLine(int prevPC,
        instruction* IR, int PC, int BP, int SP, int* stack);
void execute(instruction* IR, int* PC, int* SP, int* BP, int* halt, int* stack);
char* getOpcodeName(int opcode);
char* stackString(int* stack, int SP, int BP);
