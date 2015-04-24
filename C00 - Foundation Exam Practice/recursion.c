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

    // After 'z' you get '{ } /' so subtract 26 to wrap around
    if (str[length - 1] > 'z')
        str[length - 1] -= 26;
    caesar_cipher(str, length - 1);
}

int oddRecPal(char* str, int sIndex, int eIndex) {

    if (sIndex != (eIndex - 1)) {

        if (str[sIndex] == str[eIndex - 1]) {
            return oddRecPal(str, sIndex + 1, eIndex - 1);
        } else {
            return 0; 
        }

    } else {
        // Middle of the string end recursion
        return 1;
    }

}

int main() {

    // CAESAR_CIPHER
    char str[26] = "abcdefghijklmnopqrstuvwxyz";
    caesar_cipher(str, 26);
    printf("%s\n", str);

    // Odd Recusive Palindrome
    char* palindrome = "aabaa";
    printf("%d\n", oddRecPal(palindrome, 0, 5));
    palindrome = "aaaa";
    printf("%d\n", oddRecPal(palindrome, 0, 4));
    palindrome = "abacabazabacaba";
    printf("%d\n", oddRecPal(palindrome, 0, 15));
    return 0; 
}

