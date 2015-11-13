CC = gcc
CFLAGS = -g -O2 -Wall
OBJECTS = src/pl0.o src/generator.o src/parser.o src/scanner.o
TEST_OBJECTS = test/test.o
PROGRAM = bin/pl0
TEST_PROGRAM = bin/test
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

test.o : test/test.c
	$(CC) $(CFLAGS) -c $< -o $(TEST_OBJECTS)

clean :
	@rm $(OBJECTS)
	@rm $(PROGRAM)
	@rm $(CLEANINPUT)
	@rm $(LEXEMETABLE)
	@rm $(TOKENLIST)
	@rm $(SYMBOLTABLE)
	@rm $(TEST_OBJECTS)
	@rm $(TEST_PROGRAM)

run :
	$(PROGRAM) $(INPUT) $(CLEANINPUT) $(LEXEMETABLE) $(TOKENLIST) $(SYMBOLTABLE) $(MCODE)

test : test.o
	$(CC) $(CFLAGS) $(TEST_OBJECTS) -o $(TEST_PROGRAM)
	$(TEST_PROGRAM)
	@rm output/*test*
