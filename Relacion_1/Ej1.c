/*
 * Created by jesuspa98 on 01/04/18.
 */
#include <stdio.h>

void punteros(int *pointer){
    printf("The value of the pointer is: %d", *pointer);
}

int main() {
    int value = 10;
    int *pointer;
    pointer = &value;
    printf("Program to show a pointer's value.\n\n");
    punteros(pointer);
    return 0;
}