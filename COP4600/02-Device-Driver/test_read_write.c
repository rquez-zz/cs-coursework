#include<stdio.h>
#include<stdlib.h>
#include<errno.h>
#include<fcntl.h>
#include<string.h>
#include<assert.h>

#define DEVICE_PATH "/dev/read_write"

static int fd;
static char receive[8];
static char receiveBig[256];

// When user can take everything the device reads out
void test1() {

   int ret;
   char send[9] = "12345678";

   ret = write(fd, send, 8);
   ret = read(fd, receive, 4);

   assert(strcmp(receive,"1234")==0);
   printf("TEST 1: Send 8, Receive 4 - PASS\n");
}

// When user sends nothing, but still receives what's still in the device buffer
void test2() {

   int ret;
   char send[8] = {0};

   ret = write(fd, send, 0);
   ret = read(fd, receive, 4);

   assert(strcmp(receive,"5678")==0);
   printf("TEST 2: DeviceHas 4, UserBuffer 4, Send 0, Receive 4 - PASS\n");
}

// When user sends 8 chars and gets back 8 chars
void test3() {

   int ret;
   char send[8] = "1234567";

   ret = write(fd, send, 8);
   ret = read(fd, receive, 8);

   assert(strcmp(receive,"1234567")==0);
   printf("TEST 3: UserBuffer 8, Send 8, Receive 8 - PASS\n");
}

// When user sends 8 chars but has no memory to receive
void test4() {

   int ret;
   memset(receive, 0, 9);
   char send[8] = "1234567";

   ret = write(fd, send, 8);
   ret = read(fd, receive, 0);

   assert(strcmp(receive,"")==0);
   printf("TEST 4: UserBuffer 0, Send 8, Receive 0 - PASS\n");
}

// When user sends nothing, but should still receive 8 chars from beforehand
void test5() {

   int ret;
   char send[8] = {0};

   ret = write(fd, send, 0);
   ret = read(fd, receive, 8);

   assert(strcmp(receive,"1234567")==0);
   printf("TEST 5: DeviceHas 8, UserBuffer 8, Send 0, Receive 8 - PASS\n");
}

// When user sends more than the device buffer
void test6() {

   int ret;
   char send[2048];
   memset(send, 'A', sizeof(send));

   ret = write(fd, send, 2048);
   ret = read(fd, receiveBig, 1024);

   assert(strlen(receiveBig) == 1024);
   printf("TEST 6: DeviceBuffer 1024, UserBuffer 0, Send 2048, Receive 1024 - PASS\n");
}

// When user sends more than the device buffer over several requests
void test7() {

   int ret;
   char send[350];

   memset(send, 'B', sizeof(send));
   ret = write(fd, send, 350);
   memset(send, 'C', sizeof(send));
   ret = write(fd, send, 350);
   memset(send, 'C', sizeof(send));
   ret = write(fd, send, 350);

   ret = read(fd, receiveBig, 1024);

   assert(strlen(receiveBig) == 1024);
   printf("TEST 7: DeviceBuffer 1024, Send 350x3, Receive 1024 - PASS\n");
}

int main() {

    fd = open(DEVICE_PATH, O_RDWR);

    test1();
    test2();
    test3();
    test4();
    test5();
    test6();
    test7();
    return 0;
}
