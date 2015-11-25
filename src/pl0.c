#include "pl0.h"

int main(int argc, char* argv[]) {

    const char* inputPath = INPUT_PATH;
    const char* cleanInputPath = CLEANINPUT_PATH;
    const char* lexTablePath = LEXEMETABLE_PATH;
    const char* tokenListPath = TOKENLIST_PATH;
    const char* symbolTablePath = SYMBOLTABLE_PATH;
    const char* mcodePath = MCODE_PATH;
    const char* acodePath = ACODE_PATH;
    const char* stacktracePath = STACKTRACE_PATH;

    // Scan the code into tokens
    token tokens;
    scan(inputPath, cleanInputPath, lexTablePath, tokenListPath, &tokens);

    symbol symbolTable[MAX_SYMBOL_TABLE_SIZE];
    instruction mcode[MAX_SYMBOL_TABLE_SIZE];

    // Initialize symbolTable and instructions array
    int j = 0;
    for (j = 0; j < MAX_SYMBOL_TABLE_SIZE; j++) {
        strcpy(symbolTable[j].name, "*");
        symbolTable[j].kind = 0;
        symbolTable[j].value = 0;
        symbolTable[j].level = 0;
        symbolTable[j].address = 0;
        mcode[j].opcode = 0;
        mcode[j].level = 0;
        mcode[j].modifier = 0;
    }

    // Parse the tokens into a symbol table and machine code
    int cx = 0;
    parse(symbolTablePath, mcodePath, &tokens, symbolTable, mcode, &cx);

    // Fetch and execute machine code in the stack
    // TODO: Initialize acode
    // TODO: Intialize stack


    return 0;
}
