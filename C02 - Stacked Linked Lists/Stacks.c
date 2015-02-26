/*
 * groceries.c
 *
 *  Created on: Feb 18, 2015
 *      Author: Ricardo Vasquez
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct produce_item {

	char produce[20];
	char type[20];
	char sold_by[20];
	float price;
	int stock_quantity;
	struct produce_item *next;

};

void menu(struct produce_item** top);
void stock_produce_department(struct produce_item** top);
void display_inventory(struct produce_item** top);
void reverse_inventory();
void export_inventory();
void push(struct produce_item** top, char* name, char* type, char* sold_by, float price, int stock_quantity);
void pop(struct produce_item** top);
void clear_stdin();

int main () {

	// Set stream buffer for eclipse console
	setvbuf(stdout, NULL, _IONBF, 0);
	setvbuf(stderr, NULL, _IONBF, 0);

	// Create the top node of the stack
	struct produce_item *top = NULL;

	menu(&top);

	return 0;
}

void menu(struct produce_item** top) {

	// Print Menu
	printf("List Operations\n");
	printf("===============\n");
	printf("1. Stock Produce Department\n");
	printf("2. Display Produce Inventory\n");
	printf("3. Reverse Order of Produce Inventory\n");
	printf("4. Export Produce Inventory\n");
	printf("5. Exit Program\n");

	int exit = 1;

	stock_produce_department(top);
	display_inventory(top);
	reverse_inventory(top);
	display_inventory(top);

	while (exit == 0) {

		//Prompt User Input
		int menuChoice = 1;
		//scanf("%d", &menuChoice);

		// Clear STDIN
		//clear_stdin();

		switch(menuChoice) {
			case 1:
				stock_produce_department(top);
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
				// Exit Program
				exit = 1;
				break;
			default:
				break;
		}
	}
}

void clear_stdin() {
	while(getchar() != '\n');
}

void stock_produce_department(struct produce_item** top) {

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
		char *Produce_item = NULL;
		char *type = NULL;
		char *sold_by = NULL;

		float price;
		int stock_quantity;

		// Read Produce_item Name
		token = (char *)strtok(str, ",");
		Produce_item = token;

		// Read Produce_item Type
		token = strtok(NULL, ",");
		type = token;

		// Read Sold By
		token = strtok(NULL, ",");
		sold_by = token;

		// Read Produce_item Price as Char*
		token = strtok(NULL, ",");
		// Convert Char* to float
		price = (float)strtof(token, NULL);

		// Read Stock Quantity
		token = strtok(NULL, ",");
		// Convert Char* to int
		stock_quantity = (int)atoi(token);

		// Push into stack
		push(top, Produce_item, type, sold_by, price, stock_quantity);
	}
}

void push(struct produce_item** top, char* produce, char* type, char* sold_by, float price, int stock_quantity) {

	// Create new produce item
	struct produce_item *item = calloc(1, sizeof(struct produce_item));

	// Insert values into new item
	strncpy(item->produce,produce,sizeof(item->produce));
	strncpy(item->type,type,sizeof(item->type));
	strncpy(item->sold_by,sold_by,sizeof(item->type));
	item->price = price;
	item->stock_quantity = stock_quantity;

	// Check if this is the first push
	if (*top != NULL) {

		// Let item point to top node
		item->next = *top;

	}

	// Change top pointer to item
	*top = item;

}

void display_inventory(struct produce_item** top) {

	struct produce_item* helper = *top;

	int item_count = 1;

	// Print Header
	printf("===========================================================================\n");
	printf(" %-7s %-14s %-16s %-14s %-8s %-8s\n", "Item #", "Produce", "Type", "Sold By", "Price", "In Stock");
	printf("===========================================================================\n");

	while (helper->next != NULL) {
		// Print with formatting
		printf("   %-5d %-13s %-16s %-13s %6.2f %8d\n",
				item_count++,
				helper->produce,
				helper->type,
				helper->sold_by,
				helper->price,
				helper->stock_quantity);

		// Move to the next item in stack
		helper = helper->next;

	}
}

void reverse_inventory(struct produce_item** top) {

	// Base Case
	if ((*top)->next != NULL) {
		reverse_inventory(&((*top)->next));
	}

	// Reverse link between nodes
	(*top)->next = *top;


}

void export_inventory(struct produce_item** top) {


}

void pop (struct produce_item** top) {


}


