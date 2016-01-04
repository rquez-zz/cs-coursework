#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#include "token.h"

FILE* openFileScanner(const char* path, const char* op);
void append(char* string, char c);
FILE* getCleanInput(const char* inputPath, const char* outputPath);
void writeTokens(token* tokens, FILE* lexTblPtr, FILE* tokLstPtr, int count);
token_type getReservedType(char* lexeme);
int scan(const char* inputPath, const char* cleanInputPath, const char* lexTablePath, const char* tokenListPath, token* tokens);
