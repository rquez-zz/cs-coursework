CC = gcc
CFLAGS = -g -O2 -Wall
OBJECTS = stack.o
PROGRAM = ../bin/stack
INPUT = ../input/mcode.txt

stack : $(OBJECTS)
	$(CC) $(CFLAGS) $(OBJECTS) -o $(PROGRAM)
	rm *.o

%.o : %.c
	$(CC) $(CFLAGS) -c $<

clean :
	rm *.o

run :
	$(PROGRAM) $(INPUT)
