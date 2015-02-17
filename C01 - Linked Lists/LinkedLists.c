/*
 * LinkedLists.c
 *
 *  Created on: Jan 26, 2015
 *      Author: Ricardo Vasquez
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct node{

	/*
	 * Using a doubly linked list
	 * to make delete operations
	 * easier.
	 */
	char name[50];
	char firstName[20];
	char middleName[8];
	char lastName[20];

	int id;

	struct node * next;
	struct node * previous;

} Node;

// Prototypes
void readInFile(Node *head);
void menu(Node *head);
void insert(Node *head, char* firstName, char* middleName, char* lastName, int id);
void display(Node *head);
void deleteByID(Node *head, int* id);
void deleteByName(Node *head, char* name);
void delete(Node **currentNode);
void clearStdin();

int main() {

	// Set stream buffer for eclipse console
	setvbuf(stdout, NULL, _IONBF, 0);
	setvbuf(stderr, NULL, _IONBF, 0);

	// Create start of list
	Node *head = (Node*)malloc(sizeof(Node));

	// Read in AssignmentOneInput.txt
	readInFile(head);

	// Loop through menu
	menu(head);

	// Exit
	return 0;
}

/*
 * Reads in AssignmentOneInput.txt and
 * creates the node for each line and
 * pushes it to the front of the list
 */
void readInFile(Node *head) {

	// Pointer to file
	FILE *ptrFile;

	// Stores one line from the file
	char str[100];

	/*
	 * Assume input.txt exists in the
	 * same directory this source file
	 */
	ptrFile = fopen("AssignmentOneInput.txt" , "r");

	// Check if file exists
	if(ptrFile == NULL) {
		perror("Error opening file.");
		return;
	}


	/*
	 * Read in each line in the file as long
	 * as there is a line to read. Each line
	 * is stored in the str array
	 */
	while ( fgets(str, 100, (FILE*)ptrFile) != NULL ){

		// Initalize pointers for the following operations
		char *token = NULL;
		char *firstName = NULL;
		char *middleName = NULL;
		char *lastName = NULL;
		char *idString = NULL;

		/*
		 * strtok places a \0 in str where the first
		 * whitespace is found and returns a pointer
		 * to the chars before the \0 as the first
		 * token. The result is the first name.
		 */
		token = (char *)strtok(str, " ");
		firstName = token;

		/*
		 * Here strtok returns the next token
		 * which is between the previous \0
		 * and the next whitespace which
		 * becomes a \0 in the original
		 * str array. The result is either
		 * the middle name or last name.
		 */
		token = strtok(NULL, " ");

		/*
		 * If a comma exists at the end
		 * of this token, then it's
		 * the last name. The comma will be
		 * overwritten in the str array with
		 * a terminating character.
		 *
		 * Otherwise the token is a middle name.
		 * If there's no middle name, the pointer
		 * remains as NULL.
		 */
		if ( strpbrk(token, ",") != NULL) {
			lastName = token;
			lastName[strlen(lastName) - 1] = '\0';
		} else {
			middleName = token;
		}

		/*
		 * The next token returned is either
		 * the last name or the ID. The same
		 * logic from the previous token is
		 * used to determine if this is
		 * the last name or the ID.
		 *
		 * The token either ends in a space
		 * or ends with a \r.

		 */
		token = strtok(NULL, " \r");
		if ( strpbrk(token, ",") != NULL) {
			lastName = token;
			lastName[strlen(lastName) - 1] = '\0';
		} else {
			idString = token;
		}

		/*
		 * If the final token is just \n, then
		 * we've reach the end of the line and
		 * the idString has already been read.
		 *
		 * Otherwise this final token will be
		 * the idString
		 */
		token = strtok(NULL, " ");
		if (strcmp(token,"\n") != 0) {
			idString = token;
		}

		/*
		 * Use pointers to populate the
		 * fields of the Node and insert it
		 * to the front of the list
		 *
		 * idString is converted from char pointer
		 * to int before being passed to the function
		 */
		insert(head, firstName, middleName, lastName, (int)atoi(idString));
	}

	// Close File
	fclose(ptrFile);
	ptrFile = NULL;
}

/*
 * Displays the menu, takes user input
 * and calls other functions. Calls itself
 * when the user is done with any of the
 * operations.
 */
