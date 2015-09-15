#include "pm0.h"

int main(int argc, char **argv) {

    // Open file using first argument
    const char* inputPath = argv[1];
    printf("Opening %s for input.\n", inputPath);
    FILE* filePtr = openFile(inputPath);

    // Perform Stack Operations
    stack(filePtr);

    // Close file
    printf("Closing input file.");
    fclose(filePtr);

    return 0;
}

/* Opens a file and returns a FILE pointer */
FILE* openFile(const char* path) {
    FILE* filePtr;
    filePtr = fopen(path, "r");
    if(filePtr == NULL) {
        perror("Error opening input file.");
    }
    return filePtr;
}
