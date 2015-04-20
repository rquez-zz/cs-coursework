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

struct nodeInQueue
{
    struct binTreeNode* data;
    struct nodeInQueue* next;
};

int getIntArrayLength();
int* getIntArrayFromFile();
int* sortIntArray(int* intArray, int length);
struct binTreeNode* generateBST(int* sortedIntArray, int low, int high);
void printPreOrder(struct binTreeNode* root);
void printInOrder(struct binTreeNode* root);
void printPostOrder(struct binTreeNode* root);
void printBreathFirst(struct nodeInQueue* mainQueue);
int find(struct binTreeNode* root, int value);
int findMin(struct binTreeNode* root);
int findMax(struct binTreeNode* root);
int average(struct binTreeNode* root);
int median(struct binTreeNode* root);
int sum(struct binTreeNode* root);
int count(struct binTreeNode* root);
struct binTreeNode* delete(struct binTreeNode* root, int value);
struct binTreeNode* getNode(struct binTreeNode* root, int value); 

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
        node->data = sortedIntArray[mid];
        node->left = generateBST(sortedIntArray, low, mid-1);
        node->right = generateBST(sortedIntArray, mid+1, high);
        return node;
    } else if (high == low) { 
        // High = Low so there's only 1 value to choose from
        struct binTreeNode* node = malloc(sizeof(struct binTreeNode));
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

void printBreathFirst(struct nodeInQueue* node) {
   
    // Get first in queue
    struct nodeInQueue* firstInQueue = node;
    while ( firstInQueue->next != NULL) {
        firstInQueue = firstInQueue->next;
    } 

    // Print first in queue
    printf("%d ", (firstInQueue->data)->data); 

    
    // Enqueue left node to the back 
    struct nodeInQueue* leftNode;
    if ((firstInQueue->data)->left != NULL) {

        // Allocate Memory
        leftNode = malloc(sizeof(struct nodeInQueue)); 
        leftNode->data = (firstInQueue->data)->left;
        //printf("LEFT NODE DATA:%d\n", (leftNode->data)->data);

        // The case when the 1 node in the queue is printed
        if (firstInQueue == node)
            leftNode->next = NULL;
        else
            leftNode->next = node;
    }
    
    // The node behind the first need to be identified so that
    // it can be used to dequeue the front of the queue.
    struct nodeInQueue* secondInQueue = node;
    while ( node != firstInQueue && secondInQueue->next != firstInQueue) {
        secondInQueue = secondInQueue->next;
    }
    
    // Enqueue right node to the back
    if ((firstInQueue->data)->right != NULL) { 

        // Allocate memory
        struct nodeInQueue* rightNode = malloc(sizeof(struct nodeInQueue));
        rightNode->data = (firstInQueue->data)->right;
        //printf("RIGHT NODE DATA:%d\n", (rightNode->data)->data);

        // The case when the node doesn't have a left child
        if ((firstInQueue->data)->left != NULL) 
            rightNode->next = leftNode; 
        else { 
           rightNode->next = node;
        }

        // Dequeue and free
        secondInQueue->next = NULL;
        free(firstInQueue);

        printBreathFirst(rightNode);
    } else {
        
        // Dequeue
        secondInQueue->next = NULL;

        // The case when the node has a left but not a right child
        if ((firstInQueue->data)->left != NULL) {

            // Dequeue
            free(firstInQueue);
            printBreathFirst(leftNode);
        } else {
            
            // Dequeue
            free(firstInQueue); 

            // Recursion ends when there's only 1 node to print
            if (secondInQueue != firstInQueue)
                printBreathFirst(node);
        }
    }
}

int find(struct binTreeNode* root, int value) {
    
    if (root != NULL) {
        if (root->data > value) {
            if (find(root->left, value) == 1)
                return 1;
        } else if (root->data < value) {
            if (find(root->right, value) == 1)
                return 1;
        } else {
            return 1;
        }
        return 0;
    }
}

int findMin(struct binTreeNode* root) {

    if (root != NULL) {
        // Get left most node            
        if (root->left != NULL) {
            findMin(root->left);
        } else {
            return root->data;
        }
    } else {
        return 0;
    }
}

int findMax(struct binTreeNode* root){

    if (root != NULL) {
        // Get right most node 
        if (root->right != NULL) {
            findMax(root->right);
        } else {
            return root->data;
        }
    } else {
        return 0;
    }
}

int average(struct binTreeNode* root) {
    return sum(root) / count(root);
}

int sum(struct binTreeNode* root) {

    if (root != NULL) {
        return root->data + sum(root->left) + sum(root->right);
    } else {
        return 0;
    }
}

int count(struct binTreeNode* root) {

    if (root != NULL) {
        return 1 + count(root->left) + count(root->right);
    } else {
        return 0;
    }
}

int median(struct binTreeNode* root) {
    //WIP
    return root->data;
}

struct binTreeNode* getNode(struct binTreeNode* root, int value) {

    if (root != NULL) {
        if (root->data > value) {
            return getNode(root->left, value);
        } else if (root->data < value) {
            return getNode(root->right, value);
        } else {
            return root;
        }
        return 0;
    }

}

struct binTreeNode* delete(struct binTreeNode* root, int value) {
    
    printf("ROOT: %d\n", root->data);    
    if (root != NULL) {
        // When the left child of this parent matches 
        if (root->left != NULL && (root->left)->data == value) {
            root->left = delete(root->left, value);
            return root;
        } else 
        
        // When the right child of this parent matches
        if (root->right != NULL && (root->right)->data == value) {

            // Let the parent's right child become either NULL
            // OR grandchild
            root->right = delete(root->right, value);
            return root;
        } else 

        // Otherwise move down the tree
        if (root->data > value) {
            return delete(root->left, value);
        } else if (root->data < value) { 
            return delete(root->right, value);
        } else {
            
            // When reached, the current root is to be deleted
            printf("MATCH: %d\n", root->data);
                
            // Node has both left and right child
            if (root->left != NULL && root->right != NULL) {
                printf("NODE HAS BOTH\n");    

                // Get the value of the node with the highest value in the 
                // left subtree and save it.
                int temp = getNode(root, findMax(root->left))->data;

                // Delete the node with that temp value
                delete(root,temp);

                // Replace the root's value the temp value
                root->data = temp;
                return root; 
            } else 
            
            // OR Node has either left of right child    
            if (root->left != NULL || root->right != NULL) { 
                printf("NODE HAS EITHER\n");
                if (root->left != NULL) {
                    struct binTreeNode* node = root->left;
                    free(root);
                    return node;
                } else { 
                    struct binTreeNode* node = root->right;
                    free(root);
                    return node;
                }
            } 
            
            // Otherwise node is a leaf
            else {
                printf("LEAF MATCH: %d\n", root->data);
                struct binTreeNode* node = root;
                free(node);
                node = NULL;
                return node;
            }

        }
    } else {
        return 0;
    }
}

int main () {

    int arrayLength = getIntArrayLength();
    int* unsortedArray = getIntArrayFromFile(arrayLength);
    int* sortedArray = sortIntArray(unsortedArray, arrayLength);
    struct binTreeNode* rootNode = generateBST(sortedArray, 0, arrayLength - 1);
    printf("Pre Order\n");
    printPreOrder(rootNode);
    printf("\nIn Order\n");
    printInOrder(rootNode);
    printf("\nPost Order\n");
    printPostOrder(rootNode);
    printf("\n\n");
    struct nodeInQueue* firstNode = malloc(sizeof(struct nodeInQueue));
    firstNode->data = rootNode;
    firstNode->next = NULL;
    printBreathFirst(firstNode);
    if (find(rootNode, 101) == 1) 
        printf("Value found\n");
    else
        printf("Value not Found\n");
    printf("MIN: %d\n", findMin(rootNode));        
    printf("MAX: %d\n", findMax(rootNode));        
    printf("AVERAGE: %d\n", average(rootNode));        
    printf("SUM: %d\n", sum(rootNode));        
    printf("COUNT: %d\n", count(rootNode));        
    printf("MEDIAN: %d\n", median(rootNode));        
    printf("DELETED? %d\n", delete(rootNode, 1)->data);
    printf("Pre Order\n");
    printPreOrder(rootNode);
    printf("DELETED? %d\n", delete(rootNode, 4)->data);
    printf("Pre Order\n");
    printPreOrder(rootNode);
    printf("DELETED? %d\n", delete(rootNode, 21)->data);
    printf("Pre Order\n");
    printPreOrder(rootNode);
    printf("DELETED? %d\n", delete(rootNode, 87)->data);
    printf("Pre Order\n");
    printPreOrder(rootNode);
    return 0;
}
