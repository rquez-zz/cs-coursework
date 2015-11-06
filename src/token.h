#ifndef TOKEN_H
#define TOKEN_H

typedef enum {
    nulsym = 1,
    identsym,
    numbersym,
    plussym,
    minussym, 
    multsym,
    slashsym,
    oddsym,
    equalsym,
    neqsym,
    lessym,
    leqsym,
    gtrsym,
    geqsym,
    lparentsym,
    rparentsym,
    commasym,
    semicolonsym,
    periodsym,
    becomesym,
    beginsym,
    endsym,
    ifsym,
    thensym,
    whilesym,
    dosym,
    callsym,
    constsym,
    varsym,
    procsym,
    writesym,
    redsym,
    elseym
} token_type;

typedef struct token {

    // Name of the token 
	char lexeme[12];

    // Value for constants
	int value;

    // type from enum
    token_type type;

    struct token* next;

} token;

#endif /* TOKEN_H */
