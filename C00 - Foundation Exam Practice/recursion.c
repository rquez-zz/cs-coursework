#include <stdio.h>
#include <stdlib.h>

/* CS Part A May 09 Q1 */ 
void caesar_cipher(char* str, int length) {
    // str[0] is already covered
    if (length <= 0) {
        return;
    }

    // You can augment to a char
    str[length - 1] += 3;
    printf("%c\n", str[length - 1]);

    // After 'z' you get '{ } /' so subtract 26 to wrap around
    if (str[length - 1] > 'z')
        str[length - 1] -= 26;
    caesar_cipher(str, length - 1);
}

int main() {

    // CAESAR_CIPHER
    char str[26] = "abcdefghijklmnopqrstuvwxyz";
    caesar_cipher(str, 26);
    printf("%s\n", str);

    
    return 0; 
}

