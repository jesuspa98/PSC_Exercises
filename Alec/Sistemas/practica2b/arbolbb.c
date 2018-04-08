//
// Created by Alejandro Garau Madrigal on 27/03/2018.
//

#include <stdlib.h>
#include "arbolbb.h"

void Crear(T_Arbol* arbol){
    *arbol = NULL;
}

/*void Insertar(T_Arbol* arbol, unsigned num){
    T_Arbol actual = *arbol;
    T_Arbol izq, der;
    if (actual != NULL) {
        if(actual->izq == NULL && actual->der == NULL){
            actual->dato = num;
            Crear(&izq); Crear(&der);
            actual->izq = izq;
            actual->der = der;
        } else if (actual->dato > num){
            Insertar(&actual->izq, num);
        } else if (actual->dato < num){
            Insertar(&actual->der, num);
        }
    } else {
        actual = malloc(sizeof(actual));
        actual->dato = num;
    }
    *arbol = actual;
}*/

void Insertar(T_Arbol* arbol, unsigned num){
    // TODO NOT WORKING!!!
    T_Arbol  newNode;
    if (*arbol == NULL){
        newNode = (T_Arbol) malloc(sizeof(struct T_Nodo));
        newNode->dato = num;
        newNode->izq = newNode->der = NULL;
        *arbol = newNode;
    } else {
        if ((*arbol)->dato > num){
            Insertar(&(*arbol)->izq, num);
        } else if ((*arbol)->dato < num){
            Insertar(&(*arbol)->der, num);
        }
    }
}

void Destruir(T_Arbol* arbol){
    if (*arbol != NULL) {
        Destruir(&((*arbol)->izq));
        Destruir(&((*arbol)->der));
        free(*arbol);
        *arbol = NULL;
    }
}

void Mostrar(T_Arbol arbol){
    if (arbol != NULL) {
        Mostrar(arbol->izq);
        printf("%d ", arbol->dato);
        Mostrar(arbol->der);
    }
}

void Salvar(T_Arbol arbol, FILE* fichero){
    if(fichero != NULL){
        if(arbol != NULL){
            Salvar(arbol->izq, fichero);
            fwrite(&arbol->dato, sizeof(arbol->dato), 1, fichero);
            Salvar(arbol->der, fichero);
        }
    }else{
        perror("Couldn't open the file.");
    }
}

