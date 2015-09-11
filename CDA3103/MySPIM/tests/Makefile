CC = gcc
CFLAGS = -g -O2 -Wall
OBJECTS = tests.o
PROGRAM = ../bin/test

test : $(OBJECTS)
	$(CC) $(CFLAGS) $(OBJECTS) -o ../bin/$(PROGRAM)
	rm *.o

%.o : %.c
	$(CC) $(CFLAGS) -c $<

run :
	./$(PROGRAM)
