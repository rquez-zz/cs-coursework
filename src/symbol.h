#ifndef SYMBOL_H 
#define SYMBOL_H 

#define MAX_SYMBOL_TABLE_SIZE (100)

typedef struct symbol {

    char name[12];

    /*
     * 1 for constant
     * 2 for variable
     * 3 for procedure
     */
    int kind;

    int value;

    // L Level
    int level;

    // M Address
    int address;

    struct symbol* next;

} symbol;

#endif /* SYMBOL_H */
