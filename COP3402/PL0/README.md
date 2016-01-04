# PL/0

### Members

* Ricardo Vasquez
* Tyler Mellor
* Joesph Leavitt

### Documentation
Screenshots are located in the `docs` directory.

### Compile
`make`

### Run
`./pl0 [-t] [-s] [-m] [-a] [-v]`

    -t
        Prints the token list

    -s
        Prints the symbol table

    -m
        Prints the machine code

    -a
        Prints the disassembled code

    -v
        Prints the VM execution stacktrace

### Input
* Input file is at `input/input.txt`

### Output
* Clean input file is at `output/cleanInput.txt`
* Lexeme table is at `output/lexemeTable.txt`
* Token list is at `output/tokenList.txt`
* Machine code is at `output/mcode.txt`
* Disassembled code is at `output/acode.txt`
* Stacktrace is at `output/stacktrace.txt`
