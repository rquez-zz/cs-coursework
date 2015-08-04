#include "../src/project.c"
#include <assert.h>

static unsigned Mem[16384];
unsigned Reg[32 + 4];
struct_controls controls;

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

	memset(Reg, 0, (32 + 4) * sizeof(unsigned));
    Reg[0x1C] = 0xC000;
    Reg[0x1D] = 0xFFFC;
    Reg[0x20] = 0x4000;
}

void setupFull() {
    Mem[0x1000] = 0x21080001;
    Mem[0x1001] = 0x21090002;
    Mem[0x1002] = 0x01095020;
    Mem[0x1003] = 0xafaa0000;
    Mem[0x1004] = 0x8fab0000;
    Mem[0x1005] = 0x016a602a;
    Mem[0x1006] = 0x012a682b;
    Mem[0x1007] = 0x3c0e0020;
    Mem[0x1008] = 0x0800100b;
    Mem[0x1009] = 0xdeadbeaf;
    Mem[0x100A] = 0xbadabeaf;
    Mem[0x100B] = 0x114bfffd;

    unsigned i;
    printf("DISPLAYING TEST MEM\n");
    for (i= 0x1000; i <= 0x100B; i++) {
        printf("Mem[0x%04x]: 0x%08x\n", i, Mem[i]);
    }
    printf("----------------------------\n\n");

	memset(Reg, 0, (32 + 4) * sizeof(unsigned));
    Reg[0x1C] = 0xC000;
    Reg[0x1D] = 0xFFFC;
    Reg[0x20] = 0x4000;
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
    int halt;
    unsigned op, r1, r2, r3, funct, offset, jsec, PC, instruction;

    // Parition First Instruction
    PC = 0x4000;
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
            "\n\t\t\top:0x%x\n\t\t\tr1:0x%x\n\t\t\tr2:0x%x"
            "\n\t\t\tr3:0x%x\n\t\t\tfunct:0x%x"
            "\n\t\t\toffset:0x%x\n\t\t\tjsec:0x%x\n",
            instruction, op, r1, r2, r3, funct, offset, jsec);


    // Parition Third Instruction add
    PC = 0x4020;
    halt = instruction_fetch(PC, Mem, &instruction);
    instruction_partition(instruction,&op,&r1,&r2,&r3,&funct,&offset,&jsec);
    assert( halt == 0 );
    assert( op == 0x0 );
    assert( r1 == 0x0008 );
    assert( r2 == 0x0009 );
    assert( r3 == 0x000a );
    assert( funct == 0x0020 );
    printf("PASS\t PARTITION - INSTRUCTION:0x%x"
            "\n\t\t\top:0x%x\n\t\t\tr1:0x%x\n\t\t\tr2:0x%x"
            "\n\t\t\tr3:0x%x\n\t\t\tfunct:0x%x"
            "\n\t\t\toffset:0x%x\n\t\t\tjsec:0x%x\n",
            instruction, op, r1, r2, r3, funct, offset, jsec);

    // Parition fourth Instruction sw
    PC = 0x4030;
    halt = instruction_fetch(PC, Mem, &instruction);
    instruction_partition(instruction,&op,&r1,&r2,&r3,&funct,&offset,&jsec);
    assert( halt == 0 );
    assert( op == 0x2B );
    assert( r1 == 0x1D );
    assert( r2 == 0xA );
    assert( offset == 0x0 );
    printf("PASS\t PARTITION - INSTRUCTION:0x%x"
            "\n\t\t\top:0x%x\n\t\t\tr1:0x%x\n\t\t\tr2:0x%x"
            "\n\t\t\tr3:0x%x\n\t\t\tfunct:0x%x"
            "\n\t\t\toffset:0x%x\n\t\t\tjsec:0x%x\n",
            instruction, op, r1, r2, r3, funct, offset, jsec);

    // Parition Sixth Instruction slt
    PC = 0x4050;
    halt = instruction_fetch(PC, Mem, &instruction);
    instruction_partition(instruction,&op,&r1,&r2,&r3,&funct,&offset,&jsec);
    assert( halt == 0 );
    assert( op == 0x0 );
    assert( funct == 0x002a );
    assert( r1 == 0x000b );
    assert( r2 == 0x000a );
    assert( r3 == 0x000c );
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

