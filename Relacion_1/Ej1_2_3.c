#include <stdio.h>

/*
 * Ejercicio 1
 */
void get_from_pt(int* pt);

/*
 * Ejercicio 2
 */
void modify_pt(int* pt1, int* pt2);
/*
 * Ejercicio 3
 */
void set_array(int array[], int size);

int main(){

    return 0;
}
/*
 * Ejercicio 1
 */
void get_from_pt(int* pt){
    printf("%d\n", *pt);
}

/*
 * Ejercicio 2
 */
void modify_pt(int* pt1, int* pt2){
    *pt1 = 14;
    *pt2 = 15;
    printf("%d, %d\n", *pt1, *pt2);
}
/*
 * Ejercicio 3
 */
void set_array(int array[], int size){
    for(size_t i = 0; i < size; i++){
        scanf("%s\n", );
    }
}
