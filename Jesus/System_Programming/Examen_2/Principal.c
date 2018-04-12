/*
 *  Principal.c
 *
 *  Created on: 09/04/2018
 *      Author: Jesús Parejo Aliaga
 */

#include "ListaCircular.h"
#include <stdio.h>

// Lee el fichero y lo introduce en la lista
void cargarFichero (char *nombreFich, TListaCircular *lc){
	FILE* f = fopen(nombreFich, "rb");
	char name[30];

	if(f == NULL){
	    perror("Error abriendo fichero");
	}else{
	    while(fscanf(f, "%s", name) != -1){
	        insertar(&*lc, name);
	    }
	    fclose(f);
	}
}


int main(){

	TListaCircular lc;
	crear(&lc);

	char nombre[30];

	int n;

	cargarFichero ("listaNombres.txt", &lc);
    /*insertar(&lc, "a");
    insertar(&lc, "b");
    insertar(&lc, "c");
    insertar(&lc, "d");
    insertar(&lc, "e");
    insertar(&lc, "f");
    insertar(&lc, "g");
    insertar(&lc, "h");*/
	recorrer(lc);
	printf("\nIntroduce un numero entre 0 y 60: ");
	fflush(stdout);
	scanf("%d",&n);
	while (longitud(lc)>1){
			mover(&lc,n);
			extraer(&lc,nombre);
			printf("%s ha salido del circulo \n",nombre);
		}

	extraer(&lc,nombre);
	printf("--------------------------------------\n");
	printf("%s ha sido seleccionado !!!!! \n",nombre);
	fflush(stdout);

	return 0;
}
