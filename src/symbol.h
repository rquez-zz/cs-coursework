#ifndef SYMBOL_H 
#define SYMBOL_H 

typedef struct symbol {

    char name[12];

    /*
     * 0 for constant 
     * 1 for variable 
     * 2 for procedure
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
