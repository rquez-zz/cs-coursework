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

int main() {
    
    struct node* first = malloc(sizeof(struct node));
    first->value = 1;
    struct node* second = malloc(sizeof(struct node));
    second->value = 2;
    struct node* third = malloc(sizeof(struct node));
    third->value = 3;
    struct node* fourth = malloc(sizeof(struct node));
    fourth->value = 4;

    first->next = second;
    second->next = third;
    third->next = fourth;
    fourth->next = NULL;
    print(first);
    first = reverse(first);
    print(first);
    first = reverse(first);
    print(first);
    addC(first, 1);
    print(first);
    addC(first,2);
    print(first);
    first = frontToBack(first);
    print(first); 
    first = doubleList(first);
    print(first);
    first = delValue(first, 7);
    print(first);
    print(first);
    printf("PRINTING IN REVERSE\n");
    print_reverse(first);
    return 0;
}
