#ifndef "GENERATOR_H"
#define "GENERATOR_H"
#include <stdio.h>
#include "symbol.h"
#include "token.h"

void generate(FILE* mcodePtr, char* op, int level, int address);

struct emit
{
	char operation;
	//op code

	int lexLevel;
	//lexlevel of item in symbol table

	int addresslocation;
	//location of symbol in symbol table
	// might need to make these all arrays of sub 
	
};


#endif 

//generate