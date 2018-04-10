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
	printf("%s\n", first->nombre);
	lc = lc->sig;
	while(lc != first) {
		printf("%s\n", lc->nombre);
		lc = lc->sig;
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
	// TODO hacerlo bien la verdad sinceramente segmentation fault 11
	TListaCircular current, prev, first;
	first = *lc;
	current = (*lc)->sig;
	prev = NULL;
	while(current != first && strcmp(current->nombre, nombre) != 0) {
		prev = current;
		current = current->sig;
	}
    if(strcmp(current->nombre, nombre) == 0) {
	    if(prev == NULL){
            while(current->sig != first){
                current = current->sig;
                }
            current = first->sig;
            (*lc) = current;
	    } else {
            prev->sig = current->sig;
            *lc = prev;
	    }
	}
}


int main(int argc, char const *argv[]) {
	TListaCircular l;
	crear(&l);
	insertar(&l, "xD");
	insertar(&l, "rob");
	insertar(&l, "erto");
	insertar(&l, "xd");
	recorrer(l);
	printf("eeeee!\n");
	extraer(&l, "xd");
	printf("not eeee!\n");
	recorrer(l);
	return 0;
}