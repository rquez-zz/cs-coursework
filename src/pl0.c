#include "pl0.h"

int main(int argc, char* argv[]) {

    const char* inputPath = argv[1];
    const char* cleanInputPath = argv[2];
    const char* lexTablePath = argv[3];
    const char* tokenListPath = argv[4];
    const char* symbolTablePath = argv[5];
    const char* mcodePath = argv[6];

    fprintf(stdout, "[PL0] Starting...\n");

    fprintf(stdout, "[SCANNER] Starting...\n");
    token tokens;
    scan(inputPath, cleanInputPath, lexTablePath, tokenListPath, &tokens);

    fprintf(stdout, "[PARSER] Starting...\n");
    symbol symbolTable[MAX_SYMBOL_TABLE_SIZE];
    int j = 0;
    for (j = 0; j < MAX_SYMBOL_TABLE_SIZE; j++) {
        strcpy(symbolTable[j].name, "*");
        symbolTable[j].kind = 0;
        symbolTable[j].value = 0;
        symbolTable[j].level = 0;
        symbolTable[j].address = 0;
    }
    parse(symbolTablePath, &tokens, symbolTable);

    fprintf(stdout, "[GENERATOR] Starting...\n");
    generate(mcodePath, &tokens, symbolTable);

    return 0;
}
