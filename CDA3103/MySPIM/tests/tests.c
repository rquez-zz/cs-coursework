#include "../src/project.c"
#include <assert.h>

static unsigned Mem[16384];

#define MEM(addr) (Mem[addr >> 2])

void setup() {
    Mem[0x1000] = 0x21080001;
    Mem[0x1004] = 0x21090002;
    Mem[0x1008] = 0x01095020;
    Mem[0x100C] = 0xafaa0000;
    Mem[0x1010] = 0x8fab0000;
    Mem[0x1014] = 0x016a602a;
    Mem[0x1018] = 0x012a682b;
    Mem[0x101C] = 0x3c0e0020;
    Mem[0x1020] = 0x0800100b;
    Mem[0x1024] = 0xdeadbeaf;
    Mem[0x1028] = 0xbadabeaf;
    Mem[0x102C] = 0x114bfffd;

    unsigned i;
    printf("DISPLAYING TEST MEM\n");
    for (i= 0x1000; i <= 0x102C; i = i+4) {
        printf("Mem[0x%04x]: 0x%08x\n", i, Mem[i]);
    }
    printf("----------------------------\n\n");
}

void instruction_fetch_test() {
    unsigned instruction;
    unsigned PC;
    int halt;

    // Fetch first Instruction
    PC = 0x4000;
    halt = instruction_fetch(PC, Mem, &instruction);
    assert(instruction == 554172417);
    assert(halt == 0);
    printf("PASS\t FETCH - INSTRUCTION at 0x%x is 0x%x\n", PC, instruction);

    // Fetch 5th Instruction
    PC = 0x4040;
    halt = instruction_fetch(PC, Mem, &instruction);
    assert(instruction == 2410348544);
    assert(halt == 0);
    printf("PASS\t FETCH - INSTRUCTION at 0x%x is 0x%x\n", PC, instruction);

    // Fetch 8th Instruction
    PC = 0x4070;
    halt = instruction_fetch(PC, Mem, &instruction);
    assert(instruction == 1007550496);
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

    // Parition First Instruction
    assert( halt == 0 );
    assert( op == 0b1000 );
    assert( r1 == 0b1000 );
    assert( r2 == 0b1000 );
    assert( r3 == 0b0 );
    assert( funct == 0b1 );
    assert( offset == 0b1 );
    assert( jsec == 0b01000010000000000000000001);
    printf("PASS\t PARTITION - INSTRUCTION:0x%x"
            "\n\t\t\top:0x%x\n\t\t\tr1:0x%x\n\t\t\tr2:0x%x"
            "\n\t\t\tr3:0x%x\n\t\t\tfunct:0x%x"
            "\n\t\t\toffset:0x%x\n\t\t\tjsec:0x%x\n",
            instruction, op, r1, r2, r3, funct, offset, jsec);
}

