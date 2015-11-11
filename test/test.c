#include <assert.h>
#include "../src/scanner.c"
#include "../src/parser.c"
#include "../src/generator.c"

void setup() {

}

void testScan() {
    const char* testInputAll = "input/input-test-all.txt";
    const char* testCleanOutput = "output/input-test-all-clean.txt";
    const char* testLexTable = "output/input-test-all-lex-table.txt";
    const char* testTokenListPath = "output/input-test-all-token-list.txt";
    token tokens;

    scan(testInputAll, testCleanOutput, testLexTable, testTokenListPath, &tokens);

    token* helper = &tokens;
    char lexeme[12];
    int value;
    int type;
    FILE* ifp = fopen("test/testTokenList.txt", "r");
    while (helper != NULL) {
        int count = fscanf(ifp, "%s\t%d\t%d", lexeme, &value, &type);
        assert(count == 3);
        assert(type == helper->type);
        assert(value == helper->value);
        int result = strcmp(lexeme, helper->lexeme);
        assert(result == 0);
        helper = helper->next;
    }
}


void testParse() {
    const char* testInputAll = "input/input-test-parser.txt";
    const char* testCleanOutput = "output/input-test-parser-clean.txt";
    const char* testLexTable = "output/input-test-parser-lex-table.txt";
    const char* testTokenListPath = "output/input-test-parser-token-list.txt";
    const char* testSymbolTablePath = "output/input-test-all-symbol-table.txt";

    token tokens;
    scan(testInputAll, testCleanOutput, testLexTable, testTokenListPath, &tokens);

    symbol symbolTable[MAX_SYMBOL_TABLE_SIZE];
    int j = 0;
    for (j = 0; j < MAX_SYMBOL_TABLE_SIZE; j++) {
        strcpy(symbolTable[j].name, "*");
        symbolTable[j].kind = 0;
        symbolTable[j].value = 0;
        symbolTable[j].level = 0;
        symbolTable[j].address = 0;
    }
    parse(testSymbolTablePath, &tokens, symbolTable);

    FILE* ifp = fopen("test/testSymbolTable.txt", "r");
    char name[12];
    int i = 0, kind = 0, value = 0, level = 0, address = 0;
    while (i < MAX_SYMBOL_TABLE_SIZE) {
        int count = fscanf(ifp, "%s", name);
        count = fscanf(ifp, "%d", &kind);
        count = fscanf(ifp, "%d", &value);
        count = fscanf(ifp, "%d", &level);
        count = fscanf(ifp, "%d", &address);
        assert(count == 1);
        int result = strcmp(symbolTable[i].name, name);
        assert(result == 0);
        assert(symbolTable[i].kind == kind);
        assert(symbolTable[i].value == value);
        assert(symbolTable[i].level == level);
        assert(symbolTable[i].address == address);
        i++;
    }
}


void testGenerate() {

}

int main() {

    setup();
    testScan();
    testParse();
    testGenerate();
    printf("ALL TESTS PASSED!\n");
    return 0;
}
