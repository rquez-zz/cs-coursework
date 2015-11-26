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

    // Loop through all the arguments
    int i = 0;
    for (i = 1; i <= argc; i++) {

        if (strcmp(argv[i], TOKENLIST_SWITCH) == 0) {
            printTokenList(tokenListPath);
        } else if (strcmp(argv[i], SYMBOLTABLE_SWITCH) == 0) {
            printSymbolTable(symbolTablePath);
        } else if (strcmp(argv[i], MCODE_SWITCH) == 0) {
            printMachineCode(mcodePath);
        } else if (strcmp(argv[i], ACODE_SWITCH) == 0) {
            // TODO: Pass correct param
            printDisassembledCode();
        } else if (strcmp(argv[i], STACKTRACE_SWITCH) == 0) {
            // TODO: Pass correct param
            printStackTrace(stacktracePath);
        } else {
            // TODO: Throw error
        }
    }

    return 0;
}

void printTokenList(const char* tokenListPath) {
   
    FILE* tokenListFilePtr;
    tokenListFilePtr = fopen(tokenListPath, "r");
    char buffer[500];

    fgets(buffer, sizeof buffer, tokenListPtr);

    printf("\n%s\n\n", buffer);
    
    fclose(tokenListFilePtr);
    
}

void printSymbolTable(const char* symbolTablePath) {
   
    FILE* symbolTableFilePtr;
    symbolTableFilePtr = fopen(symbolTablePath, "r);
    char buffer[500];
    
    printf("\n");
    while(!feof(symbolTableFilePtr))
    {
        fgets(buffer, sizeof buffer, symbolTableFilePtr);
        printf("%s", buffer);
    }
    printf("\n\n");

    fclose(symbolTableFilePtr);
    
}

void printMachineCode(const char* mcodePath) {
   
    FILE* mcodeFilePtr;
    mcodeFilePtr = fopen(mcodePath, "r");
    char buffer[500];
    
    printf("\n");
    while(!feof(mcodeFilePtr))
    {
        fgets(buffer, sizeof buffer, mcodeFilePtr);
        printf("%s", buffer);
    }
    printf("\n\n"); 
    
    fclose(mcodeFilePtr);
    
}

void printDisassembledCode() {

  

}

void printStackTrace(const char* stacktracePath) {
    
    FILE* stacktraceFilePtr;
    stacktraceFilePtr = fopen(stacktracePath, "r");
    char buffer[500];
    
    // TODO: print to screen
    
    
    fclose(stacktraceFilePtr);

}
