/*
 * Trees.c
 *
 * Created On: April 15, 2015
 *     Author: Ricardo Vasquez
 */

#include <stdio.h>
#include <stdlib.h>

struct binTreeNode
{
    int data;
    struct binTreeNode* right;
    struct binTreeNode* left;
};

// Generate BST
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
int* getIntArrayFromFile(char* fileName);
int* sortIntArray(int* intArray);
struct binTreeNode* generateBST(int* sortedIntArray);
int getMedian(int* sortedIntArray);
int* getLeftHalf(int* sortedIntArray);
int* getRightHalf(int* sortedIntArray);

struct binTreeNode* generateBST(int* sortedIntArray) {
    
    // Create the node
    struct binTreeNode* node = malloc(sizeof(struct binTreeNode));
    
    // Get the median value in the sorted array
    int medianValue = getMedian(sortedIntArray);
    node->data = medianValue;
    
    // Get the left half of array
    int* leftHalf = getLeftHalf(sortedIntArray);
    node->left = generateBST(leftHalf);

    // Get the right half of array
    int* rightHalf = getRightHalf(sortedIntArray);
    node->right = generateBST(rightHalf);
    
    return node;  
}

int main () {


    return 0;
}
