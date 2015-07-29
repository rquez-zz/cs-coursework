#include "spimcore.h"


/* ALU */
/* 10 Points */
void ALU(unsigned A,unsigned B,char ALUControl,unsigned *ALUresult,char *Zero)
{
    switch(ALUControl) {
        case '0': // 000 add
            *ALUresult = A + B;
            break;
        case '1': // 001 sub
            *ALUresult = A - B;
            break;
        case '2': // 010 slt
            if (A < B)
                *ALUresult = 1;
            else
                *ALUresult = 0;
            break;
        // TODO: Difference between sltu an slt
        case '3': // 011 sltu
            if (A < B)
                *ALUresult = 1;
            else
                *ALUresult = 0;
            break;
        case '4': // 100 and
            *ALUresult = A && B;
            break;
        case '5': // 101 or
            *ALUresult = A || B;
            break;
        case '6': // 110 sll
            *ALUresult = B << 16;
            break;
        case '7': // 111 not
            *ALUresult = !A;
            break;
    }

    if (*ALUresult == 0)
        *Zero = '1';
    else
        *Zero = '0';
}

/* instruction fetch */
/* 10 Points */
int instruction_fetch(unsigned PC,unsigned *Mem,unsigned *instruction)
{
    return 0;
}


/* instruction partition */
/* 10 Points */
void instruction_partition(unsigned instruction, unsigned *op, unsigned *r1,unsigned *r2, unsigned *r3, unsigned *funct, unsigned *offset, unsigned *jsec)
{
}



/* instruction decode */
/* 15 Points */
int instruction_decode(unsigned op,struct_controls *controls)
{
    return 0;
}

/* Read Register */
/* 5 Points */
void read_register(unsigned r1,unsigned r2,unsigned *Reg,unsigned *data1,unsigned *data2)
{
}


/* Sign Extend */
/* 10 Points */
void sign_extend(unsigned offset,unsigned *extended_value)
{
}

/* ALU operations */
/* 10 Points */
int ALU_operations(unsigned data1,unsigned data2,unsigned extended_value,unsigned funct,char ALUOp,char ALUSrc,unsigned *ALUresult,char *Zero)
{
    return 0;
}

/* Read / Write Memory */
/* 10 Points */
int rw_memory(unsigned ALUresult,unsigned data2,char MemWrite,char MemRead,unsigned *memdata,unsigned *Mem)
{
    return 0;
}


/* Write Register */
/* 10 Points */
void write_register(unsigned r2,unsigned r3,unsigned memdata,unsigned ALUresult,char RegWrite,char RegDst,char MemtoReg,unsigned *Reg)
{
}

/* PC update */
/* 10 Points */
void PC_update(unsigned jsec,unsigned extended_value,char Branch,char Jump,char Zero,unsigned *PC)
{
}

