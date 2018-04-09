/*
 *  Created by jesuspa98 on 5/04/18.
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "ListaCircular.h"

// Crea una lista circular vacía (sin ningún nodo)
void crear(TListaCircular *lc){
    *lc = malloc(sizeof(struct TNodo));
}

// Inserta un nuevo nodo con el dato nombre al final de la lista circular
void insertar(TListaCircular *lc,char *nombre){
    TListaCircular new;
    crear(new);

    strcpy(new->nombre, nombre);
    if(*lc != NULL){
        *lc = new;
        new ->sig = new;
    }else{
        new->sig = (*lc)->sig;
        (*lc)->sig = new;
        *lc = new;
    }
}

// Recorre la lista circular escribiendo los nombres de los nodos en la pantalla
void recorrer(TListaCircular lc){
    int i;
    while(lc != NULL){
        printf("Nombre: ");
        i = 0;
        while(lc->nombre[i] != '\0' && i < 30){
            printf("%c", lc->nombre[i]);
            i++;
        }
        printf("\n");
        lc = lc->sig;
    }
    printf("\n");

}

// Devuelve el número de nodos de la lista
int longitud(TListaCircular lc){
    int n = 0;
    TListaCircular aux;

    if(lc != NULL){
        aux = lc->sig;
        while(aux != lc){
            n++;
            aux = aux->sig;
        }
    }
    return n;
}

// Mueve el puntero exterto de la lista n nodos (siguiendo la dirección de la lista)
void mover(TListaCircular *lc,int n){
    int i = 0;

    while(lc != NULL && i < n){
        *lc = (*lc)->sig;
        i++;
    }
}

/* Elimina el primer nodo de la lista, y devuelve el nombre que contiene a
 * través del parámetro nombre
 */
void extraer(TListaCircular *lc,char *nombre){
    TListaCircular aux;
    if(*lc != NULL){
        nombre[0] = '\0';
    }else{
        strcpy(nombre, (*lc)->sig->nombre);
        aux = (*lc)->sig;
        if(aux == *lc){
            *lc = NULL;
        }else{
            (*lc)->sig = NULL;
            free(aux);
        }

    }
}