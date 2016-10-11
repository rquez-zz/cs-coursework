obj-m+=read_write.o

all:
	make -C /lib/modules/$(shell uname -r)/build/ M=$(PWD) modules
	$(CC) test_read_write.c -o test_read_write
clean:
	make -C /lib/modules/$(shell uname -r)/build/ M=$(PWD) clean
