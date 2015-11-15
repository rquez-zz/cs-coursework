#include "generator.h"

#define MAXTABLE 100
// the struct and this will be moved to a header at some point

struct emit
{
	char operation;
	
	int lexLevel;

	int addresslocation;
	// might need to make these all arrays of sub 
	
};

void generate(FILE* mcodePtr, char* op, int level, int address) {

	int codeindex = 0;
	struct emit emitter[MAXTABLE];
	// this size will be defined at some point not sure yet

	if(codeindex > MAXTABLE)
		return -1;
	else
	{
		emitter[codeindex].operation = op;
		emitter[codeindex].lexLevel = level;
		emitter[codeindex].addresslocation = address;

		codeindex++;
	}


}
