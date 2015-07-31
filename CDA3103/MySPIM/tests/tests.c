#include "../src/project.c"
#include <assert.h>

static unsigned Mem[16384];

#define MEM(addr) (Mem[addr >> 2])

void setup() {
    Mem[4096] = 554172417;
    Mem[4097] = 554237954;
    Mem[4098] = 17387552;
    Mem[4099] = 2947153920;
    Mem[4100] = 2410348544;
    Mem[4101] = 23748650;
    Mem[4102] = 19556395;
    Mem[4103] = 1007550496;
    Mem[4104] = 134221835;
    Mem[4105] = 3735928495;
    Mem[4106] = 3134897839;
    Mem[4107] = 290193405;

    unsigned i;
    printf("DISPLAYING TEST MEM\n");
    for (i= 4096; i < 4108; i++) {
        printf("Mem[%x]: %x\n", i << 2, Mem[i]);
    }
    printf("----------------------------\n\n");
}

void instruction_fetch_test() {
    unsigned instruction;
    unsigned PC = 0x4000;
    int halt = 0;

    // Fetch first Instruction
    halt = instruction_fetch(PC, Mem, &instruction);
    assert(instruction == 554172417);
    assert(halt == 0);
    printf("PASS\t FETCH - INSTRUCTION at 0x%x is 0x%x\n", PC, instruction);

    // Fetch 5th Instruction
    PC = 0x4010;
    halt = instruction_fetch(PC, Mem, &instruction);
    assert(instruction == 2410348544);
    assert(halt == 0);
    printf("PASS\t FETCH - INSTRUCTION at 0x%x is 0x%x\n", PC, instruction);

    // Handle Unaligned Word
    PC = 0x4012;
    halt = instruction_fetch(PC, Mem, &instruction);
    assert(halt == 1);
    printf("PASS\t FETCH - PC ADDR 0x%x is not word aligned\n", PC);
}

void instruction_partition_test() {
    unsigned instruction;
    unsigned PC = 0x4000;
    int halt = 0;
    unsigned op, r1, r2, r3, funct, offset, jsec;

    halt = instruction_fetch(PC, Mem, &instruction);
    instruction_partition(instruction,&op,&r1,&r2,&r3,&funct,&offset,&jsec);

    assert( halt == 0 );
    assert( op == 0b1000 );
    assert( r1 == 0b1000 );
    assert( r2 == 0b1000 );
    assert( r3 == 0b0 );
    assert( funct == 0b1 );
    assert( offset == 0b1 );
    assert( jsec == 0b01000010000000000000000001);
    printf("PASS\t PARTITION - INSTRUCTION:0x%x"
            "\n\t\t op:0x%x \n\t\t r1:0x%x \n\t\t r2:0x%x"
            "\n\t\t r3:0x%x \n\t\t funct:0x%x"
            "\n\t\t offset:0x%x \n\t\t jsec:0x%x\n",
            instruction, op, r1, r2, r3, funct, offset, jsec);
}

void alu_test() {

    unsigned A;
    unsigned B;
    int As;
    int Bs;
    char ALUControl;
    unsigned ALUResult = 0;
    char Zero = '0';

    // When A + b
    A = 2, B = 1, ALUControl = '0';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 3 );
    assert( Zero == '0' );
    printf("PASS\t ALU - add %d, %d \n", A, B);

    // When A - B
    A = 2, B = 1, ALUControl = '1';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - sub %d, %d \n", A, B);

    // When A - B == Zero == 0
    A = 2, B = 2, ALUControl = '1';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 0);
    assert( Zero == '1' );
    printf("PASS\t ALU - sub %d, %d \n", A, B);

    // When (signed)A < (signed)B is false & Zero == 1
    As = 2, Bs = 1, ALUControl = '2';
    ALU(As, Bs, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 0 );
    assert( Zero == '1' );
    printf("PASS\t ALU - slt %d, %d \n", A, B);

    // When (signed)A < (signed)B is true & Zero == 0
    As = 1, Bs = 2, ALUControl = '2';
    ALU(As, Bs, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - slt %d, %d \n", A, B);

    // When (unsigned) A < (unsigned) B is true & Zero == 0
    A = 1, B = 2, ALUControl = '3';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - sltu %u, %u \n", A, B);

    // When (unsigned) A < (unsigned) B is true & Zero == 0
    A = 1, B = -2, ALUControl = '3';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - sltu %u, %u \n", A, B);

    // When A && B is true & Zero == 0
    A = 1, B = 1, ALUControl = '4';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - and %u, %u \n", A, B);

    // When A && B is true & Zero == 0
    A = 4, B = 1, ALUControl = '4';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - and %u, %u \n", A, B);

    // When A && B is false & Zero == 1
    A = 4, B = 0, ALUControl = '4';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 0 );
    assert( Zero == '1' );
    printf("PASS\t ALU - or %u, %u \n", A, B);

    // When A || B is true & Zero == 0
    A = 1, B = 1, ALUControl = '5';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - or %u, %u \n", A, B);

    // When A || B is true & Zero == 0
    A = 4, B = 1, ALUControl = '5';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - or %u, %u \n", A, B);

    // When A || B is false & Zero == 1
    A = 0, B = 0, ALUControl = '5';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 0 );
    assert( Zero == '1' );
    printf("PASS\t ALU - or %u, %u \n", A, B);

    // When B is left shifted by 16 bits & Zero == 0
    /*
    A = 0, B = 1, ALUControl = '6';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    printf("%d", B);
    assert( B != 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - sll %u, %u \n", A, B);
    */

    // When ~A is true & Zero == 0
    A = 0, B = 0, ALUControl = '7';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - not %u, %u \n", A, B);

    // When ~A is false & Zero == 0
    A = 1, B = 0, ALUControl = '7';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 0 );
    assert( Zero == '1' );
    printf("PASS\t ALU - not %u, %u \n", A, B);
}

int main() {

    setup();
    alu_test();
    instruction_fetch_test();
    instruction_partition_test();
    return 0;
}
