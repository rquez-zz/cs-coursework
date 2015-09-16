#include "stack.h"

const int MAX_STACK_HEIGHT = 200;
const int MAX_CODE_LENGTH = 500;
const int MAX_LINE_LENGTH = 10;
const int MAX_LEXI_LEVEL = 3;

int stack(FILE* inputPtr, FILE* outputPtr) {

    int SP = 0;
    int BP = 0;
    int PC = 0;
    instruction* IR;

    int halt = 0;

    // Initialize stack
    int stack[MAX_STACK_HEIGHT];
    memset(stack, 0, sizeof(int));

    // Read instructions
    instruction instructions[MAX_CODE_LENGTH];
    read(inputPtr, instructions);

    // Fetch Cycle
    while(halt == 0) {
        // Fetch instruction
        IR = &instructions[PC];
        // Execute instruction and return new PC
        PC = execute(IR, PC, &halt);
    }

    printf("Stack operations halted.\n");

    return 0;
}

/* Fills the instruction array with instructions from the input file */
void read(FILE* inputPtr, instruction* instructions) {

    // Char array of each line in the file
    char line[MAX_LINE_LENGTH];

    // Loop through the end of the file
    int i = 0;
    while ( fgets(line, MAX_LINE_LENGTH, (FILE*)inputPtr) != NULL ) {
        // Parse token chars to int and assign instruction
        instructions[i].opcode = (int)atoi((char *)strtok(line, " "));
        instructions[i].lex = (int)atoi((char *)strtok(NULL, " "));
        instructions[i].param = (int)atoi((char *)strtok(NULL, " "));
        i++;
    }
}

/* Executes the instruction IR and increments the PC */
int execute(instruction* IR, int PC, int* halt) {

    // Read Instructions
    int opcode = IR->opcode;
    int lex = IR->lex;
    int param =  IR->param;

    // Switch statement on opcode
    switch (opcode) {
        case 1: // LIT
            break;
        case 2: // OPR
            break;
        case 3: // LOD
            break;
        case 4: // STO
            break;
        case 5: // CAL
            break;
        case 6: // INC
            break;
        case 7: // JMP
            return param;
            break;
        case 8: // JPC
            break;
        case 9: // SIO 1
            break;
        case 10: // SIO 2
            break;
        case 11: // SIO 3
            *halt = 1;
            break;
    }

    // Increment PC
    return PC + 1;
}
