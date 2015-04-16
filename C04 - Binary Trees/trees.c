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
void generateBST();

int main () {


    return 0;
}
