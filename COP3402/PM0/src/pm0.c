#include "pm0.h"

int main(int argc, char **argv) {

    // Open file using first argument
    const char* inputPath = argv[1];
    printf("Opening %s for input.\n", inputPath);
    FILE* inputPtr= openFile(inputPath);

    // Open output file using second argument
    const char* outputPath = argv[2];
    printf("Opening %s for output.\n", outputPath);
    FILE* outputPtr= openFile(outputPath);

    // Perform Stack Operations
    stack(inputPtr, outputPtr);

    // Close file
    printf("Closing input file.\n");
    fclose(inputPtr);
    fclose(outputPtr);

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
