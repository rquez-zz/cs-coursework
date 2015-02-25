/*
 * groceries.c
 *
 *  Created on: Feb 18, 2015
 *      Author: Ricardo Vasquez
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct produceItem {

	char produce[20];
	char type[20];
	char soldBy[20];
	float price;
	int quantityInStock;
	struct produceItem *next;
};

// Output %5d %3 %-13s %-16s %6.2f %8d \n

void stock_produce_department();
void display_inventory();
void reverse_inventory();
void export_inventory();
void insert(char* produce, char* type, char* soldBy, float price);

int main () {

	// Set stream buffer for eclipse console
	setvbuf(stdout, NULL, _IONBF, 0);
	setvbuf(stderr, NULL, _IONBF, 0);

	char * charPrice = "1.29";
	//float price = strtof(charPrice);
	//printf("%.2f", price);

	//menu();

	return 0;
}

int menu() {

	printf("List Operations\n");
	printf("===============\n");
	printf("1. Stock Produce Department\n");
	printf("2. Display Produce Inventory\n");
	printf("3. Reverse Order of Produce Inventory\n");
	printf("4. Export Produce Inventory\n");
	printf("5. Exit Program\n");

	int choice;

	switch(choice) {
		case 1:
			stock_produce_department();
			break;
		case 2:
			//display_inventory();
			break;
		case 3:
			//reverse_inventory();
			break;
		case 4:
			//export_inventory();
			break;
		case 5:
			return 0;
			break;
		default:
			break;
	}

	menu();

	return 0;
}

void stock_produce_department() {

	// Pointer to file
	FILE *ptrFile;

	// Stores one line from the file
	char str[100];

	/*
	 * Assume input.txt exists in the
	 * same directory this source file
	 */
	ptrFile = fopen("AssignmentTwoInput.txt" , "r");

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
		char *produce = NULL;
		char *type = NULL;
		char *soldBy = NULL;
		char *char_price = NULL;

		token = (char *)strtok(str, ",");
		produce = token;

		token = strtok(NULL, ",");
		type = token;

		token = strtok(NULL, ",");
		soldBy = token;

		token = strtok(NULL, ",");
		char_price = token;

		//float price = strtof(price);

		//insert(produce, type, soldBy, price);

	}

}
