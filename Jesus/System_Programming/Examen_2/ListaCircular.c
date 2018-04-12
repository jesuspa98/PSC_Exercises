/*
 *  Created by jesuspa98 on 5/04/18.
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "ListaCircular.h"

// Crea una lista circuladr vacía (sin ningún nodo)
void crear(TListaCircular *lc){
    *lc = NULL;
}

// Inserta un nuevo nodo con el dato nombre al final de la lista circular
void insertar(TListaCircular *lc,char *nombre){
    TListaCircular first, current, new;
    first = *lc;

    if(first == NULL){
        new = malloc(sizeof(struct TNodo));
        new->sig = new;
        strcpy(new->nombre, nombre);
        *lc = new;
    }else{
        current = first->sig;
        while(current->sig != first){
            current = current->sig;
        }
        new = malloc(sizeof(struct TNodo));
        strcpy(new->nombre, nombre);
        current->sig = new;
        new->sig = first;
        (*lc) = new;
    }
}

// Recorre la lista circular escribiendo los nombres de los nodos en la pantalla
void recorrer(TListaCircular lc){
    TListaCircular first = lc;
    if(lc != NULL){
        printf("%s\n", first->nombre);
        lc = lc->sig;
        while(lc != first) {
            printf("%s\n", lc->nombre);
            lc = lc->sig;
        }
    }
}

// Devuelve el número de nodos de la lista
int longitud(TListaCircular lc){
    TListaCircular first = lc;
    lc = lc->sig;
    int length = 1;
    while(lc != first) {
        length++;
        lc = lc->sig;
    }
    return length;
}

// Mueve el puntero exterto de la lista n nodos (siguiendo la dirección de la lista)
void mover(TListaCircular *lc,int n){
    TListaCircular lista = *lc;
    int counter = 0;
    while(counter < n){
        lista = lista->sig;
        counter++;
    }
    *lc = lista;
}

/*
 * Elimina el primer nodo de la lista, y devuelve el nombre que contiene a
 * través del parámetro nombre
 */
void extraer(TListaCircular *lc,char *nombre) {
    if (*lc == NULL)
        nombre = "";
    else {
        strcpy(nombre, (*lc)->nombre);

        int n = longitud(*lc);
        TListaCircular primero = *lc;

        mover(lc, n - 1);
        TListaCircular siguiente = (*lc)->sig;
        (*lc)->sig = siguiente->sig;

        free(primero);
    }
}