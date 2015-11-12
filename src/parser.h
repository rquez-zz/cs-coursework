#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#include "symbol.h"
#include "token.h"

int parse(const char* symbolTablePath, token* tokens, symbol* symbolTable);
FILE* openFileParser(const char* path, const char* op);
void writeSymbolTable(symbol* symbolTable, FILE* symTblPtr);
void addToSymbolTable(symbol** symbolTable, char* name, int kind, int value, int level, int address);
int hashToken(char* name, int kind);
void linearProbe(int* index, symbol** symbolTable);
void program(token* tokens, symbol* symbolTable, int level);
void block(token** tokens, symbol* symbolTable, int level);
void constant(token** tokens, symbol* symbolTable);
void variable(token** tokens, symbol* symbolTable);
void procedure(token** tokens, symbol* symbolTable, int level);
void statement(token** tokens, symbol* symbolTable);
