Introduction
################
In this project, you are asked to write the core part of a mini processor
simulator called MySPIM using C language on a Unix or a PC platform.
Your MySPIM will demonstrate some functions of MIPS processor as well as
the principle of the datapath and the control signals of MIPS processor.
The MySPIM simulator should read in a file containing MIPS machine codes
(in the format specified below) and simulate what the MIPS does cycle-by-cycle.
You are required to implement the MySPIM with a single-cycle datapath.
You are asked to fill in the body of several functions in a given file.


Specification of the simulator
##############################
MySPIM should handle the 32 general purpose registers.
The size of memory of MySPIM is 64kB (Address 0x0000 to 0xFFFF).
The system assumes that all program starts at memory location 0x4000.
All instructions are word-aligned in the memory, i.e., the addresses of all
instructions are multiple of 4.
The simulator (and the MIPS processor itself) treats the memory as one segment.
(The division of memory into text, data, and stack segments is only done by the compiler/assembler.)
At the start of the program, all memory are initialized to zero,
except those specified in the “-asc” file, as shown in the provided codes.
The memory is in big-endian byte order. (Most significant byte in the smallest address)
The memory is in the following format:
e.g. Store a 32-bit number 0xaabbccdd in memory address
0x0: aa, 0x1: bb, 0x2: cc, 0x3: dd

Halting
################################
Global flag Halt is set to 1 when
* An illegal instruction is encountered
* Jumping to an address that is not word-aligned (being a multiple of 4)
* The address of lw or sw is not word-aligned
* Accessing data or jump to address that is beyond the memory.

Input
################################
MySPIM takes hexadecimal formatted machine codes, with filename xxx.asc, as input.
An example of .asc file is shown below. Text after “#” on any line is treated as comments.
`
20010000 	# addi $1, $0, 0
200200c8 	# addi $2, $0, 200
10220003 	# beq $1, $2, 3
00000020 	# delay slot
20210001 	# addi $1, $1, 1
00000020 	# no operation
`
The simulation ends when an illegal instruction, such as 0x00000000, is encountered.

Files Provided
#################################
*spimcore.c
*spimcore.h
*project.c
These files contain the main program and the other supporting functions of the simulator.
The code should be self-explanatory. You are required to fill in the functions in project.c.
You may also introduce new functions, but do not modify any other part of the files.
Otherwise, your program may not be properly marked.
You are not allowed to modify spimcore.c and spimcore.h.
All your works should be placed in project.c only.

Functions To Be Filled
##################################
ALU(...)
* Implement the operations on input parameters A and B according to ALUControl.
* Output the result (Z) to ALUresult.
* Assign Zero to 1 if the result is zero; otherwise, assign 0.

instruction_fetch(...)
* Fetch the instruction addressed by PC from Mem and write it to instruction.
* Return 1 if a halt condition occurs; otherwise, return 0.

instruction_partition(...)
* Partition instruction into several parts (op, r1, r2, r3, funct, offset, jsec).
* Read line 41 to 47 of spimcore.c for more information.

instruction_decode(...)
* Decode the instruction using the opcode (op).
* Assign the values of the control signals to the variables in the structure controls (See spimcore.h file).
* The meanings of the values of the control signals:
** For MemRead, MemWrite or RegWrite, the value 1 means that enabled, 0 means that disabled, 2 means “don’t care”.
** For RegDst, Jump, Branch, MemtoReg or ALUSrc, the value 0 or 1 indicates the selected path of the multiplexer; 2 means “don’t care”.
* Return 1 if a halt condition occurs; otherwise, return 0.

read_register(...)
* Read the registers addressed by r1 and r2 from Reg, and write the read values to data1 and data2 respectively.

sign_extend(...)
* Assign the sign-extended value of offset to extended_value.

ALU_operations(...)
* Apply ALU operations on data1, and data2 or extended_value (determined by ALUSrc).
* The operation performed is based on ALUOp and funct.
* Apply the function ALU(…).
* Output the result to ALUresult.
* Return 1 if a halt condition occurs; otherwise, return 0.

rw_memory(...)
* Base on the value of MemWrite or MemRead to determine memory write operation or memory read operation.
* Read the content of the memory location addressed by ALUresult to memdata.
* Write the value of data2 to the memory location addressed by ALUresult.
* Return 1 if a halt condition occurs; otherwise, return 0.

write_register(...)
* Write the data (ALUresult or memdata) to a register (Reg) addressed by r2 or r3

PC_update(...)
* Update the programcounter (PC)

The file spimcore.h is the header file which contains the definition of a
structure storing the control signals and the prototypes of the above 10
functions. The functions may contain some parameters. Read spimcore.h for more information.

Hint: Some instructions may try to write to the register $zero and we assume that they are valid.
However, your simulator should always keep the value of $zero equal to 0.

NOTE: You should not do any “print” operation in project.c. Otherwise, the operation will disturb the marking process and you will be penalized.

Running MySPIM
#################################
For your convenience, here is how you could do it in UNIX environment. First compile:
`
$ gcc -o spimcore spimcore.c project.c
`
After compilation, to use MySPIM, you would type the following command in UNIX:
`
$ ./spimcore <filename>.asc
`

