#include "stack.h"

const int MAX_STACK_HEIGHT = 200;
const int MAX_CODE_LENGTH = 500;
const int MAX_LINE_LENGTH = 10;
const int MAX_LEXI_LEVEL = 3;

int stack(FILE* filePtr) {

    int SP = 0;
    int BP = 0;
    int PC = 0;
    int IR = 0;

    int halt = 0;

    // Read instructions
    instruction instructions[MAX_CODE_LENGTH];
    read(filePtr, instructions);

    int i = 0;
    while(!halt) {
        // Fetch next instruction
        instruction* nextInstruction = &instructions[i - 1];
        // Execute next instruction and get next index
        i = execute(nextInstruction, i);
    }

    return 0;
}

//Returns a pointer to array of instructions read from input
instruction* read(const char* path) {

}

// Executes instructions
// Returns next index
int execute(instruction* instruction, int index) {

}
