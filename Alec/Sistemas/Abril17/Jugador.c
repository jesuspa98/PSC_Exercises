//
// Created by Alejandro Garau Madrigal on 12/03/2018.
//

#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include "ListaJugadores.h"

#define JUGADOR "JUGADOR.BIN"


void destruir(TLista* lista){
    TLista actual = *lista;
    TLista siguiente;
    while(actual != NULL){
        siguiente = actual->sig;
        free(actual);
        actual = siguiente;
    }
}

void crearLista(TLista *lc){
    *lc = malloc(sizeof(struct TNodo));
}

int compara(struct Jugador j1, struct Jugador j2){
    if (j1.codigo_jugador == j2.codigo_jugador){
        if (j1.posicion == j2.posicion){
            if (fabsf(j1.precio - j2.precio) < 0.001){
                return 0;
            } else if (j1.precio > j2.precio) {
                return 1;
            } else if (j1.precio < j2.precio){
                return -1;
            }
        }  else {
            return j1.posicion - j2.posicion;
        }
    } else{ 
        return j1.codigo_jugador - j2.codigo_jugador;
    } 
}


void insertar_jugador(TLista* lista, struct Jugador jug, int* ok){
    TLista aux = *lista, anterior = NULL, nuevo;
    while(aux != NULL && compara(jug, aux->jug) < 0){
        anterior = aux;
        aux = aux->sig;
    }
    if(aux != NULL && compara(jug, aux->jug) == 0){
        *ok = 0;
    } else {
        if(anterior == NULL){
            crearLista(&anterior);
            anterior->jug = jug;
            anterior->sig = aux;
            aux = anterior;
        } else {
            crearLista(&nuevo);
            nuevo->jug = jug;
            nuevo->sig = aux;
            anterior->sig = nuevo;
        }
        *ok = 1;
        *lista = aux;
    }
}


int leer_fichero_lista(TLista* lista){
    int number_of_players = 0;
    int ok;
    struct Jugador new;
    FILE* file = fopen(JUGADOR, "rb");
    if(file != NULL){
        while(!feof(file)){
            if(fread(&new.codigo_jugador, sizeof(new.codigo_jugador), 1, file) != 1){
                fclose(file);
                return number_of_players;
            }
            if(fread(&new.codigo_equipo, sizeof(new.codigo_equipo), 1, file) != 1){
                fclose(file);
                return number_of_players;
            }
            if(fread(&new.posicion, sizeof(new.posicion), 1, file) != 1){
                fclose(file);
                return number_of_players;
            }
            if(fread(&new.precio, sizeof(new.precio), 1, file) != 1){
                fclose(file);
                return number_of_players;
            }
            insertar_jugador(lista, new, &ok);
            number_of_players += ok;
        }
    }
    fclose(file);
    return number_of_players;
}


void agregar_a_fichero(struct Jugador jugador, int* ok){
    FILE* file = fopen("output.BIN", "ab");
    if(file != NULL){
        if(fwrite(&jugador.codigo_jugador, sizeof(int), 1, file) != 1){
            *ok = 0;
            return;
        }
        if(fwrite(&jugador.codigo_equipo, sizeof(int), 1, file) != 1){
            *ok = 0;
            return;
        }
        if(fwrite(&jugador.posicion, sizeof(int), 1, file) != 1){
            *ok = 0;
            return;
        }
        if(fwrite(&jugador.precio, sizeof(float), 1, file) != 1){
            *ok = 0;
            return;
        }
        *ok = 1;
    } else {
        *ok = 0;
    }
}

void mostrar_lista(TLista lista){
    while(lista != NULL){
        printf("Codigo; %d; Equipo: %d; Posicion: %d; Precio: %g\n", lista->jug.codigo_jugador,
               lista->jug.codigo_equipo, lista->jug.posicion, lista->jug.precio);
        lista = lista->sig;
    }
    printf("\n");
}