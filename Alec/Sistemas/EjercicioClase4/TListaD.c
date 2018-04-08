//
// Created by Alejandro Garau Madrigal on 11/03/2018.
//


#include <stdlib.h>
#include <stdio.h>
#include "TListaD.h"
/*
 *  Definir una biblioteca para gestión de una lista dinámica de enteros con las operaciones habituales:
 *  Crear, Mostrar, Insertar por el principio, Insertar por el final, Insertar ordenado,
 *  Eliminar un elemento, Destruir (Segundo ejemplo de manejo de listas dinámicas):
 */

void crear(T_ListaD* lista){
    *lista = calloc(sizeof(struct T_NodeD), 1);
}

void mostrar(T_ListaD lista){
    lista = lista->sig;
    while(lista != NULL){
        printf("%d ", lista->num);
        lista = lista->sig;
    }
    printf("\n");
}

void destruir(T_ListaD* lista){
    T_ListaD actual = *lista;
    T_ListaD siguiente;
    while(actual != NULL){
        siguiente = actual->sig;
        free(actual);
        actual = siguiente;
    }
}

void insertar_principio(T_ListaD* lista, unsigned elem){
    T_ListaD new_elem = malloc(sizeof(struct T_NodeD));
    new_elem->num = elem;
    new_elem->sig = *lista;
    *lista = new_elem;
}

void insertar_final(T_ListaD* lista, unsigned elem){
     if(lista != NULL) {
         T_ListaD aux = *lista;
         T_ListaD new;
         while (aux->sig != NULL) {
            aux = aux->sig;
         }
         new = malloc(sizeof(struct T_NodeD));
         new->num = elem;
         new->sig = NULL;
         aux->sig = new;
     } else {
         T_ListaD new_lista = malloc(sizeof(struct T_NodeD));
         new_lista->num = elem;
         new_lista->sig = NULL;
         *lista = new_lista;
     }

}

void insertar_ordenado(T_ListaD* lista, unsigned elem){
    if(lista != NULL){
        T_ListaD aux = *lista;
        T_ListaD anterior = NULL;
        aux = aux->sig;
        while(aux != NULL && aux->num <= elem){
            anterior = aux;
            aux = aux->sig;
        }
        if(aux != NULL){
            T_ListaD new = malloc(sizeof(struct T_NodeD));
            new->num = elem;
            if(anterior!=NULL) {
                anterior->sig = new;
                new->sig = aux;
            } else {
                new->sig = aux;
                *lista = new;
            }
        }
    }

}

void eliminar_elemento(T_ListaD* lista, unsigned elem){
    if(lista != NULL) {
        T_ListaD aux = *lista;
        T_ListaD anterior = NULL;
        aux = aux->sig;
        while(aux != NULL && elem != aux->num){
            anterior = aux;
            aux = aux->sig;
        }
        if(aux != NULL) {
            if (aux->num == elem && anterior != NULL) {
                anterior->sig = aux->sig;
                free(aux);
            } else {
                *lista = aux->sig;
                free(aux);
            }
        }
    }
}



int main(){
    T_ListaD lista;
    crear(&lista);
    insertar_final(&lista, 1);
    insertar_final(&lista, 2);
    insertar_final(&lista, 4);
    mostrar(lista);
    insertar_ordenado(&lista, 3);
    mostrar(lista);
    eliminar_elemento(&lista, 2);
    mostrar(lista);
    return 0;
}