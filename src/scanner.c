#include "scanner.h"

//constants store kind, name, and val
//variables store kind, name, L, and M
//procedures store kind, name, L, and M

/* Opens a file and returns a FILE pointer */
FILE* openFile(const char* path, const char* op) {
    FILE* filePtr;
    filePtr = fopen(path, op);
    if(filePtr == NULL) {
        perror("[SCANNER-ERROR] Error opening file.");
        return NULL;
    }
    return filePtr;
}

/* Appends a char to a string */
void append(char* string, char c) {
    string[strlen(string)] = c;
    string[strlen(string) + 1] = '\0';
}

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

