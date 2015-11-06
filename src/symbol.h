#ifndef SYMBOL_H 
#define SYMBOL_H 

typedef struct symbol {

    char symbol;

    /*
     * 0 for constant 
     * 1 for variable 
     * 2 for procedure
     */
    int type;

    // L Level
    int level;

    // M Address
    int address;

    struct symbol* next;

} symbol;

#endif /* SYMBOL_H */
