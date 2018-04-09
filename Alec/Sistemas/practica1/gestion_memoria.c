//
// Created by Alejandro Garau Madrigal on 14/03/2018.
//
#include <stdlib.h>
#include <stdio.h>
#include "./gestion_memoria.h"

#define MEMORYSIZE 1000

void crear(T_Manejador* manejador){
    *manejador = (struct T_Nodo*) malloc(sizeof(struct T_Nodo));
    (*manejador)->inicio = 0;
    (*manejador)->fin = MEMORYSIZE-1;
    (*manejador)->sig = NULL;
}

void obtener(T_Manejador *manejador, unsigned tam, unsigned* dir, unsigned* ok){
    T_Manejador actual = *manejador;
    T_Manejador anterior = NULL;
    while(actual->sig != NULL && (actual->fin - actual->inicio) < tam){
        anterior = actual;
        actual = actual->sig;
    }
    *ok = actual != NULL;
    if(*ok){
        *dir = actual->inicio;
        if((actual->fin - actual->inicio + 1) == tam){
            if(anterior == NULL){
                *manejador = actual->sig;
            } else {
                anterior->sig = actual->sig;
            }
            free(actual);
        } else {
            actual->inicio +=tam;
        }
    }
}

void devolver(T_Manejador *manejador, unsigned tam, unsigned dir){
    T_Manejador head = *manejador;
    T_Manejador new, last = NULL;
    char pegadoanterior, pegadoposterior;
    while(head->sig != NULL && head->inicio < dir){
        last = head;
        head = head->sig;
    }
    pegadoanterior = last != NULL && dir == (last->fin);
    pegadoposterior = head->sig != NULL && (dir + tam) == (head->inicio);
     if(pegadoanterior && pegadoposterior){
        last->fin = head->fin;
        last->sig = head->sig;
        free(head);
    } else if(pegadoanterior){ // && !pegadoposterior
        last->fin += tam;
    } else if(pegadoposterior){ // && !pegadoanterior
        head->inicio -= tam;
    } else { // si no está pegado ni al anterior ni al siguiente!
        new = (T_Manejador) malloc(sizeof(struct T_Nodo));
        new->inicio = dir;
        new->fin = dir + tam - 1;
        new->sig = head;
        if(last == NULL){
            *manejador = new;
        } else {
            last->sig = new;
        }
    }
    
}

void destruir(T_Manejador* manejador){
    T_Manejador head = *manejador;
    T_Manejador next = NULL;
    while(head != NULL){
        next = head->sig;
        free(head);
        head = next;
    }   
    *manejador = NULL;
}

void mostrar(T_Manejador manejador){
    while(manejador != NULL){
        printf("Inicio: %d, Fin: %d; ", manejador->inicio, manejador->fin);
        manejador = manejador->sig;
    }
    printf("\n");
    fflush(stdout);
}


// EXTENSION DE LA PRACTICA.
/*
 * Modificar la operación obtener para que el gestor de memoria le de el bloque de memoria disponible
 * cuyo tamaño esté más cercano a la petición de memoria.
 * Extender la práctica añadiendo una lista de memoria ocupada junto con la lista de memoria disponible
 */