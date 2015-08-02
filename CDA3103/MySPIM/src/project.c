#include "spimcore.h"


/* ALU */
/* 10 Points */
void ALU(unsigned A,unsigned B,char ALUControl,unsigned *ALUresult,char *Zero)
{
    switch(ALUControl) {
        case 0: // 000 add
            *ALUresult = (signed)A +(signed) B;
            break;
        case 1: // 001 subtract
            *ALUresult = (signed)A - (signed)B;
            break;
        case 2: // 010 set 1 if A < B else 0
            if ((signed)A < (signed)B)
                *ALUresult = 1;
            else
                *ALUresult = 0;
            break;
        case 3: // 011 set 1 if (unsigned)A < (unsigned)B else 0
            if (A < B)
                *ALUresult = 1;
            else
                *ALUresult = 0;
            break;
        case 4: // 100 A AND B
            *ALUresult = A && B;
            break;
        case 5: // 101 A OR B
            *ALUresult = A || B;
            break;
        case 6: // 110 shift left B 16 bits
            // TODO: Ask if this should be output to ALUresult
            B = B << 16;
            break;
        case 7: // 111 not
            *ALUresult = !A;
            break;
    }

    if (*ALUresult == 0)
        *Zero = 1;
    else
        *Zero = 0;
}

/* instruction fetch */
/* 10 Points */
int instruction_fetch(unsigned PC,unsigned *Mem,unsigned *instruction)
{
    // Check if PC is word aligned
    if (PC % 4 != 0)
        return 1;

    // Shift PC right by 2 to get proper index
    *instruction = Mem[PC >> 2];
    return 0;
}


/* instruction partition */
/* 10 Points */
void instruction_partition(unsigned instruction, unsigned *op, unsigned *r1,unsigned *r2, unsigned *r3, unsigned *funct, unsigned *offset, unsigned *jsec)
{
    // Unless if the bits are already at the end of the string,
    // shift right to place the bits at the end and then bitmask
    // to isolate.
    *op = (instruction >> 26)   & 0b00000000000000000000000000111111;
    *r1 = (instruction >> 21)   & 0b00000000000000000000000000011111;
    *r2 = (instruction >> 16)   & 0b00000000000000000000000000011111;
    *r3 = (instruction >> 11)   & 0b00000000000000000000000000011111;
    *funct = instruction        & 0b00000000000000000000000000111111;
    *offset = instruction       & 0b00000000000000001111111111111111;
    *jsec = instruction         & 0b00000011111111111111111111111111;
}



/* instruction decode */
/* 15 Points */
int instruction_decode(unsigned op,struct_controls *controls)
{
    switch(op) {
        // R-Type
        case 0x0:
            controls->RegDst = 1;
            controls->Jump = 0;
            controls->Branch = 0;
            controls->MemRead = 0;
            controls->MemtoReg = 0;
            controls->ALUOp = 7;
            controls->MemWrite = 0;
            controls->ALUSrc = 0;
            controls->RegWrite = 1;
            break;
        // Jump
        case 0x2:
            controls->RegDst = 0;
            controls->Jump = 1;
            controls->Branch = 0;
            controls->MemRead = 0;
            controls->MemtoReg = 0;
            controls->ALUOp = 0;
            controls->MemWrite = 0;
            controls->ALUSrc = 0;
            controls->RegWrite = 0;
            break;
        // Branch Equal
        case 0x4:
            controls->RegDst = 2;
            controls->Jump = 0;
            controls->Branch = 1;
            controls->MemRead = 0;
            controls->MemtoReg = 2;
            controls->ALUOp = 1;
            controls->MemWrite = 0;
            controls->ALUSrc = 0;
            controls->RegWrite = 0;
            break;
        // Add Immediate
        case 0x8:
            controls->RegDst = 0;
            controls->Jump = 0;
            controls->Branch = 0;
            controls->MemRead = 0;
            controls->MemtoReg = 0;
            controls->ALUOp = 0;
            controls->MemWrite = 0;
            controls->ALUSrc = 1;
            controls->RegWrite = 1;
            break;
        // Set to 1 if Less Than Immediate
        case 0xA:
            controls->RegDst = 0;
            controls->Jump = 0;
            controls->Branch = 0;
            controls->MemRead = 0;
            controls->MemtoReg = 0;
            controls->ALUOp = 2;
            controls->MemWrite = 0;
            controls->ALUSrc = 1;
            controls->RegWrite = 1;
            break;
        // Set to 1 if Less Than Unsigned Immediate
        case 0xB:
            controls->RegDst = 0;
            controls->Jump = 0;
            controls->Branch = 0;
            controls->MemRead = 0;
            controls->MemtoReg = 0;
            controls->ALUOp = 3;
            controls->MemWrite = 0;
            controls->ALUSrc = 1;
            controls->RegWrite = 1;
            break;
        // Bitwise AND Immediate
        case 0xC:
            controls->RegDst = 0;
            controls->Jump = 0;
            controls->Branch = 0;
            controls->MemRead = 0;
            controls->MemtoReg = 0;
            controls->ALUOp = 4;
            controls->MemWrite = 0;
            controls->ALUSrc = 1;
            controls->RegWrite = 1;
            break;
        // Bitwise OR Immediate
        case 0xD:
            controls->RegDst = 0;
            controls->Jump = 0;
            controls->Branch = 0;
            controls->MemRead = 0;
            controls->MemtoReg = 0;
            controls->ALUOp = 5;
            controls->MemWrite = 0;
            controls->ALUSrc = 1;
            controls->RegWrite = 1;
            break;
        // Load Upper Immediate
        case 0xF:
            controls->RegDst = 0;
            controls->Jump = 0;
            controls->Branch = 0;
            controls->MemRead = 0;
            controls->MemtoReg = 0;
            controls->ALUOp = 6;
            controls->MemWrite = 0;
            controls->ALUSrc = 1;
            controls->RegWrite = 1;
            break;
        // Load Word
        case 0x23:
            controls->RegDst = 0;
            controls->Jump = 0;
            controls->Branch = 0;
            controls->MemRead = 1;
            controls->MemtoReg = 1;
            controls->ALUOp = 0;
            controls->MemWrite = 0;
            controls->ALUSrc = 1;
            controls->RegWrite = 1;
            break;
        // Store Word
        case 0x2B:
            controls->RegDst = 2;
            controls->Jump = 0;
            controls->Branch = 0;
            controls->MemRead = 0;
            controls->MemtoReg = 2;
            controls->ALUOp = 0;
            controls->MemWrite = 1;
            controls->ALUSrc = 1;
            controls->RegWrite = 0;
            break;
        // Halt Otherwise
        default:
            return 1;
    }
    // No Errors
    return 0;
}

