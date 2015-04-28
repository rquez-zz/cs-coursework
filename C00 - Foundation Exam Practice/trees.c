

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct binTreeNode 
{
    int data;
    struct binTreeNode* left;
    struct binTreeNode* right;
};

struct binTreeNode* generateTree() {

    struct binTreeNode* root = malloc(sizeof(struct binTreeNode));
    struct binTreeNode* leftRoot = malloc(sizeof(struct binTreeNode)); 
    struct binTreeNode* rightRoot = malloc(sizeof(struct binTreeNode)); 
    struct binTreeNode* leftLeftRoot = malloc(sizeof(struct binTreeNode)); 
    struct binTreeNode* leftRightRoot = malloc(sizeof(struct binTreeNode)); 
    struct binTreeNode* rightLeftRoot = malloc(sizeof(struct binTreeNode)); 
    struct binTreeNode* rightRightRoot = malloc(sizeof(struct binTreeNode)); 
    root->data = 64;
    leftRoot->data = 100;
    rightRoot->data = 201;
    leftLeftRoot->data = 68;
    leftRightRoot->data = 90;
    rightLeftRoot->data = 11;
    rightRightRoot->data = 211;
    
    root->left = leftRoot;
    root->right = rightRoot;
    
    leftRoot->left = leftLeftRoot;
    leftRoot->right = leftRightRoot;

    rightRoot->left = rightLeftRoot;
    rightRoot->right = rightRightRoot;

    return root;
}

int numLessThan(struct binTreeNode* root, int n) {
    
    if (root != NULL) {
        int sum = 0;
        if (root->data < n) 
            sum++;
        return sum + numLessThan(root->left, n) + numLessThan(root->right, n);
    } else {
        return 0;
    }
}

int main() {

    struct binTreeNode* root = generateTree();
    printf("%d \n", numLessThan(root, 1000));

    return 0;
}
