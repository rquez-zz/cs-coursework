CC = gcc
CFLAGS = -g -O2 -Wall
OBJECTS = spimcore.o project.o
PROGRAM = spimcore
INPUT = input_file.asc

test : $(OBJECTS)
	$(CC) $(CFLAGS) $(OBJECTS) -o $(PROGRAM)

%.o : %.c
	$(CC) $(CFLAGS) -c $<

clean :
	rm *.o $(PROGRAM)

run :
	./$(PROGRAM) $(INPUT)
