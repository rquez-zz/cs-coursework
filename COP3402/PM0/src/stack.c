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


/* Fills the instruction array with instructions from the input file */
void read(FILE* filePtr, instruction* instructions) {

    // Char array of each line in the file
    char line[MAX_LINE_LENGTH];

    // Loop through the end of the file
    int i = 0;
    while ( fgets(line, MAX_LINE_LENGTH, (FILE*)filePtr) != NULL ) {
        // Parse token chars to int and assign instruction
        instructions[i].opcode = (int)atoi((char *)strtok(line, " "));
        instructions[i].lex = (int)atoi((char *)strtok(NULL, " "));
        instructions[i].param = (int)atoi((char *)strtok(NULL, " "));
        i++;
    }
}

// Executes instructions
// Returns next index
int execute(instruction* instruction, int index) {

}
