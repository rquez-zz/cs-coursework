/**
 * @file read_write.c
 * @author Ricardo Vasquez
 */

#include <linux/init.h>
#include <linux/module.h>
#include <linux/device.h>
#include <linux/kernel.h>
#include <linux/fs.h>
#include <asm/uaccess.h>

#define DRIVER_AUTHOR "Ricardo Vasquez <ricardov@knights.ucf.edu>"
#define DRIVER_DESC "A simple character LKM that writes to a buffer and reads from it."
#define DEVICE_NAME "read_write"
#define BUFFER_SIZE 1024

MODULE_LICENSE("GPL");
MODULE_AUTHOR(DRIVER_AUTHOR);
MODULE_DESCRIPTION(DRIVER_DESC);

static int major_number;
static int dev_lock = 0;
static char message[BUFFER_SIZE] = {0};
static short size_of_message;

static int dev_open(struct inode *, struct file *);
static int dev_release(struct inode *, struct file *);
static ssize_t dev_read(struct file *, char *, size_t, loff_t *);
static ssize_t dev_write(struct file *, const char *, size_t, loff_t *);

static struct file_operations fops = {
   .open = dev_open,
   .read = dev_read,
   .write = dev_write,
   .release = dev_release,
};

static int __init readWrite_init(void){

   printk(KERN_INFO "%s-INIT: Intializing the device.\n", DEVICE_NAME);

   // Get the major number for the device
   major_number = register_chrdev(0, DEVICE_NAME, &fops);
   if (major_number < 0) {
      printk(KERN_ALERT "%s-INIT: Error registering a major number for the device.\n", DEVICE_NAME);
      return major_number;
   }

   // Log major number
   printk(KERN_INFO "%s-INIT: Registered major number %d\n", DEVICE_NAME, major_number);
   printk(KERN_INFO "%s-INIT: Run `mknod /dev/%s c %d 0` to create the device file.\n", DEVICE_NAME, DEVICE_NAME, major_number);

   return 0;
}

static void __exit readWrite_exit(void){
   unregister_chrdev(major_number, DEVICE_NAME);
   printk(KERN_INFO "%s-EXIT: Device has been unregistered.\n", DEVICE_NAME);
}

static int dev_open(struct inode *inodep, struct file *filep){

    if (dev_lock)
        return -EBUSY;

   dev_lock = 1;
   printk(KERN_INFO "%s-OPEN: Device has been opened.\n", DEVICE_NAME);
   return 0;
}

static ssize_t dev_read(struct file *filep, char *buffer, size_t len, loff_t *offset){

   int i, error_count;

   printk(KERN_INFO "%s-READ: Device message buffer is [%s] at size(%d).\n", DEVICE_NAME, message, size_of_message);

   // When user has no local buffer to read to
   if (len == 0) {
       printk(KERN_INFO "%s-READ: User buffer size is 0, message won't be read.\n", DEVICE_NAME);
       return size_of_message;
   }

   if (size_of_message == 0) {
       printk(KERN_INFO "%s-READ: Device message is empty, message won't be read.\n", DEVICE_NAME);
       return size_of_message;
   }

   // When size of message exceeds user local buffer
   if (size_of_message > len) {
       printk(KERN_INFO "%s-PARTIAL_READ: Device message size(%d) exceeds user buffer size(%zd).\n", DEVICE_NAME, size_of_message, len);

       error_count = copy_to_user(buffer, message, len);

       if (error_count) {
          printk(KERN_ALERT "%s-PARTIAL_READ: Error reading from buffer.\n", DEVICE_NAME);
          return -EFAULT;
       } else {

          printk(KERN_INFO "%s-PARTIAL_READ: Read partial message [%s] of size %zd from device buffer to user.\n", DEVICE_NAME, buffer, strlen(buffer));

          // Remove characters read from device buffer
          for (i = 0;message[len + i] != '\0'; i++) {
               message[i] = message[len + i];
          }
          message[i] = '\0';

          // Update device buffer size
          size_of_message = strlen(message);
          printk(KERN_INFO "%s-PARTIAL_READ: Device buffer is now [%s] at size %d.\n", DEVICE_NAME, message, size_of_message);
          return size_of_message;
       }
   } else {

       error_count = copy_to_user(buffer, message, size_of_message);

       if (error_count) {
          printk(KERN_ALERT "%s-READ: Failed to send %d characters to user.\n", DEVICE_NAME, error_count);
          return -EFAULT;
       } else {
          printk(KERN_INFO "%s-READ: Read message [%s] of size %zd from device buffer to user.\n", DEVICE_NAME, buffer, strlen(buffer));

          // Empty device buffer
          memset(message, '\0', sizeof message);
          size_of_message = strlen(message);
          printk(KERN_INFO "%s-READ: Read message successfully; message now [%s] of size %d.\n", DEVICE_NAME, message, size_of_message);
          return size_of_message;
       }
   }
}

static ssize_t dev_write(struct file *filep, const char *buffer, size_t len, loff_t *offset){

   printk(KERN_INFO "%s-WRITE: Device message buffer is now [%s] at size %d.\n", DEVICE_NAME, message, size_of_message);

   // When user sends nothing to write
   if (len == 0) {
       printk(KERN_INFO "%s-READ: User message size is 0, message won't be written to device buffer.\n", DEVICE_NAME);
       return size_of_message;
   }

   // Check user buffer plus current buffer exceeds the limit
   if (len + size_of_message > BUFFER_SIZE) {

       int diff = (len + size_of_message) - BUFFER_SIZE;
       printk(KERN_INFO "%s-PARTIAL_WRITE: User message exceeds device buffer by %d.\n", DEVICE_NAME, diff);

       // Copy only what fits into device buffer
       strncat(message, buffer, len - diff);

       size_of_message = strlen(message);
       printk(KERN_INFO "%s-PARTIAL_WRITE: Wrote partial message successfully; message now [%s] of size %d.\n", DEVICE_NAME, message, size_of_message);
   } else {

       printk(KERN_INFO "%s-WRITE: Writing message [%s] of size %zd from user to buffer.\n", DEVICE_NAME, buffer, len);

       // Copy whole buffer into device buffer
       strncat(message, buffer, len);
       size_of_message = strlen(message);
       printk(KERN_INFO "%s-WRITE: Wrote message successfully; message now [%s] of size %d.\n", DEVICE_NAME, message, size_of_message);
   }
   return len;
}

static int dev_release(struct inode *inodep, struct file *filep){

   dev_lock = 0;
   printk(KERN_INFO "%s-RELEASE: Deviced successfully closed.\n", DEVICE_NAME);
   return 0;
}

module_init(readWrite_init);
module_exit(readWrite_exit);