void menu (Node *head) {

	// Display list of choices
	printf("List Operations\n===============\n");
	printf("1. Insert\n2. Display\n3. Delete by ID\n4. Delete by Name\n5. Exit\n");
	printf("Enter your choice: ");

	//Prompt User Input
	int menuChoice = 0;
	scanf("%d", &menuChoice);

	// Clear STDIN
	clearStdin();

	// Execute choice
	switch(menuChoice) {
		case 1: {

			// Allocate Memory and Initalize
			int id = 0;
			char* firstName = calloc(1, sizeof(char) * 20);
			char* middleName = calloc(1, sizeof(char) * 20);
			char* lastName = calloc(1, sizeof(char) * 20);

			// Prompt First Name
			printf("Enter a first name for the student.\n");
			scanf("%s", firstName);

			clearStdin();

			// Prompt Middle Name
			printf("Enter a middle name for the student (Enter \"none\" if none).\n");
			scanf("%s", middleName);

			clearStdin();

			// Nullify middleName if none
			if (strcmp(middleName,"none") == 0) {
				middleName = NULL;
			}

			// Prompt Last Name
			printf("Enter a last name for the student.\n");
			scanf(" %s", lastName);

			clearStdin();


			// Prompt ID
			printf("Enter an id for the student.\n");
			scanf(" %d", &id);

			// Insert student into list
			insert(head, firstName, middleName, lastName, id);
			break;
		}
		case 2:
			// Display all Students
			display(head);
			break;
		case 3: {

			// Initalize int that holds the id
			int id = 0;
			int *pID;

			// deleteByID takes in an int pointer
			pID = &id;

			// Prompt ID
			printf("Enter an id for the student.\n");
			scanf("%d", pID);

			clearStdin();

			// Delete student by ID
			deleteByID(head, pID);
			break;
		}
		case 4: {

			// Allocate memory for the name to find
			char * pName = (char *)calloc(1, sizeof(char) * 50);

			// Display Directions
			printf("Enter the first, last, or full name of a student to delete.\n");

			// Get input
			scanf("%s", pName);

			clearStdin();

			// Delete student by Name
			deleteByName(head, pName);

			// Free memory
			free(pName);
			pName = NULL;

			break;
		}
		case 5:
			// Exit Menu
			return;
		default :
			// Error message
			printf("Not a valid choice.\n");
	}

	// Return to Menu
	menu(head);
}

/*
 * Inserts a node at the front of the
 * linked list.
 */
void insert (Node * head, char * firstName, char * middleName,
		char * lastName, int id) {

	// Allocate memory for the student Node
	Node *student = calloc(1,sizeof(Node));

	/*
	 * Assign values to student.
	 *
	 * For the names, the string token
	 * pointed by the char* are copied into
	 * the char array of the student struct.
	 * The last index of the array is terminated
	 * with \0.
	 */
	strncpy(student->firstName, firstName, sizeof(student->firstName));
	student->firstName[sizeof(student->firstName) - 1] = '\0';
	strcat(student->name, student->firstName);

	// Space between first and middle/last name
	strcat(student->name, " ");

	// Don't strncpy anything if a middle name doesn't exist
	if (middleName != NULL) {
		strncpy(student->middleName, middleName, sizeof(student->middleName));
		student->middleName[sizeof(student->middleName) - 1] = '\0';
		strcat(student->name, student->middleName);

		// Space between first and middle name
		strcat(student->name, " ");
	}

	// Copy last name into node
	strncpy(student->lastName, lastName, sizeof(student->lastName));
	student->lastName[sizeof(student->lastName) - 1] = '\0';
	strcat(student->name, student->lastName);

	// Terminate name string
	student->name[sizeof(student->name) - 1] = '0';

	// Copy id into node
	student->id = (int)id;

	/*
	 * Let the new node point to the node
	 * after the head node.
	 *
	 * In case of the first
	 * insert, the new node's next will
	 * point to null.
	 */
	student->next = head->next;

	// Put head back in 1st position
	head->next = student;

	// Let the new node point previously to head
	student->previous = head;

	/*
	 * If this new node is not the first
	 * insert, then it should have a node after
	 * it. If so, the node after this new
	 * node should point previously to this
	 * new node.
	 */
	if (student->next != NULL)
		(student->next)->previous = student;
}

/*
 * Traverses through each node
 * and displays their contents.
 */
void display(Node *head) {

	/*
	 * The following Node is the
	 * helper node that traverses
	 * through the linked list.
	 *
	 * The head Node is skipped
	 * since it doesn't hold relevant
	 * data.
	 */
	Node * currentNode = head->next;

	// Tranverse until next is null
	while (currentNode != NULL) {

		// Display
		printf("%s, %d\n", currentNode->name, currentNode->id);

		// Move to the next node
		currentNode = currentNode->next;
	}
}

/*
 * If id matches an id in
 * the linked list, delete that
 * Node.
 */
