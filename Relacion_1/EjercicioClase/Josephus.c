//
// Created by jesuspa98 on 9/03/18.
//

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct TNodo *TListaC;
struct TNodo{
    char nombre[30];
    TListaC sig;
};

void cargarFichero (char *nom, TListaC *lista){
    char nombre [30];
    FILE *f = fopen(nom, "rt");
    if(f!=NULL){
        while(fscanf(f, "%S", nombre)==1){
            insertar(lista, nombre);
        }
        fclose(f);
    }
}

void  insertar (TListaC *lista, char *nombre){
    TListaC nuevo;

    nuevo = malloc(sizeof(struct TNodo));
    strcpy(nuevo->nombre, nombre);
    if(*lista == NULL){
        *lista = nuevo;
        nuevo->sig=nuevo;
    }else{
        nuevo>sig = (*lista)->sig;
        (*lista)->sig = nuevo;
        *lista = nuevo;
    }
}

int longitud(TListaC lista){
    int n = 0; TListaC aux;
    if(lista != NULL){
        aux = lista->sig;
        while(aux != lista){
            n++;
            aux = aux->sig;
        }
    }
    return n;
}

void extraer(TListaC *lista, char *nombre){

    if(*lista == NULL){
        nombre[0] = '\0';
    }else{
        strcpy(nombre, (*lista)->sig->nombre);
        aux = (*lista)->sig;
        if(aux == *lista){
            *lista == NULL;
        }else{
            (*lista)->sig = NULL;
            free(aux);
        }
    }
}