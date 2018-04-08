#include <stdio.h>
#include <stdlib.h>
#include "lista.c"
#define FICH "JUGADORBIN"
struct FILE;

typedef struct T_Nodo *T_Lista;

struct Jugador {
    int codjugador; //código que identifica al jugador
    int codequipo; //código de equipo
    int posición; //la posición del jugador en el campo puede ser 1 ,2,3,4 o 5
    float precio; //valor de mercado del jugador en millones de euros
};

struct T_Nodo{
    struct Jugador j;
    T_Lista sig;
};

void agregar(struct Jugador j, int *ok){
    FILE* f = fopen(FICH, "ab");
    if(f!=NULL){
        *ok = fwrite(&j, sizeof(j), 1, f) == 1;
        fclose(f);
    }else{
        *ok = ok;
    }
}

void insertar_jugador(T_Lista *lista, struct Jugador j, int *ok) {
    T_Lista ptr = *lista, ant = NULL, nuevo;

    while(ptr != NULL && compara(ptr->j, j)) {
        ant = ptr;
        ptr = ptr->sig;
    }

    if(ant == NULL && (ptr == NULL || compara(ptr->j, j) != 0)) {
        nuevo = malloc(sizeof(struct Jugador));
        nuevo->j = j;
        nuevo->sig = *lista;
        *lista = nuevo;
    } else if(ptr != NULL && compara(ptr->j,j) == 0){
        *ok = 0;
    } else {
        nuevo = malloc(sizeof(struct T_Nodo));
        nuevo->j = j;
        nuevo->sig = ptr;
        ant->sig = nuevo;
    }
}

int leerF(T_Lista *lista){
    struct Jugador j;
    int ok, numero_jugadores = 0;

    FILE *f = fopen(FICH, "rb");
    if(f!=NULL){
        while(fread(&j, sizeof(j), 1, f) == 1){
            insertar_jugador(lista, j, &ok);
            numero_jugadores += ok;
            //(*ok == 1) ? numero_jugadores++ : numero_jugadores;
        }
        fclose(f);
    }

    return numero_jugadores;
}