void full_test() {

    unsigned op,r1,r2,r3,funct,offset,jsec,PC,data1,data2,instruction = 0,
             extended_value,ALUresult,halt, memdata;
    char Zero;
    PC = 0x4000;

    printf("\n***START FULL TEST!***\n");

    // First instruction - Itype
    halt = instruction_fetch(PC, Mem, &instruction);
    instruction_partition(instruction, &op, &r1, &r2, &r3, &funct, &offset,
            &jsec);
    halt = instruction_decode(op, &controls);
    read_register(r1, r2, Reg, &data1, &data2);
    sign_extend(offset, &extended_value);
    halt = ALU_operations(data1, data2, extended_value, funct, controls.ALUOp,
            controls.ALUSrc, &ALUresult, &Zero);
    assert( ALUresult == 0x00000001 );
    assert( Zero == 0 );
    printf("PASS\t addi $8, $8, 1 - ALU OPERATION - ITYPE OPCODE:0x%x DATA1:%u IMM:%u RESULT:%u\n",
            op, data1, extended_value, ALUresult);
    assert( halt == 0 );
    halt = rw_memory(ALUresult, data2, controls.MemWrite, controls.MemRead,
            &memdata, Mem);
    assert( halt == 0 );
    write_register(r2, r3, memdata, ALUresult, controls.RegWrite,
            controls.RegDst, controls.MemtoReg, Reg);
    assert( Reg[r2] == 0x00000001 );
    printf("PASS\t addi $8, $8, 1 - WRITE REG - ITYPE RT:0x%x RESULT:%u\n", r2, ALUresult);
    PC_update(jsec,extended_value,controls.Branch,controls.Jump,Zero,&PC);

    // Second instruction - Itype
    halt = instruction_fetch(PC, Mem, &instruction);
    instruction_partition(instruction, &op, &r1, &r2, &r3, &funct, &offset,
            &jsec);
    halt = instruction_decode(op, &controls);
    read_register(r1, r2, Reg, &data1, &data2);
    sign_extend(offset, &extended_value);
    halt = ALU_operations(data1, data2, extended_value, funct, controls.ALUOp,
            controls.ALUSrc, &ALUresult, &Zero);
    assert( ALUresult == 0x00000003 );
    assert( Zero == 0 );
    printf("PASS\t addi $9, $8, 2 - ALU OPERATION - ITYPE OPCODE:0x%x DATA1:%u IMM:%u RESULT:%u\n",
            op, data1, extended_value, ALUresult);
    assert( halt == 0 );
    halt = rw_memory(ALUresult, data2, controls.MemWrite, controls.MemRead,
            &memdata, Mem);
    assert( halt == 0 );
    write_register(r2, r3, memdata, ALUresult, controls.RegWrite,
            controls.RegDst, controls.MemtoReg, Reg);
    assert( Reg[r2] == 0x00000003 );
    printf("PASS\t addi $9, $8, 2 - WRITE REG - ITYPE RT:0x%x REG[0x%x]:%u MEMDATA:%u ALURESULT:%u\n",
            r2, r2, Reg[r2], memdata, ALUresult);
    PC_update(jsec,extended_value,controls.Branch,controls.Jump,Zero,&PC);

    // Third instruction - rtype
    halt = instruction_fetch(PC, Mem, &instruction);
    assert( halt == 0 );
    instruction_partition(instruction, &op, &r1, &r2, &r3, &funct, &offset,
            &jsec);
    halt = instruction_decode(op, &controls);
    assert( halt == 0 );
    read_register(r1, r2, Reg, &data1, &data2);
    sign_extend(offset, &extended_value);
    halt = ALU_operations(data1, data2, extended_value, funct, controls.ALUOp,
            controls.ALUSrc, &ALUresult, &Zero);
    assert( ALUresult == 0x00000004 );
    assert( Zero == 0 );
    printf("PASS\t add $10, $9, $8 - ALU OPERATION - RTYPE FUNC:%u DATA1:%u DATA2:%u RESULT:%u\n",
            funct, data1, data2, ALUresult);
    halt = rw_memory(ALUresult, data2, controls.MemWrite, controls.MemRead,
            &memdata, Mem);
    assert( halt == 0 );
    write_register(r2, r3, memdata, ALUresult, controls.RegWrite,
            controls.RegDst, controls.MemtoReg, Reg);
    assert( Reg[r3] == 0x00000004 );
    printf("PASS\t add $10, $9, $8 - WRITE REG - RTYPE RT:0x%x RD:0x%x RESULT:%u\n",
            r2, r3, ALUresult);
    PC_update(jsec,extended_value,controls.Branch,controls.Jump,Zero,&PC);

    // Fourth instruction - itype sw
    halt = instruction_fetch(PC, Mem, &instruction);
    assert( halt == 0 );
    instruction_partition(instruction, &op, &r1, &r2, &r3, &funct, &offset,
            &jsec);
    halt = instruction_decode(op, &controls);
    assert( halt == 0 );
    read_register(r1, r2, Reg, &data1, &data2);
    sign_extend(offset, &extended_value);
    halt = ALU_operations(data1, data2, extended_value, funct, controls.ALUOp,
            controls.ALUSrc, &ALUresult, &Zero);
    assert( Zero == 0 );
    assert( halt == 0 );
    printf("PASS\t sw $10, 0(29) - ALU OPERATION - ITYPE OPCODE:0x%x DATA1:%u IMM:%u RESULT:%u\n",
            op, data1, extended_value, ALUresult);
    halt = rw_memory(ALUresult, data2, controls.MemWrite, controls.MemRead,
            &memdata, Mem);
    assert( Mem[ALUresult >> 2] == 0x00000004 );
    assert( halt == 0 );
    printf("PASS\t sw $10, 0(29) - RW MEMORY- ITYPE MEMWRITE:%u MEMREAD:%u MEMDATA:%u ADDR:0x%x MEM[addr]:%u\n", 
            controls.MemWrite, controls.MemRead, memdata, ALUresult >> 2, Mem[ALUresult >> 2]);
    write_register(r2, r3, memdata, ALUresult, controls.RegWrite,
            controls.RegDst, controls.MemtoReg, Reg);
    PC_update(jsec,extended_value,controls.Branch,controls.Jump,Zero,&PC);

    // Fifth instruction - itype lw
    halt = instruction_fetch(PC, Mem, &instruction);
    assert( halt == 0 );
    instruction_partition(instruction, &op, &r1, &r2, &r3, &funct, &offset,
            &jsec);
    halt = instruction_decode(op, &controls);
    assert( halt == 0 );
    read_register(r1, r2, Reg, &data1, &data2);
    sign_extend(offset, &extended_value);
    halt = ALU_operations(data1, data2, extended_value, funct, controls.ALUOp,
            controls.ALUSrc, &ALUresult, &Zero);
    assert( Zero == 0 );
    printf("PASS\t lw $11 0(29) - ALU OPERATION - ITYPE OPCODE:0x%x DATA1:%u IMM:%u RESULT:%u\n",
            op, data1, extended_value, ALUresult);
    assert( halt == 0 );
    halt = rw_memory(ALUresult, data2, controls.MemWrite, controls.MemRead,
            &memdata, Mem);
    assert( memdata == 0x00000004 );
    assert( halt == 0 );
    printf("PASS\t lw $11 0(29) - RW MEMORY- ITYPE MEMWRITE:%u MEMREAD:%u MEMDATA:%u ADDR:0x%x MEM[addr]:%u\n",
            controls.MemWrite, controls.MemRead, memdata, ALUresult >> 2, Mem[ALUresult >> 2]);
    write_register(r2, r3, memdata, ALUresult, controls.RegWrite,
            controls.RegDst, controls.MemtoReg, Reg);
    assert( Reg[r2] == 0x00000004 );
    printf("PASS\t lw $11 0(29) - WRITE REG - ITYPE RT:0x%x REG[0x%x]:%u MEMDATA:%u ALURESULT:%u\n",
            r2, r2, Reg[r2], memdata, ALUresult);
    PC_update(jsec,extended_value,controls.Branch,controls.Jump,Zero,&PC);

    // Sixth instruction - rtype slt
    halt = instruction_fetch(PC, Mem, &instruction);
    assert( halt == 0 );
    instruction_partition(instruction, &op, &r1, &r2, &r3, &funct, &offset,
            &jsec);
    halt = instruction_decode(op, &controls);
    assert( halt == 0 );
    read_register(r1, r2, Reg, &data1, &data2);
    sign_extend(offset, &extended_value);
    halt = ALU_operations(data1, data2, extended_value, funct, controls.ALUOp,
            controls.ALUSrc, &ALUresult, &Zero);
    assert( ALUresult == 0x00000000 );
    assert( Zero == 1 );
    printf("PASS\t slt $12, $11, $10 - ALU OPERATION - RTYPE FUNC:%u DATA1:%u DATA2:%u RESULT:%u\n",
            funct, data1, data2, ALUresult);
    halt = rw_memory(ALUresult, data2, controls.MemWrite, controls.MemRead,
            &memdata, Mem);
    assert( halt == 0 );
    write_register(r2, r3, memdata, ALUresult, controls.RegWrite,
            controls.RegDst, controls.MemtoReg, Reg);
    assert( Reg[r3] == 0x00000000 );
    printf("PASS\t slt $12, $11, $10 - WRITE REG - RTYPE RT:0x%x RD:0x%x RESULT:%u\n",
            r2, r3, ALUresult);
    PC_update(jsec,extended_value,controls.Branch,controls.Jump,Zero,&PC);

    // Seventh instruction - rtype sltu
    halt = instruction_fetch(PC, Mem, &instruction);
    assert( halt == 0 );
    instruction_partition(instruction, &op, &r1, &r2, &r3, &funct, &offset,
            &jsec);
    halt = instruction_decode(op, &controls);
    assert( halt == 0 );
    read_register(r1, r2, Reg, &data1, &data2);
    sign_extend(offset, &extended_value);
    halt = ALU_operations(data1, data2, extended_value, funct, controls.ALUOp,
            controls.ALUSrc, &ALUresult, &Zero);
    assert( ALUresult == 0x00000001 );
    assert( Zero == 0 );
    printf("PASS\t sltu $13, $10, $9 - ALU OPERATION - RTYPE FUNC:%u DATA1:%u DATA2:%u RESULT:%u\n",
            funct, data1, data2, ALUresult);
    halt = rw_memory(ALUresult, data2, controls.MemWrite, controls.MemRead,
            &memdata, Mem);
    assert( halt == 0 );
    write_register(r2, r3, memdata, ALUresult, controls.RegWrite,
            controls.RegDst, controls.MemtoReg, Reg);
    assert( Reg[r3] == 0x00000001 );
    printf("PASS\t sltu $13, $10, $9 - WRITE REG - RTYPE RT:0x%x RD:0x%x RESULT:%u\n",
            r2, r3, ALUresult);
    PC_update(jsec,extended_value,controls.Branch,controls.Jump,Zero,&PC);

    // eigth instruction - itype lui
    halt = instruction_fetch(PC, Mem, &instruction);
    assert( halt == 0 );
    instruction_partition(instruction, &op, &r1, &r2, &r3, &funct, &offset,
            &jsec);
    halt = instruction_decode(op, &controls);
    assert( halt == 0 );
    read_register(r1, r2, Reg, &data1, &data2);
    sign_extend(offset, &extended_value);
    assert( extended_value == 0x0020 );
    halt = ALU_operations(data1, data2, extended_value, funct, controls.ALUOp,
            controls.ALUSrc, &ALUresult, &Zero);
    assert( Zero == 0 );
    assert( ALUresult == 0x00200000 );
    printf("PASS\t lui $14, 32 - ALU OPERATION - ITYPE OPCODE:0x%x DATA1:%u IMM:%u RESULT:0x%x\n",
            op, data1, extended_value, ALUresult);
    assert( halt == 0 );
    halt = rw_memory(ALUresult, data2, controls.MemWrite, controls.MemRead,
            &memdata, Mem);
    assert( halt == 0 );
    write_register(r2, r3, memdata, ALUresult, controls.RegWrite,
            controls.RegDst, controls.MemtoReg, Reg);
    assert( Reg[r2] == 0x00200000 );
    printf("PASS\t lui $14, 32 - WRITE REG - ITYPE RT:0x%x REG[0x%x]:%u MEMDATA:%u ALURESULT:0x%x\n",
            r2, r2, Reg[r2], memdata, ALUresult);
    PC_update(jsec,extended_value,controls.Branch,controls.Jump,Zero,&PC);

    // ninth instruction - jtype j
    halt = instruction_fetch(PC, Mem, &instruction);
    assert( halt == 0 );
    instruction_partition(instruction, &op, &r1, &r2, &r3, &funct, &offset,
            &jsec);
    halt = instruction_decode(op, &controls);
    assert( halt == 0 );
    read_register(r1, r2, Reg, &data1, &data2);
    sign_extend(offset, &extended_value);
    halt = ALU_operations(data1, data2, extended_value, funct, controls.ALUOp,
            controls.ALUSrc, &ALUresult, &Zero);
    assert( Zero == 1 );
    printf("PASS\t j end - ALU OPERATION - JTYPE OPCODE:0x%x ADDR:0x%x RESULT:%u\n",
            op, jsec, ALUresult);
    assert( halt == 0 );
    halt = rw_memory(ALUresult, data2, controls.MemWrite, controls.MemRead,
            &memdata, Mem);
    assert( halt == 0 );
    write_register(r2, r3, memdata, ALUresult, controls.RegWrite,
            controls.RegDst, controls.MemtoReg, Reg);
    PC_update(jsec,extended_value,controls.Branch,controls.Jump,Zero,&PC);

    // tenth instruction - itype beq
    halt = instruction_fetch(PC, Mem, &instruction);
    assert( halt == 0 );
    instruction_partition(instruction, &op, &r1, &r2, &r3, &funct, &offset,
            &jsec);
    halt = instruction_decode(op, &controls);
    assert( halt == 0 );
    read_register(r1, r2, Reg, &data1, &data2);
    sign_extend(offset, &extended_value);
    halt = ALU_operations(data1, data2, extended_value, funct, controls.ALUOp,
            controls.ALUSrc, &ALUresult, &Zero);
    assert( Zero == 1 );
    printf("PASS\t beq $10, $11, label - ALU OPERATION - ITYPE OPCODE:0x%x DATA1:%u IMM:%u RESULT:%u\n",
            op, data1, extended_value, ALUresult);
    assert( halt == 0 );
    halt = rw_memory(ALUresult, data2, controls.MemWrite, controls.MemRead,
            &memdata, Mem);
    assert( halt == 0 );
    write_register(r2, r3, memdata, ALUresult, controls.RegWrite,
            controls.RegDst, controls.MemtoReg, Reg);
    PC_update(jsec,extended_value,controls.Branch,controls.Jump,Zero,&PC);

    // eleventh instruction - HALT
    halt = instruction_fetch(PC, Mem, &instruction);
    assert( halt == 0 );
    instruction_partition(instruction, &op, &r1, &r2, &r3, &funct, &offset,
            &jsec);
    halt = instruction_decode(op, &controls);
    assert( halt == 1 );
    printf("PASS\t bad instr REACHED, HALTED\n");
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
    A = 2, B = 1, ALUControl = 0;
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 3 );
    assert( Zero == 0 );
    printf("PASS\t ALU - add %d, %d \n", A, B);

    // When A - B
    A = 2, B = 1, ALUControl = 1;
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == 0 );
    printf("PASS\t ALU - sub %d, %d \n", A, B);

    // When A - B == Zero == 0
    A = 2, B = 2, ALUControl = 1;
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 0);
    assert( Zero == 1 );
    printf("PASS\t ALU - sub %d, %d \n", A, B);

    // When (signed)A < (signed)B is false & Zero == 1
    As = 2, Bs = 1, ALUControl = 2;
    ALU(As, Bs, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 0 );
    assert( Zero == 1 );
    printf("PASS\t ALU - slt %d, %d \n", A, B);

    // When (signed)A < (signed)B is true & Zero == 0
    As = 1, Bs = 2, ALUControl = 2;
    ALU(As, Bs, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == 0 );
    printf("PASS\t ALU - slt %d, %d \n", A, B);

    // When (unsigned) A < (unsigned) B is true & Zero == 0
    A = 1, B = 2, ALUControl = 3;
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == 0 );
    printf("PASS\t ALU - sltu %u, %u \n", A, B);

    // When (unsigned) A < (unsigned) B is true & Zero == 0
    A = 1, B = -2, ALUControl = 3;
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == 0 );
    printf("PASS\t ALU - sltu %u, %u \n", A, B);

    // When A && B is true & Zero == 0
    A = 1, B = 1, ALUControl = 4;
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == 0 );
    printf("PASS\t ALU - and %u, %u \n", A, B);

    // When A && B is true & Zero == 0
    A = 4, B = 1, ALUControl = 4;
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == 0 );
    printf("PASS\t ALU - and %u, %u \n", A, B);

    // When A && B is false & Zero == 1
    A = 4, B = 0, ALUControl = 4;
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 0 );
    assert( Zero == 1 );
    printf("PASS\t ALU - or %u, %u \n", A, B);

    // When A || B is true & Zero == 0
    A = 1, B = 1, ALUControl = 5;
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == 0 );
    printf("PASS\t ALU - or %u, %u \n", A, B);

    // When A || B is true & Zero == 0
    A = 4, B = 1, ALUControl = 5;
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == 0 );
    printf("PASS\t ALU - or %u, %u \n", A, B);

    // When A || B is false & Zero == 1
    A = 0, B = 0, ALUControl = 5;
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 0 );
    assert( Zero == 1 );
    printf("PASS\t ALU - or %u, %u \n", A, B);

    // When B is left shifted by 16 bits & Zero == 0
    A = 0, B = 1, ALUControl = 6;
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 65536 );
    printf("PASS\t ALU - sll %u, %u \n", A, B);

    // When ~A is true & Zero == 0
    A = 0, B = 0, ALUControl = 7;
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == 0 );
    printf("PASS\t ALU - not %u, %u \n", A, B);

    // When ~A is false & Zero == 0
    A = 1, B = 0, ALUControl = 7;
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 0 );
    assert( Zero == 1 );
    printf("PASS\t ALU - not %u, %u \n", A, B);
}

