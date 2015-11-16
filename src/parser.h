#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#include "symbol.h"
#include "token.h"
#include "instruction.h"

#define AR_SIZE (4)

void parse(const char* symbolTablePath, const char* mcodePath, token* tokens, symbol* symbolTable, instruction* instructions, int* cx);
FILE* openFileParser(const char* path, const char* op);
void writeSymbolTable(symbol* symbolTable, FILE* symTblPtr);
void writeInstructions(instruction* instructions, FILE* mcodePtr, int cx);
void addToSymbolTable(symbol** symbolTable, char* name, int kind, int value, int level, int address);
void emit(int opcode, int level, int modifier, int* cx, instruction** instructions);
int hashToken(char* name, int kind);
void linearProbe(int* index, symbol** symbolTable);
void program(token* tokens, symbol* symbolTable, instruction* instructions, int level, int* cx);
void block(token** tokens, symbol* symbolTable, instruction* instructions, int level, int* cx);
void constant(token** tokens, symbol* symbolTable, int level);
void variable(token** tokens, symbol* symbolTable, int level);
void procedure(token** tokens, symbol* symbolTable, instruction* instructions, int level, int* cx);
void statement(token** tokens, symbol* symbolTable, instruction* instructions, int level, int* cx);
void condition(token** tokens, symbol* symbolTable, instruction* instrctions, int level, int* cx);
void expression(token** tokens, symbol* symbolTable, instruction* instructions, int level, int* cx);
void term(token** tokens, symbol* symbolTable, instruction* instructions, int level, int* cx);
void factor(token** tokens, symbol* symbolTable, instruction* instructions, int level, int* cx);
int lookupIdentifier(char* name, symbol** symbolTable, int level);
