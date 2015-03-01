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
char* inventory_table(struct produce_item** top);
void reverse_inventory(struct produce_item** top, struct produce_item** prev_top);
void export_inventory();
void push(struct produce_item** top, char* name, char* type, char* sold_by, float price, int stock_quantity);
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

	int exit = 0;

	while (exit == 0) {

		// Print Menu
		printf("List Operations\n");
		printf("===============\n");
		printf("1. Stock Produce Department\n");
		printf("2. Display Produce Inventory\n");
		printf("3. Reverse Order of Produce Inventory\n");
		printf("4. Export Produce Inventory\n");
		printf("5. Exit Program\n");

		//Prompt User Input
		int menuChoice = 1;
		printf("Enter your choice: ");
		scanf("%d", &menuChoice);

		switch(menuChoice) {
			case 1:
				stock_produce_department(top);
				break;
			case 2:
				printf("%s", (char*)inventory_table(top));
				break;
			case 3:
				reverse_inventory(top, &((*top)->next));
				printf("Produce inventory has been reversed.\n");
				break;
			case 4:
				export_inventory(top);
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
	FILE *ptr_file;

	// Stores one line from the file
	char str[100];

	/*
	 * Assume input.txt exists in the
	 * same directory this source file
	 */
	ptr_file = fopen("AssignmentTwoInput.txt" , "r");

	// Check if file exists
	if(ptr_file == NULL) {
		perror("Error opening file.");
		return;
	}

	/*
	 * Read in each line in the file as long
	 * as there is a line to read. Each line
	 * is stored in the str array
	 */
	while ( fgets(str, 100, (FILE*)ptr_file) != NULL ){

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

	fclose(ptr_file);
	printf("Produce department has been stocked.\n");
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

char* inventory_table(struct produce_item** top) {

	struct produce_item* helper = *top;

	char* target = malloc(2500);

	int item_count = 1;

	// Concat header to buffer string
	sprintf(target,"%s","==========================================================================\r\n");
	sprintf(target+strlen(target)," %-7s %-14s %-16s %-14s %-8s %-8s\r\n", "Item #", "Produce", "Type", "Sold By", "Price", "In Stock");
	sprintf(target+strlen(target),"%s","==========================================================================\r\n");

	while (helper != NULL) {
		// Concat row to buffer
		sprintf(target+strlen(target),"   %-5d %-13s %-16s %-13s %6.2f %8d\r\n",
				item_count++,
				helper->produce,
				helper->type,
				helper->sold_by,
				helper->price,
				helper->stock_quantity);

		// Move to the next item in stack
		helper = helper->next;

	}

	return target;
}

void reverse_inventory(struct produce_item** top, struct produce_item** below_top) {

	/*
	 * Each time this function is invoked, it handles 3 nodes
	 *
	 * top[C] -> below_top[B] -> [A]
	 */
	struct produce_item* helper;

	if (*below_top != NULL) {

		/*
		 * top[C] -> below_top[B] -> [A]
		 * Helper is a copy of node pointer A
		 */
		helper = (*below_top)->next;

		/*
		 * top[C] -> below_top[B] -> [C]
		 * Node pointer A becomes C
		 * Which makes C and B point to each other
		 * top[C] <-> below_top[B]
		 */
		(*below_top)->next = (*top);

		/*
		 * top[B] -> below_top[B]
		 * Node pointer C becomes B
		 */
		*top = *below_top;

		/*
		 * top[B] -> below_top[A]
		 * Node pointer B (pointed by below_top)
		 * becomes node pointer helper which is A
		 */
		*below_top = helper;

		/*
		 * top[B] -> below_top[A]
		 */
		reverse_inventory(top, below_top);
	}

}

void export_inventory(struct produce_item** top) {

	char* file_name = "AssignmentTwoOutput.txt";

	printf("Trying to create file %s\n", file_name);

	// Open file
	FILE *ptr_file = fopen(file_name, "w");

	// Check if it exists
	if (ptr_file == NULL) {
		printf("Error opening file!\n");
		return;
	}

	// Write table string to file
	fprintf(ptr_file, "%s", inventory_table(top));

	printf("Successfully wrote out file %s\n", file_name	);

	// Close file
	fclose(ptr_file);


}


