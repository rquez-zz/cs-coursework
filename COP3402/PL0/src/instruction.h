#ifndef INSTRUCTION_H 
#define INSTRUCTION_H

typedef struct instruction {

    // Number that maps to an op memonic
	int opcode;

	int level;

	int modifier;

    // Index of symbol in symbol table
    int index;

} instruction;

#endif /* INSTRUCTION_H */
