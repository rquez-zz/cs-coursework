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
ERRSYN = test/input/input-test-errsyn.txt
ERRTYPE = test/input/input-test-errtype.txt
ERRUNDEC = test/input/input-test-errundec.txt
ERRBADVAR = test/input/input-test-badvariablename.txt
ERRINVALIDSYM = test/input/input-test-invalidsymbol.txt
ERRLONGVAR = test/input/input-test-longvariablename.txt

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

test-errsyn:
	@echo '[MAKE] Should error with "=" expected'
	@$(PROGRAM) $(ERRSYN) $(CLEANINPUT) $(LEXEMETABLE) $(TOKENLIST) $(SYMBOLTABLE) $(MCODE)

test-errtype:
	@echo '[MAKE] Should error with "A is a procedure"'
	@$(PROGRAM) $(ERRTYPE) $(CLEANINPUT) $(LEXEMETABLE) $(TOKENLIST) $(SYMBOLTABLE) $(MCODE)

test-errundec:
	@echo '[MAKE] Should error with "Undeclared identifier"'
	@$(PROGRAM) $(ERRUNDEC) $(CLEANINPUT) $(LEXEMETABLE) $(TOKENLIST) $(SYMBOLTABLE) $(MCODE)

test-errbadvar:
	@echo '[MAKE] Should error with "Bad variable name"'
	@$(PROGRAM) $(ERRBADVAR) $(CLEANINPUT) $(LEXEMETABLE) $(TOKENLIST) $(SYMBOLTABLE) $(MCODE)

test-errinvalidsym:
	@echo '[MAKE] Should error with "Invalid symbol"'
	@$(PROGRAM) $(ERRINVALIDSYM) $(CLEANINPUT) $(LEXEMETABLE) $(TOKENLIST) $(SYMBOLTABLE) $(MCODE)

test-errlongvar:
	@echo '[MAKE] Should error with "Variable name too long"'
	@$(PROGRAM) $(ERRLONGVAR) $(CLEANINPUT) $(LEXEMETABLE) $(TOKENLIST) $(SYMBOLTABLE) $(MCODE)
