/*
 * Created by jesuspa98 on 01/04/18.
 */
#include <stdio.h>

void pointers(int *pointer1, int *pointer2){
    printf("The value of the first pointer is: %d "
           "and the second pointer is: %d\n", *pointer1, *pointer2);
    int aux = *pointer1;
    pointer1 = &*pointer2;
    pointer2 = &aux;
    printf("The value of the first pointer is: %d "
           "and the second pointer is: %d\n", *pointer1, *pointer2);
}

int main(){
    int value1 = 10, value2 = 20;
    int *pointer1, *pointer2;

    pointer1 = &value1;
    pointer2 = &value2;

    printf("Program to show two pointers' value changing their values.\n\n");
    pointers(pointer1, pointer2);

    return 0;
}