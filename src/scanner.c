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
            char slash = ch;
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
                // Write / and next char
                fputc(slash, ofp);
                fputc(ch, ofp);
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
void writeTokens(token* tokens, FILE* lexTblPtr, FILE* tokLstPtr, int count) {

    // Print header
    fprintf(lexTblPtr, "lexeme\ttoken type\n");

    // Traverse through linked list of tokens
    token* helper = tokens;
    int i = 0;
    while (i < count) {

        // Write to token list
        if (helper->type == 3)
            fprintf(tokLstPtr, "%d %d ", helper->type, helper->value);
        else if (helper->type == 2)
            fprintf(tokLstPtr, "%d %s ", helper->type, helper->lexeme);
        else if (i == count - 1)
            fprintf(tokLstPtr, "%d", helper->type);
        else
            fprintf(tokLstPtr, "%d ", helper->type);

        // Write to lexeme table
        fprintf(lexTblPtr, "%s\t%d\n", helper->lexeme, helper->type);

        helper = helper->next;
        i++;
    }
}

/* Returns the token type of the lexeme if it matches, return identsym type */
token_type getReservedType(char* lexeme) {

    const char* const reserved[] = {"begin", "end", "if", "then", "while",
        "do", "call", "const", "var", "procedure", "write", "read", "else",
        "odd"};
    token_type type;

    // Loop through reserved words to find a match
    int i = 0;
    while(i < 14) {
        if (strcmp(reserved[i], lexeme) == 0) {

            if (i == 13) {
                // Use "odd" comparison token as reserved word
                type = 8;
            } else {
                // Offset to get the right type
                type = i + 21;
                i = 13;
            }

        } else {
            // If it's not a reserved word it is an identifier
            type = identsym;
        }
        i++;
    }

    return type;
}

/* add token to linked list */
void addToList(token** tokens, char* lexeme, int value, int type, int* countTokens) {

    // If type is -1 then token already exists
    if ((*tokens)->type != -1){ 
        token* nextToken = malloc(sizeof(token));
        strcpy(nextToken->lexeme, lexeme);
        nextToken->value = value;
        nextToken->type = type;
        (*tokens)->next = nextToken;
        *tokens = (*tokens)->next;
    } else {
        strcpy((*tokens)->lexeme, lexeme);
        (*tokens)->value = value;
        (*tokens)->type = type;
    }

    *countTokens += 1;
}

