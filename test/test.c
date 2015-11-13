#include <assert.h>
#include "../src/scanner.c"
#include "../src/parser.c"
#include "../src/generator.c"

void setup() {

}

void testScan() {
    const char* testInputAll = "test/input/input-test-all.txt";
    const char* testCleanOutput = "output/cleanInput-test-all-txt";
    const char* testLexTable = "output/lexTable-test-all.txt";
    const char* testTokenListPath = "output/tokenList-test-all.txt";
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
    const char* testInputAll = "test/input/input-test-parser.txt";
    const char* testCleanOutput = "output/cleanInput-test-parser-txt";
    const char* testLexTable = "output/lexTable-test-parser.txt";
    const char* testTokenListPath = "output/tokenList-test-parser.txt";
    const char* testSymbolTablePath = "output/symbolTable-test-parser.txt";
    const char* testMcodePath = "output/mcode-test-parser.txt";

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
    parse(testMcodePath, testSymbolTablePath, &tokens, symbolTable);

    FILE* ifp = fopen("test/output/symbolTable-test-parser.txt", "r");
    char name[12];
    char kindS[12];
    char valueS[12];
    char levelS[12];
    int count = fscanf(ifp, "%s\t%s\t%s\t%s\n", name, kindS, valueS, levelS);
    assert(count == 4);
    int i = 0, kind = 0, value = 0, level = 0;
    while (i < MAX_SYMBOL_TABLE_SIZE) {
        if (strcmp(symbolTable[i].name, "*") != 0) {
            int count = fscanf(ifp, "%s", name);
            count = fscanf(ifp, "%s", kindS);
            if (strcmp(kindS, "proc") == 0)
                kind = 3;
            if (strcmp(kindS, "const") == 0)
                kind = 1;
            if (strcmp(kindS, "var") == 0)
                kind = 2;
            count = fscanf(ifp, "%d", &value);
            count = fscanf(ifp, "%d", &level);
            assert(count == 1);
            int result = strcmp(symbolTable[i].name, name);
            assert(result == 0);
            assert(symbolTable[i].kind == kind);
            assert(symbolTable[i].value == value);
            assert(symbolTable[i].level == level);
        }
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
