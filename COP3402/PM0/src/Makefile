CC = gcc
CFLAGS = -g -O2 -Wall
OBJECTS = stack.o pm0.o
PROGRAM = ../bin/stack
INPUT = ../input/mcode.txt
OUTPUT = ../output/mcode-stacktrace.txt

stack : $(OBJECTS)
	$(CC) $(CFLAGS) $(OBJECTS) -o $(PROGRAM)
	rm *.o

%.o : %.c
	$(CC) $(CFLAGS) -c $<

clean :
	rm *.o

run :
	$(PROGRAM) $(INPUT) $(OUTPUT)
