CC = gcc
CFLAGS = -g -O2 -Wall
OBJECTS = src/scanner.o
PROGRAM = bin/scanner
INPUT = input/input.txt
CLEANINPUT = output/cleanInput.txt
LEXEMETABLE = output/lexemeTable.txt
TOKENLIST = output/tokenList.txt

scanner : $(OBJECTS)
	@$(CC) $(CFLAGS) $(OBJECTS) -o $(PROGRAM)

%.o : src/%.c
	@$(CC) $(CFLAGS) -c $<

clean :
	@rm $(OBJECTS)
	@rm $(PROGRAM)
	@rm $(CLEANINPUT)
	@rm $(LEXEMETABLE)

run :
	@$(PROGRAM) $(INPUT) $(CLEANINPUT) $(LEXEMETABLE) $(TOKENLIST)
