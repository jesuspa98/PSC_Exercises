#include <stdio.h>
#include <stdlib.h>
#include "gestion_memoria.h"

///#define MEMSIZE 1000
const int MEMSIZE = 1000;


/*
 * Crea la estructura utilizada para gestionar la memoria disponible. Inicialmente, sólo un nodo desde 0 a MAX
 */

void crear(T_Manejador* manejador){
    *manejador = (T_Manejador) malloc(sizeof(struct T_Nodo));
    (*manejador)->inicio = 0;
    (*manejador)->fin = MEMSIZE - 1;
    (*manejador)->sig = NULL;
}

/*
 * Destruye la estructura utilizada (libera todos los nodos de la lista.
 * El parámetro manejador debe terminar apuntando a NULL
 */

void destruir(T_Manejador* manejador){

}

/*
 * Devuelve en dir la dirección de memoria simulada (unsigned) donde comienza el trozo de
 * memoria continua de tamaño tam solicitada. Si la operación se pudo llevar a cabo, es decir, existe
 * un trozo con capacidad suficiente, devolvera TRUE (1) en ok; FALSE (0) en otro caso.
 */

void obtener(T_Manejador *manejador, unsigned tam, unsigned* dir, unsigned* ok){
    T_Manejador ant = NULL, ptr = *manejador;
    while(ptr!=NULL && (ptr->fin-ptr->inicio+1)<tam){
        ant = ptr;
        ptr = ptr->sig;
    }
    *ok = ptr!=NULL;    //(ptr->fin-ptr->inicio+1)>=tam
    if(*ok){
        *dir = ptr->inicio;
        if((ptr->fin-ptr->inicio+1)>tam){
            ptr->inicio += tam;
        }else{
            if(ant == NULL){
                *manejador = ptr->sig;
            }else{
                ant->sig = ptr->sig;
            }
            free(ptr);
        }
    }
}

/*
 * Muestra el estado actual de la memoria, bloques de memoria libre
 */

void mostrar (T_Manejador manejador){

}

/*
 * Devuelve el trozo de memoria continua de tamaño tam y que
 * comienza en dir.
 * Se puede suponer que se trata de un trozo obtenido previamente.
 */

void devolver(T_Manejador *manejador,unsigned tam,unsigned dir){
    T_Manejador nuevo, ant = NULL, ptr = *manejador;
    char pegadoAnt, pegadoPtr;
    while(ptr!=NULL && ptr->inicio<dir){
        ant = ptr;
        ptr = ptr->sig;
    }

    pegadoAnt = ant!=NULL && dir == ant->fin+1;
    pegadoPtr = ptr!=NULL && (dir+tam-1) == ptr->inicio-1;

    if(pegadoAnt && pegadoPtr){
        ant->fin = ptr->fin;
        ant->sig = ptr->sig;
        free(ptr);
    }else if(pegadoAnt){    //&& !pegadoPtr
        ant->fin += dir;
    }else if(pegadoPtr){    //&& !pegadoAnt
        ptr->inicio -= dir;
    }else{                  // !pegadoAnt && !pegadoPtr
        nuevo = (T_Manejador) malloc(sizeof(struct T_Nodo));
        nuevo->inicio = dir;
        nuevo->fin = dir+tam-1;
        nuevo->sig = ptr;
        if(ant==NULL){
            *manejador = nuevo;
        }else{
            ant->sig = nuevo;
        }
    }
}