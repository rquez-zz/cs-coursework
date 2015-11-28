#include "pm0.h"

int stack(const char* acodePath, const char* stackTracePath, instruction* mcode) {

    // Open output file for writing
    FILE* acodePtr = openFile(acodePath, "w");
    FILE* stackTracePtr = openFile(stackTracePath, "w");

    // Intialize registers
    int SP = 0; // Points to the top of the stack
    int BP = 1; // Points to base of the activation record
    int PC = 0; // The index of the next instruction
    instruction* IR; // The current instruction container
    int halt = 0;

    // Initialize stack
    int stack[MAX_STACK_HEIGHT] = { 0 };

    // Build and write Instructions string to file
    fprintf(acodePtr, "%s", buildInstructionsString(mcode));

    // Write header for stacktrace
    fprintf(stackTracePtr, "\t\t\t\tpc\tbp\tsp\tstack\n");
    fprintf(stackTracePtr, "Initial Values\t\t\t%d\t%d\t%d\n", PC, BP, SP);

    // Fetch Cycle
    while(halt == 0) {
        // Fetch instruction
        IR = &mcode[PC];

        // Execute instruction
        int prevPC = PC;
        execute(IR, &PC, &SP, &BP, &halt, stack);

        // Write execution trace line to file
        fprintf(stackTracePtr, "%s", buildTraceLine(prevPC, IR, PC, BP, SP, stack));
    }

    fclose(acodePtr);
    fclose(stackTracePtr);

    return 0;
}

/* Opens a file and returns a FILE pointer */
FILE* openFile(const char* path, const char* op) {
    FILE* filePtr;
    filePtr = fopen(path, op);
    if(filePtr == NULL) {
        fprintf(stderr, "[PARSER-ERROR] Error opening %s\n", path);
        perror("");
        return NULL;
    }
    return filePtr;
}

/* Build string showing all instructions */
char* buildInstructionsString(instruction* instructions) {
    char* text = malloc(MAX_CODE_LENGTH);

    // Header
    sprintf(text, "%s", "LINE\tOP\tL\tM\n");

    // Concat each instruction to a string
    int i = 0;
    while(i != -1) {
        sprintf(text+strlen(text), "%d\t%s\t%d\t%d\n",
                i,
                getOpcodeName(instructions[i].opcode),
                instructions[i].level,
                instructions[i].modifier);
        // Check if last instruction is reached
        if (instructions[i].opcode == 11 && instructions[i].modifier == 3)
            i = -1;
        else
            i++;
    }
    return text;
}

/* Returns the 3 letter name of the opcode */
char* getOpcodeName(int opcode) {
    char* name = "";
    switch (opcode) {
        case 1: // LIT
            name = "lit";
            break;
        case 2: // OPR
            name = "opr";
            break;
        case 3: // LOD
            name = "lod";
            break;
        case 4: // STO
            name = "sto";
            break;
        case 5: // CAL
            name = "cal";
            break;
        case 6: // INC
            name = "inc";
            break;
        case 7: // JMP
            name = "jmp";
            break;
        case 8: // JPC
            name = "jpc";
            break;
        case 9: // SIO
        case 10:
        case 11:
            name = "sio";
            break;
    }
    return name;
}

/* Build string showing execution trace line */
char* buildTraceLine(int prevPC, instruction* IR, int PC, int BP, int SP, int* stack) {

    char* line = malloc(MAX_CODE_LENGTH);

    sprintf(line+strlen(line), "%d\t%s\t%d\t%d\t%d\t%d\t%d\t%s\n",
            prevPC,
            getOpcodeName(IR->opcode),
            IR->level,
            IR->modifier,
            PC,
            BP,
            SP,
            stackString(stack, SP, BP));

    return line;
}

/* Build string showing the stack */
char* stackString(int* stack, int SP, int BP) {

    char* stackString = malloc(MAX_STACK_HEIGHT);

    int i = 1;
    // Loop through the stack from 1 to SP
    // When BP that is not 1 is encountered, print a divider
    while (i <= SP) {
        if (i == BP && BP != 1)
            sprintf(stackString+strlen(stackString), "| ");
        sprintf(stackString+strlen(stackString), "%d ", stack[i]);
        i++;
    }

    return stackString;
}

