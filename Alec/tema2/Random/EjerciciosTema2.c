//
// Created by Alejandro Garau Madrigal on 09/03/2018.
//
#include <stdio.h>
#include <stdlib.h>


struct Info1{
    char nombre[30];
    char apellidos[60];
};

struct Info2{
    int edad;
    int altura;
};

struct BigStruct{
    struct Info1 inf1;
    struct Info2 inf2;
};


int ejercicio1(int* array, int tam);
void mostrar_puntero(int* puntero, int tam);
void modificar_puntero(int* pointer, int tam);
void introduce_info1(struct Info1* inf);
void introduce_info2(struct Info2* inf);



int main(){
    // Ejercicio 4
    /*int array[5] ; int size = 5;
    int tam = ejercicio1(array, size);
    printf("%s, %d\n", "El tamaño del array es de", tam);
    mostrar_puntero(array, tam);
    */

    // Ejercicio 3
    /*int* puntero = malloc(5*sizeof(int));
    int tem = ejercicio1(puntero, 3);
    printf("%s, %d\n", "El tamaño del array es de", tem);
    //Ejercicio 5
    mostrar_puntero(puntero, tem);
    modificar_puntero(puntero, tem);
    mostrar_puntero(puntero, tem);
    free(puntero);*/

    struct BigStruct st;
    introduce_info1(&st.inf1);
    introduce_info2(&st.inf2);
    printf("%s, %s, %d, %d", st.inf1.nombre, st.inf1.apellidos, st.inf2.edad, st.inf2.altura);

    return 0;
}


int ejercicio1(int* array, int tam){
    int counter = 0;
    while(counter < tam){
        scanf("%d", array + counter);
        counter++;
    }
    return counter;
}

void mostrar_puntero(int* puntero, int tam){
    for(size_t i = 0; i < tam; i ++){
        printf("%d\n", puntero[i]);
    }
}

void modificar_puntero(int* pointer, int tam){
    pointer[tam-1] = tam;
    pointer[0] = 1;
}

void introduce_info1(struct Info1* inf){
    printf("%s", "Introduzca su apellido: ");
    scanf("%s", inf->apellidos);
    printf("%s", "Introduzca su nombre: ");
    scanf("%s", inf->nombre);
}

void introduce_info2(struct Info2* inf){
    printf("%s", "Introduzca su edad: ");
    scanf("%d", &inf->edad);
    printf("%s", "Introduzca su altura en cm: ");
    scanf("%d", &inf->altura);
}