void deleteByID(Node * head, int * id) {

	/*
	 * Create a node to traverse through
	 * the linked list. Let this node
	 * point to the beginning of the
	 * linkedlist after head.
	 */
	Node * currentNode = head->next;

	/*
	 * 0 - Node not found
	 * 1 - Node was found
	 */
	int found = 0;

	/*
	 * Traverse through the linked list
	 * until the all the matching nodes
	 * are deleted or user exits.
	 */
	while (currentNode != NULL) {

		// If ID matches
		if (currentNode->id == *id) {

			// Node was found
			found = 1;

			/*
			 * Ask the user if they want to
			 * delete this particular student.
			 */
			char choice;
			printf("Student \"%s\" ID: %d found. Do you want to delete this student? (Y/N)\n", currentNode->name, currentNode->id);

			/*
			 * Read in choice.
			 *
			 * The space before the %c
			 * ignores whitespaces and new lines.
			 */
			scanf(" %c", &choice);

			// Ignore letter case and execute choice.
			if (choice == 'Y' || choice == 'y') {

				/*
				 * Before deleting, make a pointer
				 * that points to the node before
				 * the one we are going to delete,
				 * so that the traversal can continue
				 * at the end of this loop.
				 */
				Node * previousNode = currentNode->previous;

				// Delete student
				delete(&currentNode);

				// Check if node exists
				if (currentNode == NULL) {

					// Show user result
					printf("Student ID: %d was deleted.\n", *id);

					/*
					 * The the node after the previous node
					 * now points to the node was after
					 * currentNode. This assignment lets
					 * the loop continue as usual.
					 */
					currentNode = previousNode;

				} else {

					// Show error and return to menu
					printf("Error deleting Student \"%s\" ID: %d.\n", currentNode->name, *id);
					return;
				}

			} else if (choice == 'N' || choice == 'n') {

				// This either returns to menu or displays another result
				printf("Student \"%s\" ID: %d was not deleted.\n", currentNode->name, *id);

			} else {

				//Error message and return to menu
				printf("Not a valid respose.\n");
				return;
			}
		}

		// Move to the next node
		currentNode = currentNode->next;

	}

	// Ending message
	if (found == 0) {
		// Student not found
		printf("Student ID: %d was not found.\n", *id);
	}
}

/*
 * If name matches the first name, last name,
 * or full name of the Node in the linked list,
 * then that node will deleted.
 */
void deleteByName(Node * head, char * name) {

	/*
	 * Create a node to traverse through
	 * the linked list. Let this node
	 * point to the beginning of the
	 * linkedlist after head.
	 */
	Node * currentNode = head->next;
	Node * previousNode = NULL;

	// Holds the current full name in the loop
	char * fullName = NULL;

	/*
	 * 0 - Node not found
	 * 1 - Node was found
	 */
	int found = 0;

	/*
	 * Traverse through the linked list
	 * until the node we are looking for
	 * is deleted.
	 */
	while (currentNode != NULL) {

		/*
		 * Determine if the user inputed name matches
		 * the first name, last name, or full name.
		 */
		if ( (strcmp(name, currentNode->name) == 0) ||
				(strcmp(name, currentNode->firstName) == 0) ||
					(strcmp(name, currentNode->lastName) == 0) ) {

			// Node was found
			found = 1;

			// Verify if they want to delete.
			printf("Student \"%s\" ID: %d found. Do you want to delete this student? (Y/N)\n", currentNode->name, currentNode->id);

			/*
			 * Read in choice.
			 *
			 * The space before the %c
			 * ignores whitespaces and new lines.
			 */
			char choice;
			scanf(" %c", &choice);

			// Save the name before deleting
			fullName = (char *)malloc(1 + strlen(currentNode->name));
			strcpy(fullName, currentNode->name);
			fullName[strlen(fullName)] = '\0';

			// Execute choice
			if (choice == 'Y' || choice == 'y') {

				/*
				 * Before deleting, make a pointer
				 * that points to the node before
				 * the one we are going to delete,
				 * so that the traversal can continue
				 * at the end of this loop.
				 */
				previousNode = currentNode->previous;

				// Delete student
				delete(&currentNode);

				// Check if node exists
				if (currentNode == NULL) {

					// Show user node was deleted
					printf("Student \"%s\" was deleted.\n", fullName);

					/*
					 * The the node after the previous node
					 * now points to the node was after
					 * currentNode. This assignment lets
					 * the loop continue as usual.
					 */
					currentNode = previousNode;

				} else {

					//Error and return to menu
					printf("Error deleting student \"%s\"", fullName);
					return;
				}

			} else if (choice == 'N' || choice == 'n') {

				// This either returns to menu or displays another result
				printf("Student \"%s\" was not deleted.\n", fullName);

			} else {

				// Display error and return to menu
				printf("Not a valid respose.\n");
				return;
			}

			// Free Resources
			free(fullName);
			fullName = NULL;

		}

		// Move to the next node

		if (currentNode != NULL)
			currentNode = currentNode->next;
	}

	// Display the correct ending message
	if (found == 0) {
		// Student not found
		printf("Student \"%s\" not found.\n", name);
	}

}

/*
 * This function frees and nulfies both
 * the Node in memory and the pointer pointing
 * to that node.
 */
void delete(Node **currentNode) {

	// Get the previous node
	Node * previousNode = (*currentNode)->previous;

	// Let the previous point to the node after currentNode
	previousNode->next = (*currentNode)->next;

	/*
	 * Let the node after currentNode point previously to the node
	 * before currentNode. Only if this is not the last node in the list.
	 */
	if ((*currentNode)->next != NULL)
		((*currentNode)->next)->previous = (*currentNode)->previous;

	// Free currentNode and pointer to it
	free(*currentNode);
	*currentNode = NULL;
	currentNode = NULL;

}

/*
 * Used to clear the stdin so
 * that scanf isn't skipped or
 * picks up random characters
 */
void clearStdin() {
	while(getchar() != '\n');
}





