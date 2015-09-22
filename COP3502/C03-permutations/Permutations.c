/*
 * Permutations.c
 *
 * Created On: March 29th, 2015
 *     Author: Ricardo Vasquez
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void read_in_file();
void recursive_permute(char str[], int k);
void exchange_character(char str[], int i, int j);
void export_file(char* results);
void write_to_file();
char* permutations;

int main () {
  
    // Allocate space 
    permutations = malloc(500000);
    
    read_in_file(); 
    
    return 0;
}

void read_in_file() {

    FILE *ptr_file;
    
    // Open file
    printf("Trying to open file AssignmentThreeInput.txt\n");
    ptr_file = fopen("AssignmentThreeInput.txt", "r");

    char str[10];

    // Error opening file
    if(ptr_file == NULL) {
        perror("Error opening file.");
        return;
    }

    // Permutation loop
    while ( fgets(str, 10, (FILE*)ptr_file) != NULL ) {
    
        // Remove the \n from string
        char* token = (char *)strtok(str, "\n");
    
        printf("Permutating %s\n\n", str);
        
        // Permutate string
        recursive_permute(token, 0);    
    }

    // Write results to file
    write_to_file(permutations);
    
    // Close file
    fclose(ptr_file);
}

void exchange_characters(char str[], int i, int j) {

    // Switch positions of chars in string
    char temp = str[i];
    str[i] = str[j];
    str[j] = temp;
}

void recursive_permute(char str[], int k) {
   
 
    int j;
    // Base case
    // Concat to permutations string when k is
    // equal to the length of the string
    if (k == strlen(str)) {
        strcat(permutations, str);
        strcat(permutations, "\n");
    } else {
        for (j = k; j < strlen(str); j++) {
            exchange_characters(str, k, j);
            recursive_permute(str, k+1);
            exchange_characters(str, j ,k);
        }
    }
}

void write_to_file() {

    char* file_name = "AssignmentThreeOutput.txt";

    // Open file
    FILE *ptr_file = fopen(file_name , "w");

    // Check if it exists
    if (ptr_file == NULL) {
        perror("Error opening file!\n");
        return;
    }

    // Write string to file
    fprintf(ptr_file, "%s", permutations);

    printf("Processed input and wrote output!\n");

    // Close file
    fclose(ptr_file);
}
