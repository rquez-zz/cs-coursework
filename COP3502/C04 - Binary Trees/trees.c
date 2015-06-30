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
int median(struct binTreeNode* root, int halfSize);
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

int median(struct binTreeNode* root, int halfSize) {
    // Find the median is the same as finding the (n/2)th largest node
    if (root == NULL) {
        return 0;
    }

    int rightTreeSize = 0;
   
    // Count nodes in right subtree 
    if(root->right != NULL) {
        rightTreeSize = count(root->right);
    }

    // Root is median
    if (halfSize == rightTreeSize + 1) {
        return root->data;
    }

    if (halfSize > rightTreeSize) {
        return median(root->left, (halfSize - rightTreeSize - 1));
    } else {
        return median(root->right, halfSize);
    }

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
            
            // Node has both left and right child
            if (root->left != NULL && root->right != NULL) {

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

    int exit = 0;

	while (exit == 0) {

        // Print Menu
		printf("List Operations\n");
		printf("===============\n");
		printf("1. Generate Binary Search Tree\n");
		printf("2. Print the BST in pre-order format\n");
		printf("3. Print the BST in in-order format\n");
		printf("4. Print the BST in post-order format\n");
		printf("5. Print the BST in breath-first format\n");
		printf("6. Find a value in the BST\n");
		printf("7. Find the miniumum value in the BST nodes\n");
		printf("8. Find the maximum value in the BST nodes\n");
		printf("9. Calculate the average value of the BST nodes\n");
        printf("10. Find the median value of the BST nodes\n");
        printf("11. Calculate the sum of the BST nodes\n");
        printf("12. Count the number of BST nodes\n");
        printf("13. Delete a value in the BST\n");
        printf("14. Exit Program\n");

		//Prompt User Input
		int menuChoice = 1;
		printf("Enter your choice: ");
		scanf("%d", &menuChoice);

        // Initialize Variables
        int arrayLength;
        int* unsortedArray;
        int* sortedArray;
        struct binTreeNode* rootNode;

		switch(menuChoice) {
			case 1: // Generate Binary Tree
                arrayLength = getIntArrayLength();
                unsortedArray = getIntArrayFromFile(arrayLength);
                sortedArray = sortIntArray(unsortedArray, arrayLength);
                rootNode = generateBST(sortedArray, 0, arrayLength - 1);
                printf("Binary search tree generated.\n\n");
				break;
			case 2: // Print Pre-Order
                printf("Pre Order\n");
                printPreOrder(rootNode);
                printf("\n\n");
				break;
			case 3: // Print In-Order
                printf("In Order\n");
                printInOrder(rootNode);
                printf("\n\n");
				break;
			case 4: // Print Post-Order
                printf("Post Order\n");
                printPostOrder(rootNode);
                printf("\n\n");
				break;
            case 5: { // Print Breath-First
                struct nodeInQueue* firstNode = 
                    malloc(sizeof(struct nodeInQueue));
                firstNode->data = rootNode;
                firstNode->next = NULL;
                printf("Breath First\n");
                printBreathFirst(firstNode);
                printf("\n\n");
				break;
            }
            case 6: // Find Value
                printf("Enter value to find: ");
                int value;
                scanf("%d", &value);
                if (find(rootNode, value) == 1) 
                    printf("%d was found\n\n", value);
                else
                    printf("%d was not Found\n\n", value);
                break;
            case 7: // Find Min
                printf("Minimum: %d\n\n", findMin(rootNode));        
                break;
            case 8: // Find Max
                printf("Maximum: %d\n\n", findMax(rootNode));        
                break;
            case 9: // Calculate Average
                printf("Average: %d\n\n", average(rootNode));        
                break;
            case 10: // Find Median
                printf("Median: %d\n\n", 
                        median(rootNode, (count(rootNode)/2)));        
                break;
            case 11: // Calculate Sum
                printf("Sum: %d\n\n", sum(rootNode));        
                break;
            case 12: // Count Nodes
                printf("Count: %d\n\n", count(rootNode));        
                break;
            case 13: // Delete 
                printf("Enter value to delete: ");
                int valueNode;
                scanf("%d", &valueNode);
                if (find(rootNode, valueNode) == 0) {
                    printf("%d is not in the BST.\n\n", valueNode);
                } else {
                    delete(rootNode, valueNode);
                    printf("%d was deleted\n\n", valueNode);
                }
                break;
            case 14: // Exit
                exit = 1;
                break;
			default:
				break;
		}
	}

    return 0;
}
