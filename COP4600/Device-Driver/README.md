read_write Character LKM
========================

### Compile
* `make`

### Register Module
* `insmod read_write.ko`
* Find the major number in logs with `tail /var/log/kern.log`
* `mknod /dev/read_write c {major number} 0`

### Test
* `./test_read_write`
