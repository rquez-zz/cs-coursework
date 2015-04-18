/*
 * Trees.c
 *
 * Created On: April 15, 2015
 *     Author: Ricardo Vasquez
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct binTreeNode
{
    int data;
    struct binTreeNode* right;
    struct binTreeNode* left;
};

void printPreOrder(struct binTreeNode* root);
void printInOrder(struct binTreeNode* root);
void printPostOrder(struct binTreeNode* root);
void printBreathFirst(struct binTreeNode* root);
int find(struct binTreeNode* root, int value);
int findMin(struct binTreeNode* root);
int findMax(struct binTreeNode* root);
int average(struct binTreeNode* root);
int median(struct binTreeNode* root);
int sum(struct binTreeNode* root);
int count(struct binTreeNode* root);
int delete(struct binTreeNode* root);
int* getIntArrayFromFile();
int* sortIntArray(int* intArray, int length);
struct binTreeNode* generateBST(int* sortedIntArray, int low, int high);
int getIntArrayLength();

int getIntArrayLength() {
    // Open file
    FILE *filePtr = fopen("AssignmentFourInput.txt", "r");

    // Error opening file
    if(filePtr == NULL) {
        perror("Error opening file.");
        return 0; 
    }

    // Count the number of integers in the file
    int numCount = 0;    
    int num;
    while ( fscanf((FILE*)filePtr, "%d", &num) != EOF)
        numCount = numCount + 1;
    fclose(filePtr);
    return numCount; 
}

int* getIntArrayFromFile(int length) {
    // Open File
    FILE *filePtr = fopen("AssignmentFourInput.txt", "r");
    
    // Make the unsorted integer array
    int *intArray = malloc(length * sizeof(int));

    // Read Array from File
    int i, num;
    for (i = 0; i < length; i++) {
        fscanf((FILE*)filePtr, "%d", &num);
        intArray[i] = num;
    }
    
    fclose(filePtr); 
    return intArray; 
}

int* sortIntArray(int* unsortedArray, int length) {
    
    int i, g, temp;
    
    for (i = 1; i < length; i++) {
        
        g = i; 
        
        // Switch elements when the previous is greater than the one in front
        while (g > 0 && unsortedArray[g] < unsortedArray[g-1]) {
            temp = unsortedArray[g];
            unsortedArray[g] = unsortedArray[g-1];
            unsortedArray[g-1]= temp;
            g--;
        }
    
    } 

    return unsortedArray;
}
struct binTreeNode* generateBST(int* sortedIntArray, int low, int high) {
    
    /*
     * When the high index isn't equal to the low index, that means
     * isn't a value to choose from in the array since the indices crossed,
     * so return null so that leaves are properly made. 
     */
    if (high > low) {
        int mid = (low + high) / 2; 
        struct binTreeNode* node = malloc(sizeof(struct binTreeNode));
        printf("DEBUG - NODE VALUE:%d LOW:%d HIGH:%d\n", sortedIntArray[mid], low, high);
        node->data = sortedIntArray[mid];
        node->left = generateBST(sortedIntArray, low, mid-1);
        node->right = generateBST(sortedIntArray, mid+1, high);
        return node;
    } else if (high == low) { 
        // High = Low so there's only 1 value to choose from
        struct binTreeNode* node = malloc(sizeof(struct binTreeNode));
        printf("DEBUG - NODE VALUE:%d LOW:%d HIGH:%d\n", sortedIntArray[high], low, high);
        node->data = sortedIntArray[high];
        node->left = NULL;
        node->right = NULL;
        return node;
    } else {
        // High < Low so there isn't a value for this node
        return NULL;
    }
}

void printPreOrder(struct binTreeNode* root) {

    if (root != NULL) {
        printf("%d ", root->data);
        printPreOrder(root->left);
        printPreOrder(root->right);
    }
}

void printInOrder(struct binTreeNode* root) {

    if (root != NULL) {
        printInOrder(root->left);
        printf("%d ", root->data);
        printInOrder(root->right);
    }
}

void printPostOrder(struct binTreeNode* root) {

    if (root != NULL) {
        printPostOrder(root->left);
        printPostOrder(root->right);
        printf("%d ", root->data);
    }
}
int main () {

    int arrayLength = getIntArrayLength();
    int* unsortedArray = getIntArrayFromFile(arrayLength);
    int* sortedArray = sortIntArray(unsortedArray, arrayLength);
    printf("DEBUG - ARRAY LENGTH:%d\n", arrayLength);
    struct binTreeNode* rootNode = generateBST(sortedArray, 0, arrayLength - 1);
    printf("Pre Order\n");
    printPreOrder(rootNode);
    printf("\nIn Order\n");
    printInOrder(rootNode);
    printf("\nPost Order\n");
    printPostOrder(rootNode);
    return 0;
}
