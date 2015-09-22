

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

struct binTreeNode* generateBST() {

    struct binTreeNode* root = malloc(sizeof(struct binTreeNode));
    struct binTreeNode* leftRoot = malloc(sizeof(struct binTreeNode)); 
    struct binTreeNode* rightRoot = malloc(sizeof(struct binTreeNode)); 
    struct binTreeNode* leftLeftRoot = malloc(sizeof(struct binTreeNode)); 
    struct binTreeNode* leftRightRoot = malloc(sizeof(struct binTreeNode)); 
    struct binTreeNode* rightLeftRoot = malloc(sizeof(struct binTreeNode)); 
    struct binTreeNode* rightRightRoot = malloc(sizeof(struct binTreeNode)); 
    root->data = 64;
    leftRoot->data = 32;
    rightRoot->data = 80;
    leftLeftRoot->data = 12;
    leftRightRoot->data = 47;
    rightLeftRoot->data = 75;
    rightRightRoot->data = 101;
    
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

int numNodes(struct binTreeNode* ptr) {
    if (ptr == NULL)
        return 0;
    else 
        return 1 + numNodes(ptr->left) + numNodes(ptr->right);
}

int rank(struct binTreeNode* ptr, int k) {
    // Get the number of nodes in the left tree
    int nodeRank = numNodes(ptr->left);
    
    if (nodeRank == k-1) 
        return ptr->data;
    // If there's more nodes in the left tree than the k number go left
    else if (nodeRank > k-1)
        return rank(ptr->left, k);
    // If there's less nodes in the left tree thank k number go right
    else 
        return rank(ptr->right,k-nodeRank-1);
}


int main() {

    struct binTreeNode* root = generateTree();
    struct binTreeNode* BSTroot = generateBST();
    printf("%d \n", numLessThan(root, 1000));
    printf("%d \n", rank(BSTroot, 1));
    return 0;
}
