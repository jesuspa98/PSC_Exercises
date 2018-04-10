/*
 *  Created by jesuspa98 on 05/04/18
 */
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "ListaJugadores.h"
#define JUGADOR "JUGADOR.BIN"

// Crea una lista vacía (sin ningún nodo)
void crearLista(TLista *lc){
    *lc = NULL;
}

/*
 * Devuelve 0 si j1 es igual que j2, es decir misma posición, mismo precio y mismo código.
 * Devuelve -1 si j1 es menor que j2 según el criterio descrito y +1 en caso contrario
 */
int compara(struct Jugador j1, struct Jugador j2){
    if(j1.posicion < j2.posicion){
        return -1;
    }else if(j1.posicion > j2.posicion){
        return 1;
    }else if(j1.precio < j2.precio){
        return -1;
    }else if(j2.precio < j1.precio){
        return 1;
    }else if(j1.codjugador < j2.codjugador){
        return -1;
    }else if(j2.codjugador < j1.codjugador){
        return 1;
    }else{
        return 0;
    }
}

/*
 * Inserta el jugador en la lista siempre que no esté repetido. Para insertarlo correctamente debe
 * cumplirse el criterio de ordenación indicado al comienzo del enunciado.
 * En ok devolverá un 1 si se ha podido insertar, y un 0 si no se pudo insertar porque estuviera
 * repetido ese jugador en la lista
 */
void insertar_jugador(TLista *lista, struct Jugador jugador, int *ok){
    TLista newNode, auxNode, currentNode, nextNode;
    *ok = 0;
    if(*lista == NULL){
        newNode = (TLista)malloc(sizeof(struct TNodo));
        newNode->jugador = jugador;
        newNode->sig = NULL;
        *ok = 1;
        *lista = newNode;
    }else{
        if(compara(jugador, (*lista)->jugador) == -1){
            newNode = (TLista)malloc(sizeof(struct TNodo));
            newNode->jugador = jugador;
            auxNode = *lista;
            newNode->sig=auxNode;
            *lista = newNode;
            *ok = 1;
        }else{
            nextNode = *lista;
            while(nextNode != NULL && compara(jugador, nextNode->jugador) > 0){
                currentNode = nextNode;
                nextNode = nextNode->sig;
            }
            if(nextNode == NULL){
                newNode = (TLista)malloc(sizeof(struct TNodo));
                newNode->jugador = jugador;
                newNode->sig = NULL;
                currentNode->sig = newNode;
                *ok = 1;
            }else if(compara(jugador, nextNode->jugador) < 0){
                newNode = (TLista)malloc(sizeof(struct TNodo));
                newNode->jugador = jugador;
                newNode->sig = nextNode;
                currentNode->sig = newNode;
                *ok = 1;
            }
        }
    }
}

/*
 * Vuelca en la lista el contenido existente en el fichero JUGADOR.BIN.
 * Cuidado: en el fichero pueden estar los jugadores almacenados en cualquier orden,
 * y el volcado debe realizarse teniendo en cuenta el criterio de ordenación indicado
 * con anterioridad en el enunciado. La función deberá devolver el total de jugadores insertados en
 * este proceso. Debe controlarse que en la lista no puede haber jugadores repetidos.
 * Solo se insertarán en la lista los jugadores no repetidos.
 */
 int leer_fichero_lista(TLista *lista){
    struct Jugador j;
    int ok = 0, numero_jugadores = 0;
    FILE* f = fopen(JUGADOR, "rb");

    if(f!=NULL){
        while(fread(&j, sizeof(struct Jugador), 1, f) == 1){
            insertar_jugador(lista, j, &ok);
            numero_jugadores += ok;
            //(*ok == 1) ? numero_jugadores++ : numero_jugadores;
        }
        fclose(f);
    }else{
        perror("Error abriendo el arcihvo");
    }

    return numero_jugadores;
}

// Muestra en pantalla el listado de jugadores
void mostrar_lista(TLista lista){
    while(lista != NULL){
        printf("Codigo; %d; Equipo: %d; Posicion: %d; Precio: %g\n", lista->jugador.codjugador,
               lista->jugador.codequipo, lista->jugador.posicion, lista->jugador.precio);
        lista = lista->sig;
    }
    printf("\n");
}

/*
 * Añade el jugador en el fichero JUGADOR.BIN. El jugador se almacenará al final del fichero.
 * En ok devolverá un 1 si se ha podido añadir, y un 0 si no se pudo añadir por algún error en el
 * manejo del fichero
 */
void agregar_a_fichero (struct Jugador jugador, int *ok){
    FILE* f = fopen(JUGADOR, "ab");
    if(f!=NULL){
        *ok = fwrite(&jugador, sizeof(jugador), 1, f) == 1;
    }else{
        *ok = -1;
    }
    fclose(f);
}

// Destruye la lista de jugadores, liberando todos los nodos de la misma de memoria.
void destruir(TLista *lista){
    TLista actual;
    while(*lista != NULL){
        actual = *lista;
        *lista = (*lista)->sig;
        free(actual);
    }
}