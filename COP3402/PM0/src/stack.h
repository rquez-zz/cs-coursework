#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int opcode;
    int lex;
    int param;
} instruction;

instruction* read(const char* path);
int execute(instruction* instruction, int index);
