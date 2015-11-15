#include "generator.h"


void generate(FILE* mcodePtr, char* op, int level, int address) {
	// these should be symbol table levels and  symbol table op and symbol table adress

	int codeindex = 0;
	struct emit emitter[MAX_SYMBOL_TABLE_SIZE];
	// this size will be defined at some point not sure yet

	int i = 0;

	if(codeindex > MAX_SYMBOL_TABLE_SIZE)
		return -1;
	else
	{
		emitter[codeindex].operation = op;
		emitter[codeindex].lexLevel = level;
		emitter[codeindex].addresslocation = address;

		codeindex++;
	}

//	for(i = 0; i < MAX_SYMBOL_TABLE_SIZE; i++)
//	{
//		fprintf(mcodePtr, "%c\t%d\t%d", emitter[i].operation, emitter[i].lexLevel, emitter[i].addresslocation );
//	}

	//start of pring function 
	// printing will be solved later



}