void instruction_decode_test() {
    struct_controls controls;
    unsigned op, r1, r2, r3, funct, offset, jsec, PC, instruction;
    int halt;

    // Decode First OPCODE addi
    PC = 0x4000;
    halt = instruction_fetch(PC, Mem, &instruction);
    instruction_partition(instruction,&op,&r1,&r2,&r3,&funct,&offset,&jsec);
    instruction_decode(op, &controls);
    assert( controls.RegDst == 0 );
    assert( controls.Jump == 0 );
    assert( controls.Branch == 0 );
    assert( controls.MemRead == 0 );
    assert( controls.MemtoReg == 0 );
    assert( controls.ALUOp == 0 );
    assert( controls.MemWrite == 0 );
    assert( controls.ALUSrc == 1 );
    assert( controls.RegWrite == 1 );
    printf("PASS\t DECODE - OPCODE:0x%x\n\t\t\t"
            "RegDst:0x%x\n\t\t\tJump:0x%x\n\t\t\t"
            "Branch:0x%x\n\t\t\tMemRead:0x%x\n\t\t\t"
            "MemtoReg:0x%x\n\t\t\tALUOp:0x%x\n\t\t\t"
            "MemWrite:0x%x\n\t\t\tALUSrc:0x%x\n\t\t\t"
            "RegWrite:0x%x\n",
            op, controls.RegDst, controls.Jump, controls.Branch,
            controls.MemRead, controls.MemtoReg, controls.ALUOp,
            controls.MemWrite, controls.ALUSrc, controls.RegWrite);

    // Decode third OPCODE add
    PC = 0x4020;
    halt = instruction_fetch(PC, Mem, &instruction);
    instruction_partition(instruction,&op,&r1,&r2,&r3,&funct,&offset,&jsec);
    instruction_decode(op, &controls);
    assert( controls.RegDst == 1 );
    assert( controls.Jump == 0 );
    assert( controls.Branch == 0 );
    assert( controls.MemRead == 0 );
    assert( controls.MemtoReg == 0 );
    assert( controls.ALUOp == 7 );
    assert( controls.MemWrite == 0 );
    assert( controls.ALUSrc == 0 );
    assert( controls.RegWrite == 1 );
    printf("PASS\t DECODE - OPCODE:0x%x\n\t\t\t"
            "RegDst:0x%x\n\t\t\tJump:0x%x\n\t\t\t"
            "Branch:0x%x\n\t\t\tMemRead:0x%x\n\t\t\t"
            "MemtoReg:0x%x\n\t\t\tALUOp:0x%x\n\t\t\t"
            "MemWrite:0x%x\n\t\t\tALUSrc:0x%x\n\t\t\t"
            "RegWrite:0x%x\n",
            op, controls.RegDst, controls.Jump, controls.Branch,
            controls.MemRead, controls.MemtoReg, controls.ALUOp,
            controls.MemWrite, controls.ALUSrc, controls.RegWrite);

    // Decode fourth OPCODE sw
    PC = 0x4030;
    halt = instruction_fetch(PC, Mem, &instruction);
    instruction_partition(instruction,&op,&r1,&r2,&r3,&funct,&offset,&jsec);
    instruction_decode(op, &controls);
    assert( controls.RegDst == 2 );
    assert( controls.Jump == 0 );
    assert( controls.Branch == 0 );
    assert( controls.MemRead == 0 );
    assert( controls.MemtoReg == 2 );
    assert( controls.ALUOp == 0 );
    assert( controls.MemWrite == 1 );
    assert( controls.ALUSrc == 1 );
    assert( controls.RegWrite == 0 );
    printf("PASS\t DECODE - OPCODE:0x%x\n\t\t\t"
            "RegDst:0x%x\n\t\t\tJump:0x%x\n\t\t\t"
            "Branch:0x%x\n\t\t\tMemRead:0x%x\n\t\t\t"
            "MemtoReg:0x%x\n\t\t\tALUOp:0x%x\n\t\t\t"
            "MemWrite:0x%x\n\t\t\tALUSrc:0x%x\n\t\t\t"
            "RegWrite:0x%x\n",
            op, controls.RegDst, controls.Jump, controls.Branch,
            controls.MemRead, controls.MemtoReg, controls.ALUOp,
            controls.MemWrite, controls.ALUSrc, controls.RegWrite);

    // Decode fifth OPCODE lw
    PC = 0x4040;
    halt = instruction_fetch(PC, Mem, &instruction);
    instruction_partition(instruction,&op,&r1,&r2,&r3,&funct,&offset,&jsec);
    instruction_decode(op, &controls);
    assert( controls.RegDst == 0 );
    assert( controls.Jump == 0 );
    assert( controls.Branch == 0 );
    assert( controls.MemRead == 1 );
    assert( controls.MemtoReg == 1 );
    assert( controls.ALUOp == 0 );
    assert( controls.MemWrite == 0 );
    assert( controls.ALUSrc == 1 );
    assert( controls.RegWrite == 1 );
    printf("PASS\t DECODE - OPCODE:0x%x\n\t\t\t"
            "RegDst:0x%x\n\t\t\tJump:0x%x\n\t\t\t"
            "Branch:0x%x\n\t\t\tMemRead:0x%x\n\t\t\t"
            "MemtoReg:0x%x\n\t\t\tALUOp:0x%x\n\t\t\t"
            "MemWrite:0x%x\n\t\t\tALUSrc:0x%x\n\t\t\t"
            "RegWrite:0x%x\n",
            op, controls.RegDst, controls.Jump, controls.Branch,
            controls.MemRead, controls.MemtoReg, controls.ALUOp,
            controls.MemWrite, controls.ALUSrc, controls.RegWrite);

    // Decode sixth OPCODE slt
    PC = 0x4050;
    halt = instruction_fetch(PC, Mem, &instruction);
    instruction_partition(instruction,&op,&r1,&r2,&r3,&funct,&offset,&jsec);
    instruction_decode(op, &controls);
    assert( controls.RegDst == 1 );
    assert( controls.Jump == 0 );
    assert( controls.Branch == 0 );
    assert( controls.MemRead == 0 );
    assert( controls.MemtoReg == 0 );
    assert( controls.ALUOp == 7 );
    assert( controls.MemWrite == 0 );
    assert( controls.ALUSrc == 0 );
    assert( controls.RegWrite == 1 );
    printf("PASS\t DECODE - OPCODE:0x%x\n\t\t\t"
            "RegDst:0x%x\n\t\t\tJump:0x%x\n\t\t\t"
            "Branch:0x%x\n\t\t\tMemRead:0x%x\n\t\t\t"
            "MemtoReg:0x%x\n\t\t\tALUOp:0x%x\n\t\t\t"
            "MemWrite:0x%x\n\t\t\tALUSrc:0x%x\n\t\t\t"
            "RegWrite:0x%x\n",
            op, controls.RegDst, controls.Jump, controls.Branch,
            controls.MemRead, controls.MemtoReg, controls.ALUOp,
            controls.MemWrite, controls.ALUSrc, controls.RegWrite);

    // Decode seventh OPCODE sltu
    PC = 0x4060;
    halt = instruction_fetch(PC, Mem, &instruction);
    instruction_partition(instruction,&op,&r1,&r2,&r3,&funct,&offset,&jsec);
    instruction_decode(op, &controls);
    assert( controls.RegDst == 1 );
    assert( controls.Jump == 0 );
    assert( controls.Branch == 0 );
    assert( controls.MemRead == 0 );
    assert( controls.MemtoReg == 0 );
    assert( controls.ALUOp == 7 );
    assert( controls.MemWrite == 0 );
    assert( controls.ALUSrc == 0 );
    assert( controls.RegWrite == 1 );
    printf("PASS\t DECODE - OPCODE:0x%x\n\t\t\t"
            "RegDst:0x%x\n\t\t\tJump:0x%x\n\t\t\t"
            "Branch:0x%x\n\t\t\tMemRead:0x%x\n\t\t\t"
            "MemtoReg:0x%x\n\t\t\tALUOp:0x%x\n\t\t\t"
            "MemWrite:0x%x\n\t\t\tALUSrc:0x%x\n\t\t\t"
            "RegWrite:0x%x\n",
            op, controls.RegDst, controls.Jump, controls.Branch,
            controls.MemRead, controls.MemtoReg, controls.ALUOp,
            controls.MemWrite, controls.ALUSrc, controls.RegWrite);

    // Decode eigth OPCODE lui
    PC = 0x4070;
    halt = instruction_fetch(PC, Mem, &instruction);
    instruction_partition(instruction,&op,&r1,&r2,&r3,&funct,&offset,&jsec);
    instruction_decode(op, &controls);
    assert( controls.RegDst == 0 );
    assert( controls.Jump == 0 );
    assert( controls.Branch == 0 );
    assert( controls.MemRead == 0 );
    assert( controls.MemtoReg == 0 );
    assert( controls.ALUOp == 6 );
    assert( controls.MemWrite == 0 );
    assert( controls.ALUSrc == 1 );
    assert( controls.RegWrite == 1 );
    printf("PASS\t DECODE - OPCODE:0x%x\n\t\t\t"
            "RegDst:0x%x\n\t\t\tJump:0x%x\n\t\t\t"
            "Branch:0x%x\n\t\t\tMemRead:0x%x\n\t\t\t"
            "MemtoReg:0x%x\n\t\t\tALUOp:0x%x\n\t\t\t"
            "MemWrite:0x%x\n\t\t\tALUSrc:0x%x\n\t\t\t"
            "RegWrite:0x%x\n",
            op, controls.RegDst, controls.Jump, controls.Branch,
            controls.MemRead, controls.MemtoReg, controls.ALUOp,
            controls.MemWrite, controls.ALUSrc, controls.RegWrite);

    // Decode ninth OPCODE j
    PC = 0x4080;
    halt = instruction_fetch(PC, Mem, &instruction);
    instruction_partition(instruction,&op,&r1,&r2,&r3,&funct,&offset,&jsec);
    instruction_decode(op, &controls);
    assert( controls.RegDst == 0 );
    assert( controls.Jump == 1 );
    assert( controls.Branch == 0 );
    assert( controls.MemRead == 0 );
    assert( controls.MemtoReg == 0 );
    assert( controls.ALUOp == 0 );
    assert( controls.MemWrite == 0 );
    assert( controls.ALUSrc == 0 );
    assert( controls.RegWrite == 0 );
    printf("PASS\t DECODE - OPCODE:0x%x\n\t\t\t"
            "RegDst:0x%x\n\t\t\tJump:0x%x\n\t\t\t"
            "Branch:0x%x\n\t\t\tMemRead:0x%x\n\t\t\t"
            "MemtoReg:0x%x\n\t\t\tALUOp:0x%x\n\t\t\t"
            "MemWrite:0x%x\n\t\t\tALUSrc:0x%x\n\t\t\t"
            "RegWrite:0x%x\n",
            op, controls.RegDst, controls.Jump, controls.Branch,
            controls.MemRead, controls.MemtoReg, controls.ALUOp,
            controls.MemWrite, controls.ALUSrc, controls.RegWrite);

    // Decode tenth OPCODE
    PC = 0x4090;
    halt = instruction_fetch(PC, Mem, &instruction);
    instruction_partition(instruction,&op,&r1,&r2,&r3,&funct,&offset,&jsec);
    halt = instruction_decode(op, &controls);
    assert(halt == 1);
    printf("PASS\t DECODE - BAD OPCODE:0x%x\n", op);

    // Decode eleventh OPCODE
    PC = 0x40A0;
    halt = instruction_fetch(PC, Mem, &instruction);
    instruction_partition(instruction,&op,&r1,&r2,&r3,&funct,&offset,&jsec);
    halt = instruction_decode(op, &controls);
    assert(halt == 1);
    printf("PASS\t DECODE - BAD OPCODE:0x%x\n", op);

    // Decode last OPCODE beq
    PC = 0x40B0;
    halt = instruction_fetch(PC, Mem, &instruction);
    instruction_partition(instruction,&op,&r1,&r2,&r3,&funct,&offset,&jsec);
    instruction_decode(op, &controls);
    assert( controls.RegDst == 2 );
    assert( controls.Jump == 0 );
    assert( controls.Branch == 1 );
    assert( controls.MemRead == 0 );
    assert( controls.MemtoReg == 2 );
    assert( controls.ALUOp == 1 );
    assert( controls.MemWrite == 0 );
    assert( controls.ALUSrc == 0 );
    assert( controls.RegWrite == 0 );
    printf("PASS\t DECODE - OPCODE:0x%x\n\t\t\t"
            "RegDst:0x%x\n\t\t\tJump:0x%x\n\t\t\t"
            "Branch:0x%x\n\t\t\tMemRead:0x%x\n\t\t\t"
            "MemtoReg:0x%x\n\t\t\tALUOp:0x%x\n\t\t\t"
            "MemWrite:0x%x\n\t\t\tALUSrc:0x%x\n\t\t\t"
            "RegWrite:0x%x\n",
            op, controls.RegDst, controls.Jump, controls.Branch,
            controls.MemRead, controls.MemtoReg, controls.ALUOp,
            controls.MemWrite, controls.ALUSrc, controls.RegWrite);
}

