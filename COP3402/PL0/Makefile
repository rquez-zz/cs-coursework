CC = gcc
CFLAGS = -g -O2 -Wall
OBJECTS = src/pl0.o src/parser.o src/scanner.o src/pm0.o
TEST_OBJECTS = test/test.o
PROGRAM = pl0
TEST_PROGRAM = test

pl0 : $(OBJECTS)
	$(CC) $(CFLAGS) $(OBJECTS) -o $(PROGRAM)

%.o : src/%.c
	$(CC) $(CFLAGS) -c $<

test.o : test/test.c
	$(CC) $(CFLAGS) -c $< -o $(TEST_OBJECTS)

clean :
	@rm $(OBJECTS)
	@rm $(PROGRAM)
	@rm $(TEST_OBJECTS)
	@rm $(TEST_PROGRAM)

test : test.o
	$(CC) $(CFLAGS) $(TEST_OBJECTS) -o $(TEST_PROGRAM)
	$(TEST_PROGRAM)
	@rm output/*test*