/* Read Register */
/* 5 Points */
void read_register(unsigned r1,unsigned r2,unsigned *Reg,unsigned *data1,unsigned *data2)
{
    // Read from registers at addresses r1 and r2
    *data1 = Reg[r1];
    *data2 = Reg[r2];
}


/* Sign Extend */
/* 10 Points */
void sign_extend(unsigned offset,unsigned *extended_value)
{
    // Check if offset is a negative
    if (offset >> 15 == 1)
        // Preserve negative by masking signficant bits to 1
        *extended_value = offset | 0xffff0000;
    else
        // Append 16 bits to the front
        *extended_value = offset & 0x0000ffff;
}

/* ALU operations */
/* 10 Points */
int ALU_operations(unsigned data1,unsigned data2,unsigned extended_value,unsigned funct,char ALUOp,char ALUSrc,unsigned *ALUresult,char *Zero)
{
    // Determine to use data2 or extended value
    if (ALUSrc == 1)
        data2 = extended_value;

    // Generate ALUControl based on ALUOp and funct
    unsigned ALUControl;
    if (ALUOp == 7) { // Operation is R-Type
        switch(funct) {
            // Add
            case 32:
                ALUControl = 0b000;
                break;
            // Subtract
            case 34:
                ALUControl = 0b001;
                break;
            // Set 1 On Less Than
            case 42:
                ALUControl = 0b010;
                break;
            // Set 1 On Less Than Unsigned
            case 43:
                ALUControl = 0b011;
                break;
            // AND
            case 36:
                ALUControl = 0b100;
                break;
            // OR
            case 37:
                ALUControl = 0b101;
                break;
            // Shift Left Extended Value by 16
            case 38:
                ALUControl = 0b110;
                break;
            // NOT
            case 39:
                ALUControl = 0b111;
                break;
            // Unknown funct, halt
            default:
                return 1;
        }
    } else // Operation is not R-type use existing ALUOp
        ALUControl = ALUOp;

    // Send to ALU
    ALU(data1, data2, ALUControl, ALUresult, Zero);

    // No Errors
    return 0;
}

/* Read / Write Memory */
/* 10 Points */
int rw_memory(unsigned ALUresult,unsigned data2,char MemWrite,char MemRead,unsigned *memdata,unsigned *Mem)
{
    // Check ALUresult has a word aligned address
    if ((MemWrite || MemRead) && ALUresult % 4 != 0)
        return 1;

    if (MemWrite == 1) { // Write to Memory
        Mem[ALUresult >> 2] = data2;
    } else if (MemRead == 1) { // Read from Memory
        *memdata = Mem[ALUresult >> 2];
    }

    // No Errors
    return 0;
}


/* Write Register */
/* 10 Points */
void write_register(unsigned r2,unsigned r3,unsigned memdata,unsigned ALUresult,char RegWrite,char RegDst,char MemtoReg,unsigned *Reg)
{
    // Continue only if RegWrite is asserted
    if( RegWrite != 1 )
        return;

    // Write to the appropriate register from the appropriate source
    // RegDst 1 is for rtype instructions
    // RegDst 0 is for itype instructions
    if ( MemtoReg == 1 ) { // Memory is source of write
        if ( RegDst == 1)
            Reg[r3] = memdata;
        else
            Reg[r2] = memdata;
    } else { // ALU Result is source of write
        if ( RegDst == 1 )
            Reg[r3] = ALUresult;
        else
            Reg[r2] = ALUresult;
    }

}

/* PC update */
/* 10 Points */
void PC_update(unsigned jsec,unsigned extended_value,char Branch,char Jump,char Zero,unsigned *PC)
{
}

