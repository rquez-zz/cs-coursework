#include "../src/stack.c"
#include <assert.h>
#include <string.h>

void read_test() {

    const char* inputPath = "../input/mcode.txt";
    FILE* filePtr = openFile(inputPath);
    instruction instructions[MAX_CODE_LENGTH];
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

void execute_test() {

}

int main() {
    read_test();
    return 0;
}
