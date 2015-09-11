CC = gcc
CFLAGS = -g -O2 -Wall
OBJECTS = spimcore.o project.o
PROGRAM = ../bin/spimcore
INPUT = ../input/input_file.asc

spimcore : $(OBJECTS)
	$(CC) $(CFLAGS) $(OBJECTS) -o $(PROGRAM)
	rm *.o

%.o : %.c
	$(CC) $(CFLAGS) -c $<

run :
	$(PROGRAM) $(INPUT)
