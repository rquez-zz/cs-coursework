### crcheck
Calculates and verifies the CRC16 of a 512 byte or less file using the following polynomial. 
`x^15 + x^13 + x^6 + x^4 + x + 1`

##### Compile
`javac crcheck.java`

#### Run
Two arguments are needed. Calculation or verification flag and filename of the input to perform operations. 

`java crcheck c in2.raw`
`java crcheck v in2.crc`

`java crcheck c ws.raw`
`java crcheck v ws.crc`
