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
