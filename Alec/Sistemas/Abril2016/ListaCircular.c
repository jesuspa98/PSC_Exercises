#include "ListaCircular.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

void crear(TListaCircular *lc) {
	*lc = NULL;
}


void insertar(TListaCircular *lc, char *nombre) {
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

void recorrer(TListaCircular lc) {
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

int longitud(TListaCircular lc) {
	TListaCircular first = lc;
	lc = lc->sig;
	int length = 1;
	while(lc != first) {
		length++;
		lc = lc->sig;
	}
	return length;
}

void mover(TListaCircular *lc,int n) {
	TListaCircular lista = *lc;
	int counter = 0;
	while(counter < n){
		lista = lista->sig;
		counter++;
	}
	*lc = lista;
}

void extraer(TListaCircular *lc,char *nombre){
    if(*lc == NULL)
        nombre = "";
    else {
        strcpy(nombre, (*lc)->nombre);

        int n = longitud(*lc);
        TListaCircular primero = *lc;

        mover(lc, n-1);
        TListaCircular siguiente = (*lc)->sig;
        (*lc)->sig = siguiente->sig;

        free(primero);
    }
}

/*
int main(){
    TListaCircular lc;
    crear(&lc);
    insertar(&lc, "a");
    insertar(&lc, "b");
    insertar(&lc, "c");
    insertar(&lc, "d");
    insertar(&lc, "j");
    recorrer(lc);
    printf("%d", longitud(lc));
    printf("jajaja      \n");
    extraer(&lc, "j");
    recorrer(lc);
    printf("%d", longitud(lc));
    printf("LOEEEL      \n");
    return 0;
}*/