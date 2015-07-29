#include "../src/project.c"
#include <assert.h>

void alu_test() {

    unsigned A;
    unsigned B;
    char ALUControl;
    unsigned ALUResult = 0;
    char Zero = '0';

    A = 2, B = 1, ALUControl = '0';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 3 );
    assert( Zero == '0' );
    printf("ALU - add 2, 1 \t\tPASSES\n");

    A = 2, B = 1, ALUControl = '1';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("ALU - sub 2, 1 \t\tPASSES\n");

    A = 2, B = 2, ALUControl = '1';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 0);
    assert( Zero == '1' );
    printf("ALU - sub 2, 2 \t\tPASSES\n");

    A = 2, B = 1, ALUControl = '2';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 0 );
    assert( Zero == '1' );
    printf("ALU - slt 2, 1 \t\tPASSES\n");

    A = 1, B = 2, ALUControl = '2';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("ALU - slt 1, 2 \t\tPASSES\n");

}

int main() {

    alu_test();

    return 0;
}
