#include "../src/project.c"
#include <assert.h>

void alu_test() {

    unsigned A;
    unsigned B;
    int As;
    int Bs;
    char ALUControl;
    unsigned ALUResult = 0;
    char Zero = '0';

    // When A + b
    A = 2, B = 1, ALUControl = '0';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 3 );
    assert( Zero == '0' );
    printf("PASS\t ALU - add %d, %d \n", A, B);

    // When A - B
    A = 2, B = 1, ALUControl = '1';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - sub %d, %d \n", A, B);

    // When A - B == Zero == 0
    A = 2, B = 2, ALUControl = '1';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 0);
    assert( Zero == '1' );
    printf("PASS\t ALU - sub %d, %d \n", A, B);

    // When (signed)A < (signed)B is false & Zero == 1
    As = 2, Bs = 1, ALUControl = '2';
    ALU(As, Bs, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 0 );
    assert( Zero == '1' );
    printf("PASS\t ALU - slt %d, %d \n", A, B);

    // When (signed)A < (signed)B is true & Zero == 0
    As = 1, Bs = 2, ALUControl = '2';
    ALU(As, Bs, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - slt %d, %d \n", A, B);

    // When (unsigned) A < (unsigned) B is true & Zero == 0
    A = 1, B = 2, ALUControl = '3';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - sltu %u, %u \n", A, B);

    // When (unsigned) A < (unsigned) B is true & Zero == 0
    A = 1, B = -2, ALUControl = '3';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - sltu %u, %u \n", A, B);

    // When A && B is true & Zero == 0
    A = 1, B = 1, ALUControl = '4';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - and %u, %u \n", A, B);

    // When A && B is true & Zero == 0
    A = 4, B = 1, ALUControl = '4';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - and %u, %u \n", A, B);

    // When A && B is false & Zero == 1
    A = 4, B = 0, ALUControl = '4';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 0 );
    assert( Zero == '1' );
    printf("PASS\t ALU - or %u, %u \n", A, B);

    // When A || B is true & Zero == 0
    A = 1, B = 1, ALUControl = '5';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - or %u, %u \n", A, B);

    // When A || B is true & Zero == 0
    A = 4, B = 1, ALUControl = '5';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - or %u, %u \n", A, B);

    // When A || B is false & Zero == 1
    A = 0, B = 0, ALUControl = '5';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 0 );
    assert( Zero == '1' );
    printf("PASS\t ALU - or %u, %u \n", A, B);

    // When B is left shifted by 16 bits & Zero == 0
    /*
    A = 0, B = 1, ALUControl = '6';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    printf("%d", B);
    assert( B != 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - sll %u, %u \n", A, B);
    */

    // When ~A is true & Zero == 0
    A = 0, B = 0, ALUControl = '7';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 1 );
    assert( Zero == '0' );
    printf("PASS\t ALU - not %u, %u \n", A, B);

    // When ~A is false & Zero == 0
    A = 1, B = 0, ALUControl = '7';
    ALU(A, B, ALUControl, &ALUResult, &Zero);
    assert( ALUResult == 0 );
    assert( Zero == '1' );
    printf("PASS\t ALU - not %u, %u \n", A, B);
}

int main() {

    alu_test();

    return 0;
}
