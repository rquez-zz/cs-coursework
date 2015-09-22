CC = gcc
CFLAGS = -g -O2 -Wall
OBJECTS = test.o
PROGRAM = ../bin/test
INPUT = ../input/mcode.txt

test : $(OBJECTS)
	$(CC) $(CFLAGS) $(OBJECTS) -o $(PROGRAM)
	rm *.o

%.o : %.c
	$(CC) $(CFLAGS) -c $<

clean :
	rm *.o

run :
	$(PROGRAM) $(INPUT)