/* Executes the instruction IR and increments the PC */
void execute(instruction* IR, int* PC, int* SP, int* BP, int* halt, int* stack) {

    // Read Instructions
    int opcode = IR->opcode;
    int lex = IR->level;
    int param =  IR->modifier;

    // Switch statement on opcode
    switch (opcode) {
        case 1: // LIT
            *SP = *SP + 1;
            stack[*SP] = param;
            *PC = *PC + 1;
            break;
        case 2: // OPR
            if (param == 0) {
                *SP = *BP - 1;
                *PC = stack[*SP + 4];
                *BP = stack[*SP + 3];
            } else {
                // TODO: Implement all OPR operations
                switch (param) {
                    case 1: // NEG
                        stack[*SP] = stack[*SP] * -1;
                        break;
                    case 2: // ADD
                    {
                        int sum = stack[*SP] + stack[*SP - 1];
                        *SP = *SP - 1;
                        stack[*SP] = sum;
                        break;
                    }
                    case 3: // SUB
                    {
                        int diff = stack[*SP - 1] - stack[*SP];
                        *SP = *SP - 1;
                        stack[*SP] = diff;
                        break;
                    }
                    case 4: // MUL
                    {
                        int product = stack[*SP] * stack[*SP - 1];
                        *SP = *SP - 1;
                        stack[*SP] = product;
                        break;
                    }
                    case 5: // DIV
                    {
                        int quo = stack[*SP - 1] / stack[*SP];
                        *SP = *SP - 1;
                        stack[*SP] = quo;
                        break;
                    }
                    case 6: // ODD
                        if (stack[*SP] % 2 == 1)
                            stack[*SP] = 1;
                        else
                            stack[*SP] = 0;
                        break;
                    case 7: // MOD
                    {
                        int mod = stack[*SP - 1] % stack[*SP];
                        *SP = *SP - 1;
                        stack[*SP] = mod;
                        break;
                    }
                    case 8: // EQL
                        if (stack[*SP] == stack[*SP - 1])
                            stack[*SP - 1] = 1;
                        else
                            stack[*SP - 1] = 0;
                        *SP = *SP - 1;
                        break;
                    case 9: // NEQ
                        if (stack[*SP] == stack[*SP - 1])
                            stack[*SP - 1] = 0;
                        else
                            stack[*SP - 1] = 1;
                        *SP = *SP - 1;
                        break;
                    case 10: // LSS
                        if (stack[*SP - 1] < stack[*SP])
                            stack[*SP - 1] = 1;
                        else
                            stack[*SP - 1] = 0;
                        *SP = *SP - 1;
                        break;
                    case 11: // LEQ
                        if (stack[*SP - 1] <= stack[*SP])
                            stack[*SP - 1] = 1;
                        else
                            stack[*SP - 1] = 0;
                        *SP = *SP - 1;
                        break;
                    case 12: // GTR
                        if (stack[*SP - 1] > stack[*SP])
                            stack[*SP - 1] = 1;
                        else
                            stack[*SP - 1] = 0;
                        *SP = *SP - 1;
                        break;
                    case 13: // GEQ
                        if (stack[*SP - 1] >= stack[*SP])
                            stack[*SP - 1] = 1;
                        else
                            stack[*SP - 1] = 0;
                        *SP = *SP - 1;
                        break;
                }
                *PC = *PC + 1;
            }
            break;
        case 3: // LOD
            *SP = *SP + 1;
            stack[*SP] = stack[BASE(lex, *BP) + param];
            *PC = *PC + 1;
            break;
        case 4: // STO
            stack[BASE(lex, *BP) + param] = stack[*SP];
            stack[*SP] = 0;
            *SP = *SP - 1;
            *PC = *PC + 1;
            break;
        case 5: // CAL
            stack[*SP + 1] = 0;
            stack[*SP + 2] = BASE(lex, *BP);
            stack[*SP + 3] = *BP;
            stack[*SP + 4] = *PC + 1;
            *BP = *SP + 1;
            *PC = param;
            break;
        case 6: // INC
            *SP = *SP + param;
            *PC = *PC + 1;
            break;
        case 7: // JMP
            *PC = param;
            break;
        case 8: // JPC
            if (stack[*SP] == 0)
                *PC = param;
            else
                *PC = *PC + 1;
            *SP = *SP - 1;
            break;
        case 9: // SIO 1
            printf("%d\n", stack[*SP]);
            *SP = *SP - 1;
            *PC = *PC + 1;
            break;
        case 10: // SIO 2
            *SP = *SP + 1;
            int result = scanf("%d", &stack[*SP]);
            if (result != 1) {
                fprintf(stderr, "[PARSER-ERROR] Error reading input.");
            }
            *PC = *PC + 1;
            break;
        case 11: // SIO 3
            *halt = 1;
            *PC = 0;
            *SP = 0;
            *BP = 0;
            break;
    }
}
