//
//
//
//

//tyler mellor skele

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAX_SYMBOL_SIZE 100

//constants store kind, name, and val
//variables store kind, name, L, and M
//procedures store kind, name, L, and M

typedef struct symbol
{
	int kind;
	char name[12];
	int val;
	int level;
	int addr;

	/* data */
}symbol;


int main()
{
	FILE *ifp;
	char ch;

	int value;

	ifp = fopen("input.txt", "r" );

	if(ifp == NULL)
	{
		printf("unable to open file");
		return 0;
	}

	ch = getchar(); // stateone

	if(isalpha(ch))
	{
		while(isalpha(ch) || isdigit(ch)) /* state two */
		{
			ch = getchar();
		}
		//dfa loop conditional determines if token is a letter or digit
		ungetc(ch, ifp); //state three

		//create token
		//accept token
		// return token

	}
	else
	{
		printf("not a identifier");
		ungetc(ch, ifp);
		// backup for next character check
	}

	ch = getchar(); //state four
	
	if(isdigit(ch))
	{
		value = (int)ch;
		//converts character to integer

		ch = getchar();
		while(isdigit(ch)) // state five
		{
			value = 10 * value + (int)ch;
			ch = getchar();
		}
		//while ch is digit convert ch to int

		ungetc(ch, ifp); //state 
		//create token
		//accept
		//return token

	}
	else
	{
		printf("not a number");
		ungetc(ch, ifp);
	}

	//check for items other than letters or digits
	fclose(ifp);

	return 0;
}

typedef enum{
	nulsym = 1, identsym, numbersym, plussym, minussym, multsym,
	slashsym, oddsym, eqsym, neqsym, lesssym, leqsym, gtrsym,
	geqsym, lparentsym, rparentsym, commasym, semicolonsym,
	periodsym, becomessym, beginsym, endsym, ifsym, tnesym,
	whilesym, dosym, callsym, constsym, varsym, procsym, writesym,
	redsym, elseym
	
}token_type;