void rw_memory_test() {

    unsigned MemTest[16384];
    unsigned memdata;
    unsigned halt;
    unsigned ALUresult, MemWrite, MemRead, data2;

    ALUresult = 0x1000, MemWrite = 1, MemRead = 0, data2 = 0x1;
    halt = rw_memory(ALUresult, data2, MemWrite, MemRead, &memdata, MemTest);
    assert( halt == 0 );
    assert( MemTest[ALUresult >> 2] == 0x1 );
    printf("PASS\t RW MEMORY- MEMWRITE:%u MEMREAD:%u MEMDATA:%u ADDR:0x%x MEM[ADDR]:%u\n",
            MemWrite, MemRead, memdata, ALUresult >> 2, MemTest[ALUresult >> 2]);

    ALUresult = 0x1000, MemWrite = 0, MemRead = 1, data2 = 0x1;
    MemTest[0x1000 >> 2] = 0x5;
    halt = rw_memory(ALUresult, data2, MemWrite, MemRead, &memdata, MemTest);
    assert( halt == 0 );
    assert( memdata == 0x5 );
    printf("PASS\t RW MEMORY- MEMWRITE:%u MEMREAD:%u MEMDATA:%u ADDR:0x%x MEM[ADDR]:%u\n",
            MemWrite, MemRead, memdata, ALUresult >> 2, MemTest[ALUresult >> 2]);

    ALUresult = 0x1001, MemWrite = 1, MemRead = 0, data2 = 0x1;
    halt = rw_memory(ALUresult, data2, MemWrite, MemRead, &memdata, MemTest);
    assert( halt == 1 );
    printf("PASS\t RW MEMORY - HALT ON UNALINED ADDR: 0x%x\n", ALUresult);

}

