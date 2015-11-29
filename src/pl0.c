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
    stack(acodePath, stacktracePath, mcode, symbolTable);

    FILE* symTblPtr = openFileParser(symbolTablePath, "w");
    writeSymbolTable(symbolTable, symTblPtr);
    fclose(symTblPtr);

    // Loop through all the arguments
    int i = 0;
    for (i = 1; i < argc; i++) {

        if (strcmp(argv[i], TOKENLIST_SWITCH) == 0) {
            printTokenList(tokenListPath);
        } else if (strcmp(argv[i], SYMBOLTABLE_SWITCH) == 0) {
            printSymbolTable(symbolTablePath);
        } else if (strcmp(argv[i], MCODE_SWITCH) == 0) {
            printMachineCode(mcodePath);
        } else if (strcmp(argv[i], ACODE_SWITCH) == 0) {
            printDisassembledCode(acodePath);
        } else if (strcmp(argv[i], STACKTRACE_SWITCH) == 0) {
            printStackTrace(stacktracePath);
        } else {
            // TODO: Throw error
        }
    }

    return 0;
}

void printTokenList(const char* tokenListPath) {

    FILE* tokenListFilePtr = open(tokenListPath, "r");
    printf("TOKEN LIST\n");
    print(tokenListFilePtr);
    printf("\n\n");
    fclose(tokenListFilePtr);
}

void printSymbolTable(const char* symbolTablePath) {

    FILE* symbolTableFilePtr = open(symbolTablePath, "r");
    printf("SYMBOL TABLE\n");
    print(symbolTableFilePtr);
    printf("\n");
    fclose(symbolTableFilePtr);
}

void printMachineCode(const char* mcodePath) {

    FILE* mcodeFilePtr = open(mcodePath, "r");
    printf("MACHINE CODE\n");
    print(mcodeFilePtr);
    printf("\n");
    fclose(mcodeFilePtr);
}

void printDisassembledCode(const char* acodePath) {

    FILE* acodeFilePtr = openFile(acodePath, "r");
    printf("DISASSEMBLED CODE\n");
    print(acodeFilePtr);
    printf("\n");
    fclose(acodeFilePtr);
}

void printStackTrace(const char* stacktracePath) {

    FILE* stacktraceFilePtr = openFile(stacktracePath, "r");
    printf("STACKTRACE\n");
    print(stacktraceFilePtr);
    printf("\n");
    fclose(stacktraceFilePtr);
}

/* Opens a file and returns a FILE pointer */
FILE* open(const char* path, const char* op) {
    FILE* filePtr;
    filePtr = fopen(path, op);
    if(filePtr == NULL) {
        perror("[STACK-ERROR] Error opening file.");
        return NULL;
    }
    return filePtr;
}

void print(FILE* filePtr) {
    char buffer[500];
    while (fgets(buffer, sizeof buffer, filePtr) != NULL) {
        printf("%s", buffer);
    }
}
