CC = gcc
CFLAGS = -g -O2 -Wall
OBJECTS = alu_test.o
PROGRAM = test

test : $(OBJECTS)
	$(CC) $(CFLAGS) $(OBJECTS) -o $(PROGRAM)

%.o : %.c
	$(CC) $(CFLAGS) -c $<

clean :
	rm *.o $(PROGRAM)

run :
	./$(PROGRAM)
