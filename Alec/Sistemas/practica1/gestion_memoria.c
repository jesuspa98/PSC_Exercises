//
// Created by Alejandro Garau Madrigal on 14/03/2018.
//
#include <stdlib.h>
#include <stdio.h>
#include "./gestion_memoria.h"

#define MEMORYSIZE 1000

void crear(T_Manejador* manejador){
    *manejador = (T_Manejador) malloc(sizeof(struct T_Nodo));
    (*manejador)->inicio = 0;
    (*manejador)->fin = MEMORYSIZE - 1;
    (*manejador)->sig = NULL;
}

void obtener(T_Manejador *manejador, unsigned tam, unsigned* dir, unsigned* ok){
    T_Manejador aux = *manejador;
    T_Manejador anterior = NULL;
    while(aux->sig != NULL && ((aux->fin - aux->inicio + 1)< tam)){
        anterior = aux;
        aux = aux->sig;
    }
    *ok = aux != NULL;
    if(*ok) {
        *dir = aux->inicio;
        if ((aux->fin - aux->inicio + 1) == tam) {
            if (anterior == NULL) {
                *manejador = aux->sig;

            } else {
                anterior->sig = aux->sig;
            }
            free(aux);
        } else {
            aux->inicio += tam;
        }
    }
}

void devolver(T_Manejador *manejador, unsigned tam, unsigned dir){
    T_Manejador head = *manejador;
    T_Manejador anterior = NULL;
    T_Manejador nuevo;
    char pegadoanterior, pegadoposterior;
    while(head->sig != NULL && head->inicio < dir){
        anterior = head;
        head = head->sig;
    }
    pegadoanterior = anterior != NULL && dir == (anterior->fin);
    pegadoposterior = head->sig != NULL && (dir + tam) == (head->inicio);
    if(pegadoanterior && pegadoposterior){
        anterior->fin = head->fin;
        anterior->sig = head->sig;
        free(head);
    } else if(pegadoanterior){ // && !pegadoposterior
        anterior->fin += tam;
    } else if(pegadoposterior){ // && !pegadoanterior
        head->inicio -= tam;
    } else { // si no está pegado ni al anterior ni al siguiente!
        nuevo = (T_Manejador) malloc(sizeof(struct T_Nodo));
        nuevo->inicio = dir;
        nuevo->fin = dir + tam - 1;
        nuevo->sig = head;
        if(anterior == NULL){
            *manejador = nuevo;
        } else {
            anterior->sig = nuevo;
        }
    }
}

void destruir(T_Manejador* manejador){
    T_Manejador head = *manejador;
    T_Manejador sig;
    while(head != NULL){
        sig = head->sig;
        free(head);
        head = sig;
    }
    *manejador = NULL;
}

void mostrar(T_Manejador manejador){
    while(manejador != NULL){
        printf("Inicio: %d, Fin: %d; ", manejador->inicio, manejador->fin);
        manejador = manejador->sig;
    }
    printf("\n");
}


// EXTENSION DE LA PRACTICA.
/*
 * Modificar la operación obtener para que el gestor de memoria le de el bloque de memoria disponible
 * cuyo tamaño esté más cercano a la petición de memoria.
 * Extender la práctica añadiendo una lista de memoria ocupada junto con la lista de memoria disponible
 */