/* clean and scan input into lex table and token list */
int scan(const char* inputPath, const char* cleanInputPath,
        const char* lexTablePath, const char* tokenListPath, token* tokens) {

    // Open clean input for reading
    FILE* ifp = getCleanInput(inputPath, cleanInputPath);

    // Linked list of tokens
    token* firstToken = tokens;
    tokens->type = -1;
    int countTokens = 0;

    // Keep track of current line number
    int lineNumber = 1;

    // Loop through input as DFA simulation
    while(!feof(ifp)) {

        // Get Character from stream
        char ch = getc(ifp);

        // Copy character into a temp string
        char lexeme[12] = "";

        // Boolean to check if the current ch has been matched
        int matched = 0;

        // Check if ch is part of an Identifier or Reserved Word
        if(isalpha(ch)) {
            int couldBeReserved = 1;
            int letterCount = 0;

            // Get the next char while checking if it's alphanumeric
            while( (isalpha(ch) || isdigit(ch)) && !feof(ifp)) {

                // If token contains a digit then it's not a reserved word
                if (isdigit(ch)) {
                    couldBeReserved = 0;
                }

                // Append ch to temp token
                append(lexeme, ch);
                letterCount++;

                // Identifier can't be longer than 11 characters
                if (letterCount > 11) {
                    fprintf(stdout, "[SCANNER-ERROR] Identifiers may not be longer than 11 characters, at line %d.", lineNumber);
                    return (-1);
                }

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

            addToList(&tokens, lexeme, 0, type, &countTokens);
        } else {
            // Not alphabetic, go back
            ungetc(ch, ifp);
        }

        // Get next char
        ch = getc(ifp);

        // Check if ch is part of a Value
        if(isdigit(ch)) {

            int numCount = 0;

            while(isdigit(ch)) {
                // Append ch to temp token
                append(lexeme, ch);
                numCount++;

                // Number can't be longer than 5 digits
                if (numCount > 5) {
                    fprintf(stdout, "[SCANNER-ERROR] Numbers may not be longer than 5 digits, at line %d.", lineNumber);
                    return (-1);
                }

                // Get next ch
                ch = getc(ifp);

                // Identifiers can't start with numbers, throw error
                if (isalpha(ch)) {
                    fprintf(stdout, "[SCANNER-ERROR] Variable doesn't start with a letter, at line %d.", lineNumber);
                    return (-1);
                }
            }

            // Parse int value
            int value = atoi(lexeme);

            // Go back 1 char
            ungetc(ch, ifp);

            addToList(&tokens, lexeme, value, numbersym, &countTokens);
        } else {
            // Not a digit, go back
            ungetc(ch, ifp);
        }

        // Get next ch
        ch = getc(ifp);

        // Check for :=
        if (ch == ':') {
            ch = getc(ifp);
            if (ch == '=') {

                addToList(&tokens, ":=", 0, becomesym, &countTokens);
                ch = getc(ifp);
            } else {
                // Not :=, go back
                ungetc(ch, ifp);
            }
        }

        // Check for =
        if(ch == '=') {
            // Create token
            addToList(&tokens, ":=", 0, equalsym, &countTokens);
        }

        // Check for > and >=
        if (ch == '>') {
            // Check if >=
            ch = getc(ifp);

            if (ch == '=') {
                addToList(&tokens, ">=", 0, geqsym, &countTokens);
            } else {
                ungetc(ch, ifp);
                addToList(&tokens, ">", 0, gtrsym, &countTokens);
            }
        }

        // Check for < and <=
        if (ch == '<') {
            // Check if <= or <>
            ch = getc(ifp);

            if (ch == '=') {
                addToList(&tokens, "<=", 0, leqsym, &countTokens);
            } else if ( ch == '>') {
                addToList(&tokens, "<>", 0, neqsym, &countTokens);
            } else {
                ungetc(ch, ifp);
                addToList(&tokens, "<", 0, lessym, &countTokens);
            }
        }

        // Check for (
        if (ch == '(') {
            // Create token
            addToList(&tokens, "(", 0, lparentsym, &countTokens);
        }

        // Check for )
        if (ch == ')') {
            // Create token
            addToList(&tokens, ")", 0, rparentsym, &countTokens);
        }

        // Check for ,
        if (ch == ',') {
            // Create token
            addToList(&tokens, ",", 0, commasym, &countTokens);
        }

        // Check for ;
        if (ch == ';') {
            // Create token
            addToList(&tokens, ";", 0, semicolonsym, &countTokens);
        }

        // Check for .
        if (ch == '.') {
            // Create token
            addToList(&tokens, ".", 0, periodsym, &countTokens);
        }

        // Check for +
        if (ch == '+') {
            // Create token
            addToList(&tokens, "+", 0, plussym, &countTokens);
        }

        // Check for -
        if (ch == '-') {
            // Create token
            addToList(&tokens, "-", 0, minussym, &countTokens);
        }

        // Check for *
        if (ch == '*') {
            // Create token
            addToList(&tokens, "*", 0, multsym, &countTokens);
        }

        // Check for /
        if (ch == '/') {
            // Create token
            addToList(&tokens, "/", 0, slashsym, &countTokens);
        }

        // Increment line number on newline
        if (ch == '\n')
            lineNumber++;

        // Throw error for invalid character
        if (matched && ch != ' ' && ch != '\n' && ch != '\r') {
            fprintf(stdout, "[SCANNER-ERROR] Invalid character, at line %d.", lineNumber);
            return -1;
        }
    }

    // Close input
	fclose(ifp);

    // Write lexeme table
    FILE* lexTblPtr = openFile(lexTablePath, "w");
    FILE* tokLstPtr = openFile(tokenListPath, "w");
    writeTokens(firstToken, lexTblPtr, tokLstPtr, countTokens);

    // Close output
    fclose(lexTblPtr);
    fclose(tokLstPtr);

	return 0;
}