void read_register_test() {

    unsigned Reg[32 + 4];
	memset(Reg, 0, (32 + 4) * sizeof(unsigned));
    struct_controls controls;
    unsigned op,r1,r2,r3,funct,offset,jsec,PC,instruction,data1,data2;
    int halt;

    PC = 0x4000;
    halt = instruction_fetch(PC, Mem, &instruction);
    instruction_partition(instruction,&op,&r1,&r2,&r3,&funct,&offset,&jsec);
    halt = instruction_decode(op, &controls);
    read_register(r1, r2, Reg, &data1, &data2);
    assert( halt == 0);
    assert( data1 == 0);
    assert( data2 == 0);
    printf("PASS\t READ REGISTER - READ IN EMPTY REGISTER AT 0x%x and 0x%x\n"
            , r1, r2);

    //TODO: WRITE TO REGISTER AND THEN READ IT
}

void sign_extend_test() {

    unsigned extended_value;
    sign_extend(0b0100000000001010, &extended_value);
    assert( extended_value == 0x0000400A);
    printf("PASS\t SIGN EXTEND - 0x400A (16394) EXTENDED TO 0x0000400A\n");

    sign_extend(0b1111111111110110, &extended_value);
    assert( extended_value == 0xfffffff6);
    printf("PASS\t SIGN EXTEND - 0xFFF6 (-10) EXTENDED TO 0xFFFFFFF6A\n");
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
    instruction_decode_test();
    read_register_test();
    sign_extend_test();
    return 0;
}
