#include "scanner.h"

/* Opens a file and returns a FILE pointer */
FILE* openFile(const char* path, const char* op) {
    FILE* filePtr;
    filePtr = fopen(path, op);
    if(filePtr == NULL) {
        fprintf(stderr, "[SCANNER-ERROR] Error opening %s\n", path);
        perror("");
        return NULL;
    }
    return filePtr;
}

/* Appends a char to a string */
void append(char* string, char c) {
    string[strlen(string)] = c;
    string[strlen(string) + 1] = '\0';
}

/* Reads the input file and returns pointer to clean input file */
FILE* getCleanInput(const char* inputPath, const char* outputPath) {
    FILE *ifp = openFile(inputPath, "r");
    FILE* ofp = openFile(outputPath, "w");

    // Read
    while(!feof(ifp)) {
        char ch = getc(ifp);

        // Filter out comments
        if(ch == '/') {
            ch = getc(ifp);
            if (ch == '*') {
                // Start of a Comment
                while(!feof(ifp)) {
                    ch = getc(ifp);

                    // End of a Comment
                    if (ch == '*') {
                        ch = getc(ifp);
                        if (ch == '/') {
                            break;
                        }
                    }
                }
            } else {
                // Go back 1 char
                ungetc(ch, ifp);
            }
        } else {
            // Write ch to file
            if (!feof(ifp)) {
                fputc(ch, ofp);
            }
        }
    }

    // Close files
    fclose(ifp);
    fclose(ofp);

    // Return ptr to cleanInput.txt
    return openFile(outputPath, "r");
}

/* Writes tokens as output */
void writeSymbolTokens(symbol* symbols, FILE* ofp) {

    // Print header
    fprintf(ofp, "lexeme\ttoken type\n");

    // Traverse through linked list of symbols
    symbol* helper = symbols;
    while (helper != NULL) {
        fprintf(ofp, "%s\t%d\n", helper->lexeme, helper->type);
        helper = helper->next;
    }
}

/* Returns the token type of the lexeme if it matches, return identsym type */
token_type getReservedType(char* lexeme) {

    const char* const reserved[] = {"begin", "end", "if", "then", "while",
        "do", "call", "const", "var", "procedure", "write", "read", "else"};
    token_type type;

    // Loop through reserved words to find a match
    int i = 0;
    while(i < 13) {
        if (strcmp(reserved[i], lexeme) == 0) {

            // Offset to get the right type
            type = i + 21;
            i = 13;

        } else {
            // If it's not a reserved word it is an identifier
            type = identsym;
        }
        i++;
    }

    return type;
}

int main()
{
    // TODO: inputPath should come from arg
    const char* inputPath = "../input/input.txt";
    const char* cleanInputPath = "../output/cleanInput.txt";
    const char* lexTablePath = "../output/lexemeTable.txt";

    // Open clean input for reading
    FILE* ifp = getCleanInput(inputPath, cleanInputPath);

    // Linked list of symbols
    symbol* firstSymbol = NULL;
    symbol* symbols = NULL;

    // Loop through input as DFA simulation
    while(!feof(ifp)) {

        // Get Character from stream
        char ch = getc(ifp);

        // Copy character into a temp string
        char lexeme[12] = "";

        // Check if ch is part of an Identifier or Reserved Word
        if(isalpha(ch)) {

            int couldBeReserved = 1;

            // Get the next char while checking if it's alphanumeric
            while( (isalpha(ch) || isdigit(ch)) && !feof(ifp)) {

                // If token contains a digit then it's not a reserved word
                if (isdigit(ch)) {
                    couldBeReserved = 0;
                }

                // Append ch to temp token
                append(lexeme, ch);

                // Get next ch
                ch = getc(ifp);
            }

            // Go back 1 char
            ungetc(ch, ifp);

            token_type type;
            if (couldBeReserved) {
                type = getReservedType(lexeme);
            } else {
                type = identsym;
            }

            // Create token symbol identifier
            symbol* newSymbol = malloc(sizeof(symbol));
            strcpy(newSymbol->lexeme, lexeme);
            newSymbol->type = type;

            // Add symbol to list
            if (symbols == NULL) {
                symbols = newSymbol;
                firstSymbol = symbols;
            } else {
                symbols->next = newSymbol;
                symbols = symbols->next;
            }

        } else {
            // Not alphabetic, go back
            ungetc(ch, ifp);
        }

        // Get next char
        ch = getc(ifp);

        // Check if ch is part of a Value
        if(isdigit(ch)) {

            while(isdigit(ch)) {
                // Append ch to temp token
                append(lexeme, ch);

                // Get next ch
                ch = getc(ifp);
            }

            // Parse int value
            int value = atoi(lexeme);

            // Go back 1 char
            ungetc(ch, ifp);

            // Create token symbol - const
            symbol* newSymbol = malloc(sizeof(symbol));
            newSymbol->value = value;
            strcpy(newSymbol->lexeme, lexeme);
            newSymbol->type = numbersym;

            // Add symbol to list
            if (symbols == NULL) {
                symbols = newSymbol;
                firstSymbol = symbols;
            } else {
                symbols->next = newSymbol;
                symbols = symbols->next;
            }
        } else {
            // Not a digit, go back
            ungetc(ch, ifp);
        }

        // Get next ch
        ch = getc(ifp);

        // TODO: Check for +
        // TODO: Check for -
        // TODO: Check for *
        // TODO: Check for /
        //
        // TODO: Check for odd
        // TODO: Check for =
        // TODO: Check for <, <=, <>
        // TODO: Check for >. >=
        //
        // TODO: Check for (
        // TODO: Check for )
        // TODO: Check for ,
        // TODO: Check for ;
        // TODO: Check for :=
        //
        // TODO: Check for begin
        // TODO: Check for end
        // TODO: Check for if
        // TODO: Check for then
        // TODO: Check for while
        // TODO: Check for do
        // TODO: Check for call
        // TODO: Check for const
        // TODO: Check for var
        // TODO: Check for procedure
        // TODO: Check for write
        // TODO: Check for read
        // TODO: Check for else
    }

    // Close input
	fclose(ifp);

    // Write lexeme table
    FILE* ofp = openFile(lexTablePath, "w");
    writeSymbolTokens(firstSymbol, ofp);

	return 0;
}

