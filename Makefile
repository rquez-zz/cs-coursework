CC = gcc
CFLAGS = -g -O2 -Wall
OBJECTS = src/pl0.o src/generator.o src/parser.o src/scanner.o
PROGRAM = bin/pl0
INPUT = input/input.txt
CLEANINPUT = output/cleanInput.txt
LEXEMETABLE = output/lexemeTable.txt
TOKENLIST = output/tokenList.txt
SYMBOLTABLE = output/symbolTable.txt
MCODE = output/mcode.txt

pl0 : $(OBJECTS)
	$(CC) $(CFLAGS) $(OBJECTS) -o $(PROGRAM)

%.o : src/%.c
	$(CC) $(CFLAGS) -c $<

clean :
	@rm $(OBJECTS)
	@rm $(PROGRAM)
	@rm $(CLEANINPUT)
	@rm $(LEXEMETABLE)
	@rm $(TOKENLIST)
	@rm $(SYMBOLTABLE)

run :
	$(PROGRAM) $(INPUT) $(CLEANINPUT) $(LEXEMETABLE) $(TOKENLIST) $(SYMBOLTABLE) $(MCODE)
