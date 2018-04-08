/*
 *  Created by jesuspa98 on 05/04/18
 */
#include "ListaJugadores.h"
#include <stdio.h>
#include <stdlib.h>
#define FICH "JUGADOR.BIN"

//TODO crea una lista vacía (sin ningún nodo)
void crearLista(TLista *lc){

}

// Devuelve 0 si j1 es igual que j2, es decir misma posición, mismo precio y mismo código.
// Devuelve -1 si j1 es menor que j2 según el criterio descrito y +1 en caso contrario
int compara (struct Jugador j1, struct Jugador j2){
    if(j1.posicion == j2.posicion && j1.codjugador == j2.codjugador && j1.precio == j2.precio){
        return 0;
    }else if(j1.posicion < j2.posicion && j1.codjugador < j2.codjugador && j1.precio < j2.precio){
        return -1;
    }else{
        return 1;
    }
}

// Inserta el jugador en la lista siempre que no esté repetido. Para insertarlo correctamente debe
// cumplirse el criterio de ordenación indicado al comienzo del enunciado.
// En ok devolverá un 1 si se ha podido insertar, y un 0 si no se pudo insertar porque estuviera
// repetido ese jugador en la lista
void insertar_jugador(TLista *lista, struct Jugador j, int *ok){
    TLista ptr = *lista, ant = NULL, nuevo;

    while(ptr != NULL && compara(ptr->jugador, j)){
        ant = ptr;
        ptr = ptr->sig;
    }

    if(ant == NULL && (ptr == NULL || compara(ptr->jugador, j) != 0)){
        nuevo = malloc(sizeof(struct Jugador));
        nuevo->jugador = j;
        nuevo->sig = *lista;
        *lista = nuevo;
    }else if(ptr != NULL && compara(ptr->jugador, j) == 0){
        *ok = 0;
    }else{
        nuevo = malloc(sizeof(struct TNodo));
        nuevo->jugador = j;
        nuevo->sig = ptr;
        ant->sig = nuevo;
    }
}

// Vuelca en la lista el contenido existente en el fichero JUGADOR.BIN.
// Cuidado: en el fichero pueden estar los jugadores almacenados en cualquier orden,
// y el volcado debe realizarse teniendo en cuenta el criterio de ordenación indicado
// con anterioridad en el enunciado. La función deberá devolver el total de jugadores insertados en
// este proceso. Debe controlarse que en la lista no puede haber jugadores repetidos.
// Solo se insertarán en la lista los jugadores no repetidos.
int leer_fichero_lista(TLista *lista){
    struct Jugador j;
    int ok, numJugad = 0;

    FILE* f = fopen(FICH, "rb");

    if(f != NULL){
        while(fread(&j, sizeof(j), 1, f) == 1){
            insertar_jugador(lista, j, &ok);
            numJugad += ok;
        }

        fclose(f);
    }

    return numJugad;
}

//TODO Muestra en pantalla el listado de jugadores
void mostrar_lista(TLista lista){

}

// Añade el jugador en el fichero JUGADOR.BIN. El jugador se almacenará al final del fichero.
// En ok devolverá un 1 si se ha podido añadir, y un 0 si no se pudo añadir por algún error en el
// manejo del fichero
void agregar_a_fichero (struct Jugador jugador, int *ok){
    FILE* f = fopen(FICH, "ab");
    if(f != NULL){
        *ok = fwrite(&jugador, sizeof(jugador), 1, f) == 1;
        fclose(f);
    }else{
        *ok = ok;
    }
}

//TODO Destruye la lista de jugadores, liberando todos los nodos de la misma de memoria.
void destruir(TLista *lista){

}