FAQ
#################################
Q. Is memory supposed to be an array of words, or an array of bytes?

As in.
mem[1] = 32 bit word
mem[4] = 32 bit word
mem[8] = 32 bit word

Or if it's an array of bytes
mem[0] = 8 bit byte
mem[1] = 8 bit byte
mem[2] = 8 bit byte
mem[3] = 8 bit byte
mem[4] = 8 bit byte  //the first byte of a new word.

So if we want to read in an instruction from memory we would read it in 4 8 bit chunks.

A. Functions that require it are instruction_fetch and rw_memory and the array Mem is passed as an argument.
The little table from the first page of the spec gives the answer:
Notice the “Mem[0]” in the first row, so the memory goes like this
Mem[0] = 0x0000 - 0x0003
Mem[1] = 0x0004 - 0x0007
...
Mem[16384] = 0xFFFC - 0xFFFF (65532 - 65536)

It also says that all program starts at memory location 0x4000 (see def of PCINIT in spimcore.c) which corresponds to Mem[4096]. (4096 = 0x1000)
The trick is to test for word alignment while it is still in address form, then reference the proper index in the Mem array by shifting the address right twice.


Q. What are the inputs and outputs of the program?

A. Most of the functionality of this simulator is provided by spimcore.
The only input that you need to provide to spimcore is a text file with
extension .asc, which should contain the 32-bit instructions as ASCII in
hexadecimal format (see the example in the project description). You can
write a sequence of instructions manually in your .asc file, or optionally
write a simple assembler to do that for you
(i.e. convert a program to its hex sequence).
There is no output. Spimcore takes care of the simulation.
But you need to make sure that the datapath/control are working correctly.
For your convenience, the diagram in Figure 2 of the project description
color-codes each function to be implemented with dotted lines.


Q. How does instruction_fetch() work?

A. The data you are given is in Mem which is an array that is filled in
the main() from the data supplied to the program in your .asc file.
PC is the index of the Mem[], where the address is.
But with a little twist: You have to use (PC >> 2) whenever you use it.
The info. that is in this location ref: by Mem[ (PC >> 2) ] is the decimal
value of the instruction that was in Hex in the file. As of for checking to
see if its word is aligned ...maybe you should read the book!
Note that 64k (bytes 0-65,536) of memory are available, but again they are
accessed by words (unsigned Mem[]). Therefore for a given address, you have
to determine its word offset, which is the index to the array Mem[].


Q. What do "RegDst" and "ALUSrc" represent?

A. RegDst defines which register is the destination register of an instruction.
If the instruction is an R-type then the destination register is given by bits
15-11 of the instruction and if the instruction is I-type or branch then the
destination register is given by bits 20-16 of the instruction.
If you look at the diagram in Figure 2 of the project description,
then you can see that for the latter case RegDst=0 and for the former RegDst=1.
ALUSrc determines the source input of the ALU. Again this can be readily
determined from the diagram in Figure 2 of the project description.
For R-type instructions the second input to the ALU is given by the register
specified by bits 20-16, which appear directly at the output Read Data2 of the
register file. For I-type and branching, however, the second input to ALU is
coming from the Sign Extend unit. Therefore based on the diagram in Figure 2
of the project description, in the former case ALUSrc=0 and in the latter ALUSrc=1.


Q. What R-type instructions are we supposed to set when ALUOp is equal to "111"? It just says "instruction is an R-type".

A. When the ALUOp control signal is 7 or 111, this tells the ALU control that
it needs to figure out what operation to tell the ALU to do by looking at the
funct field (bits[5-0]) of the instruction.
This is called "multiple levels of decoding".


Q. Should we halt in rw_memory() in the event that ((ALUresult % 4)!=0)?

A. Yes that is correct, but only halt when ALUresult is an address.
ALUresult should be an address if MemRead or MemWrite is asserted.
For instance, on an I-type command, the ALUresult would hold a memory address.


Q. Under the ALU_operations function, what are we supposed to do with the
arguments, extended_value and ALUsrc ?

A. If you look at the diagram in Figure 2 of the project description,
your ALUsrc is a control signal to a multiplexer, which chooses between a
sign extended_value, or data2 in order to send the outcome to the ALU for operation.


Q. What are we supposed to do with the Zero parameter being passed
into many of the functions?

A. Zero parameter indicates that the result of the operation performed
by ALU was zero. This can be for instance used for conditional branching.


Q. How do we know when a control signal is a “don’t care”?

A. A control signal is a “don’t care” for an instruction, if its value has
no effect on the correct operation of datapath for that instruction.
For instance, the ALUOp signals have no impact on the correct operation of
the datapath for the jump instruction. Also, since we will not write to a
destination register, the value of RegDst would be irrelevant for jump.


Q. What are argc and argv for?

A. The argc stands for "argument count" and it is also the number of elements in the argv
array. char **argv could also be written as char *argv[].
But that doesn't matter for this project. spimcore.c handles everything for you,
it also contains the main method.
Unless you changed something in spimcore.c, it should work fine. Don't forget to compile it according to the directions.

