#include "pm0.h"

int main(int argc, char **argv) {

    // Get path for input file
    const char* inputPath = argv[1];

    // Get path for output file
    const char* outputPath = argv[2];

    // Perform Stack Operations
    printf("[LOG] Performing stack operations\n");
    stack(inputPath, outputPath);

    return 0;
}
