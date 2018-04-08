//
// Created by Alejandro Garau Madrigal on 09/03/2018.
//
#include <stdlib.h>
#include <stdio.h>
#include "TLista.h"


void crear(T_Lista* lista){
    *lista = calloc(sizeof(struct T_Nodo), 1);
}

void destruir(T_Lista* lista){
    T_Lista actual = *lista;
    T_Lista siguiente;
    while(actual != NULL){
        siguiente = actual->sig;
        free(actual);
        actual = siguiente;
    }
}

void rellenar(T_Lista *lista) {
    int tam, contador;
    unsigned elem;
    struct T_Nodo *ptr, *ant = NULL, *inicio; // dudas al profesor.
    printf("%s", "Introduzca la cantidad de elementos: ");
    scanf("%d", &tam);
    for (contador = 0; contador < tam; contador++) {
        printf("%s %d: ", "Introduzca el elemento ", contador + 1);
        scanf("%u", &elem);
        ptr = malloc(sizeof(struct T_Nodo));

        if (ant != NULL) {
            ant->sig = ptr;
        } else {
            inicio = ptr;
        }
        ptr->num =  elem;
        ant = ptr;
    }
    if(tam > 0) {
        ptr->sig = NULL;
        *lista = inicio;
    }
    printf("\n");
}

void mostrar(T_Lista lista){
    while(lista != NULL){
        printf("%u ", lista->num);
        lista = lista->sig;
    }
    printf("\n");
}

void escribir_fichero_binario(char* nombre, T_Lista lista){
    FILE* file = fopen(nombre, "wb");
    if(file != NULL){
        while(lista != NULL){
            fwrite(&lista->num, sizeof(unsigned), 1, file);
            lista = lista->sig;
        }
        fclose(file);
    }
}

void escribir_fichero(char* nombre, T_Lista lista){
    FILE* file = fopen(nombre, "w");
    if(file != NULL){
        while(lista != NULL){
            fprintf(file,"%u ", lista->num);
            lista = lista->sig;
        }
        fprintf(file, "\n");
        fclose(file);
    }
}

static void insertar(T_Lista lista, unsigned elem){
    T_Lista nuevo;
    T_Lista aux = lista;
    while(aux->sig != NULL){
        aux = aux->sig;
    }
    nuevo = malloc(sizeof(struct T_Nodo));
    nuevo->num = elem;
    nuevo->sig = NULL;
    aux->sig = nuevo;
}

void leer_fichero(char* nombre, T_Lista* lista){
    FILE* file = fopen(nombre, "rb");
    T_Lista aux = *lista;
    unsigned num, read_bytes;
    if(file != NULL){
        while(!feof(file)){
            read_bytes = (int) fread(&num, sizeof(num), 1, file);
            if(read_bytes > 0){
                insertar(aux, num);
                aux = aux->sig;
            }
        }
        fclose(file);
    }
}


int main(){
    /*T_Lista lista;
    crear(&lista);
    rellenar(&lista);
    mostrar(lista);
    escribir_fichero_binario("fich.txt", lista);
    escribir_fichero("not_b.txt", lista);*/
    T_Lista read;
    crear(&read);
    leer_fichero("fich.txt", &read);
    printf("%s\n", "Enseñando mostrar: ");
    mostrar(read);
    destruir(&read);
    //destruir(&lista);
    return 0;
}