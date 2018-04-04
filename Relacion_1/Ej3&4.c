/*
 * Created by jesuspa98 on 04/04/18.
 */
#include <stdio.h>
#include <stdlib.h>

void readInts(int *pInt, int size) {
    int i;
    printf("Enter %d numbers: ", size);
    for(i = 0; i < size; i++){
        scanf("%d", &pInt[i]);
    }
}

int main(){
    int size, *zone;

    printf("Program to have a zone to int pointers and introduce numbers\n\n");
    printf("Introduce the size: ");
    scanf("%d", &size);

    int array[size];

    zone = &array[0];
    readInts(zone, size);

    return 0;
}