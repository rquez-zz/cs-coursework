#include "../src/stack.c"
#include <assert.h>

/* Opens a file and returns a FILE pointer */
FILE* openFile(const char* path) {
    FILE* filePtr;
    filePtr = fopen(path, "r");
    if(filePtr == NULL) {
        perror("Error opening input file.");
    }
    return filePtr;
}

void read_test() {

    const char* inputPath = "../input/mcode.txt";
    FILE* filePtr = openFile(inputPath);
    instruction instructions[50];
    read(filePtr, instructions);
    fclose(filePtr);

    assert(instructions[0].opcode == 7);
    assert(instructions[0].lex == 0);
    assert(instructions[0].param == 10);

    assert(instructions[1].opcode == 7);
    assert(instructions[1].lex == 0);
    assert(instructions[1].param == 2);

    assert(instructions[2].opcode == 6);
    assert(instructions[2].lex == 0);
    assert(instructions[2].param == 6);

    assert(instructions[3].opcode == 1);
    assert(instructions[3].lex == 0);
    assert(instructions[3].param == 13);

    assert(instructions[4].opcode == 4);
    assert(instructions[4].lex == 0);
    assert(instructions[4].param == 4);

    assert(instructions[5].opcode == 1);
    assert(instructions[5].lex == 0);
    assert(instructions[5].param == 1);

    assert(instructions[6].opcode == 4);
    assert(instructions[6].lex == 1);
    assert(instructions[6].param == 4);

    assert(instructions[7].opcode == 1);
    assert(instructions[7].lex == 0);
    assert(instructions[7].param == 7);

    assert(instructions[8].opcode == 4);
    assert(instructions[8].lex == 0);
    assert(instructions[8].param == 5);

    assert(instructions[9].opcode == 2);
    assert(instructions[9].lex == 0);
    assert(instructions[9].param == 0);

    assert(instructions[10].opcode == 6);
    assert(instructions[10].lex == 0);
    assert(instructions[10].param == 6);

    assert(instructions[11].opcode == 1);
    assert(instructions[11].lex == 0);
    assert(instructions[11].param == 3);

    assert(instructions[12].opcode == 4);
    assert(instructions[12].lex == 0);
    assert(instructions[12].param == 4);

    assert(instructions[13].opcode == 1);
    assert(instructions[13].lex == 0);
    assert(instructions[13].param == 0);

    assert(instructions[14].opcode == 4);
    assert(instructions[14].lex == 0);
    assert(instructions[14].param == 5);

    assert(instructions[15].opcode == 5);
    assert(instructions[15].lex == 0);
    assert(instructions[15].param == 2);

    assert(instructions[16].opcode == 11);
    assert(instructions[16].lex == 0);
    assert(instructions[16].param == 3);

    printf("READ_TEST PASSED\n");
}

void halt_test() {
    instruction* IR = malloc(1 * sizeof(instruction));
    IR->opcode = 11;
    IR->lex = 0;
    IR->param = 3;

    int halt = 0;
    execute(IR,0,&halt);

    assert( halt == 1);

    printf("HALT_TEST PASSED\n");
}

void jump_test() {
    instruction* IR = malloc(1 * sizeof(instruction));
    IR->opcode = 7;
    IR->lex = 0;
    IR->param = 10;

    int halt = 0;
    int PC = execute(IR,0,&halt);

    assert( PC == 10);
    assert( halt == 0);

    printf("JUMP_TEST PASSED\n");
}

int main() {
    read_test();
    halt_test();
    jump_test();
    return 0;
}
