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
    TListaCircular next, new;
    if(*lc == NULL) {
        new = malloc(sizeof(struct TNodo));
        new->sig = new;
        strcpy(new->nombre, nombre);
        *lc = new;
    } else {
        new = malloc(sizeof(struct TNodo));
        next = (*lc)->sig;
        strcpy(new->nombre, nombre);
        (*lc)->sig = new;
        new->sig = next;
    }
}

// Recorre la lista circular escribiendo los nombres de los nodos en la pantalla
void recorrer(TListaCircular lc){
    TListaCircular aux = lc;
    do{
        printf("Nombre: %s\n", aux->nombre);
        aux = aux->sig;
    }while(aux!= lc);
    printf("\n");
}

// Devuelve el número de nodos de la lista
int longitud(TListaCircular lc){
    if(lc == NULL)
        return 0;

    TListaCircular current = lc->sig;
    int contador = 1;
    while(current != lc) {
        current = current->sig;
        contador++;
    }
    return contador;
}

// Mueve el puntero exterto de la lista n nodos (siguiendo la dirección de la lista)
void mover(TListaCircular *lc,int n){
    int i = 0;
    TListaCircular list = *lc;

    while(i < n){
        list = list->sig;
        i++;
    }
    *lc = list;
}

/*
 * Elimina el primer nodo de la lista, y devuelve el nombre que contiene a
 * través del parámetro nombre
 */
void extraer(TListaCircular *lc,char *nombre){
    if(*lc == NULL)
        nombre = "";
    else {
        strcpy(nombre,(*lc)->nombre);

        int n = longitud(*lc);
        TListaCircular primero = *lc;

        mover(lc, n-1);
        TListaCircular siguiente = (*lc)->sig;
        (*lc)->sig = siguiente->sig;

        free(primero);
    }
}