void pc_update_test() {

    unsigned jsec, extended_value, PC;
    unsigned beforeJump;
    char Branch, Jump, Zero;

    PC = 0x4000, extended_value=0x0, Branch = 0, Jump = 0, Zero = 0, jsec=0x0;
    PC_update(jsec, extended_value, Branch, Jump, Zero, &PC);
    assert( PC == 0x4004);
    printf("PASS\t PC UPDATE - NORMAL WORD INCREMENT FROM 0x%x to 0x%x\n",
            PC - 4, PC);

    PC = 0x4000, extended_value=0x8, Branch = 1, Jump = 0, Zero = 0;
    PC_update(jsec, extended_value, Branch, Jump, Zero, &PC);
    assert( PC == 0x4004);
    printf("PASS\t PC UPDATE - BRANCH ATTEMPT WITHOUT ZERO, INCREMENT FROM 0x%x to 0x%x\n",
            PC - 4, PC);

    PC = 0x4000, extended_value=0x0010, Branch = 1, Jump = 0, Zero = 1;
    PC_update(jsec, extended_value, Branch, Jump, Zero, &PC);
    assert( PC == 0x4044);
    printf("PASS\t PC UPDATE - BRANCH ATTEMPT WITH ZERO, INCREMENT FROM 0x%x to 0x%x\n",
            PC - (extended_value >> 2) - 4, PC);

    PC = 0x20004000, extended_value=0x0010, Branch = 0, Jump = 1, Zero = 0, jsec=0x4000000;
    beforeJump = PC;
    PC_update(jsec, extended_value, Branch, Jump, Zero, &PC);
    assert( PC == 0x30000000);
    printf("PASS\t PC UPDATE - JUMP, INCREMENT FROM 0x%x to 0x%x\n",
            beforeJump, PC);

}

int main() {

    setup();
    alu_test();
    instruction_fetch_test();
    instruction_partition_test();
    instruction_decode_test();
    read_register_test();
    sign_extend_test();
    rw_memory_test();
    pc_update_test();
    setupFull();
    full_test();
    return 0;
}
