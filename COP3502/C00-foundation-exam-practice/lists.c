#include<stdio.h>
#include<stdlib.h>
 
/* Link list node */
struct node
{
    int value;
    struct node* next;
};
 


/* WK1 Q4 Part E */
struct node* reverse(struct node* front)
{
    struct node* prev   = NULL;
    struct node* current = front;
    struct node* next;
    while (current != NULL)
    {
            next  = current->next;  
            current->next = prev;   
            prev = current;
            current = next;
    }
    return prev;
}

/* 09Aug Q1 */
void print_reverse(struct node* front) {

    struct node* current = front;
    struct node* next;
    struct node* prev = NULL;

    while ( current != NULL ) {
        
        next = current->next;
        current->next = prev;
        prev = current;
        current = next;
        
    }

    while ( prev != NULL) {
        printf("%d ", prev->value);
        prev = prev->next;
    }
}

/* 09Dec PartB Q2 */ 
void delEveryOther(struct node* front) {

    if (front == NULL || front->next == NULL)
        return;

    int count = 0;
    struct node* prev = NULL;

    while( front != NULL) {
    
        count++;

        if (count % 2 == 0) {
            prev->next = front->next; 
            free(front);
            front = prev->next;
        
        } else {
            prev = front;
            front = front->next;
        }
    
    }

}

/* WK1 Q4 Part B */ 
struct node* frontToBack(struct node* front) {
    struct node* first = front;
    struct node* secondLast;
    while (front->next != NULL) {
        if((front->next)->next == NULL) {
            secondLast = front;
            front = front->next;
        } else {
            front = front->next;
        }
    }
    front->next = first->next;
    first->next = NULL;
    secondLast->next = first;
    return front;
}

/* WK1 Q4 Part C */
struct node* doubleList(struct node* front) {
    struct node* node = malloc(sizeof(node));
    node->value = front->value;
    if( front->next != NULL ) {
        struct node* afterFront = front->next;
        front->next = node;
        node->next = doubleList(afterFront);
        return front;
    } else {
        front->next = node;
        return front;
    }

}


/* WK1 Q4 Part A */ 
void addC(struct node* front, int c) {
    while (front != NULL) {
        front->value = front->value + c;
        front = front->next;
    }
}

/* WK1 Q4 Part D */
struct node* delValue(struct node* front, int value) {
    
    struct node* prev = NULL; 
    struct node* next = front->next;
    struct node* first = front;
    while (front->next != NULL) {
        if(front->value == value) {
            if (prev != NULL) {
                prev->next = next;
            } 
            free(front);
            first = next;
        } else {
            prev = front;
        } 
        front = next;
        next = next->next;
    }
    return first; 
}

/* function to print each node */
void print(struct node* front) {
    while(front != NULL) {
        printf("%d ", front->value);
        front = front->next;
    }
    printf("\n");
}

/* function to generate a linked list */
struct node* generateLinkedList(int values[], int size){

    int i = 0;
    struct node* prev = NULL;
    struct node* first;
    for (i = 0; i < size; i++) {
        struct node* node = malloc(sizeof(struct node));
        node->value = values[i];
        node->next = NULL;
        if (i != 0) {
            prev->next = node; 
        } else {
            first = node;
        }
        prev = node; 
    }
    return first; 
}

int main() {
    
    int values[0];
    struct node* list = generateLinkedList(values, 1);
    print(list);
    printf("DELETING EVERY OTHER NODE\n");
    delEveryOther(list);
    print(list);
    return 0;
}
