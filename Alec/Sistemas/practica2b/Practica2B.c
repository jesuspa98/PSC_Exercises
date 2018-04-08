/*
 ============================================================================
 Name        : Practica2B.c
 Author      : Alejandro Garau Madrigal
 Version     : V1.0
 Copyright   : MIT Copyright
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "arbolbb.h"

/**
 * Pide un n�mero "tam" al usuario, y
 * crea un fichero binario para escritura con el nombre "nfichero"
 * en que escribe "tam" numeros (unsigned int) aleatorios
 * Se utiliza srand(time(NULL)) para establecer la semilla (de la libreria time.h)
 * y rand()%100 para crear un n�mero aleatorio entre 0 y 99.
 */
void creafichero(char* nfichero) {
	int tam;
	FILE* file;
	int i, num;
	printf("Insertar tamaño: ");
	scanf("%d", &tam);
	file = fopen(nfichero, "wb");
	if(file == NULL){
	    perror("No se pudo abrir el fichero");
	} else {
	    srand(time(NULL));
	    for (i = 0; i < tam; i++){
	        num = rand() % tam;
	        if (fwrite(&num, sizeof(num), 1, file) != 1){
	            perror("Error writing on file");
	        }
	    }
	}
	fclose(file);
}
/**
 * Muestra por pantalla la lista de n�meros (unsigned int) almacenada
 * en el fichero binario "nfichero"
 */
void muestrafichero(char* nfichero) {
    FILE* file;
    unsigned int read;
    file = fopen(nfichero, "rb");
    if(file == NULL){
        perror("Couldn't open the file");
    }else{
        while(fread(&read, sizeof(read), 1, file) == 1){
            printf(" %d", read);
        }
        fclose(file);
    }
}

/**
 * Guarda en el arbol "*miarbol" los n�meros almacenados en el fichero binario "nfichero"
 */

void cargaFichero(char* nfichero, T_Arbol* miarbol) {
    FILE * file;
    unsigned int num;
    file = fopen(nfichero, "rb");
    if (file == NULL){
        perror("Error opening the file");
    } else {
        while (fread(&num, sizeof(num), 1, file) == 1){
            Insertar(miarbol, num);
        }
    }
    fclose(file);
}

int main(void) {
	char nfichero[50];
	printf ("Introduce el nombre del fichero binario:\n");
	fflush(stdout);
	scanf ("%s",nfichero);
	fflush(stdin);
	creafichero(nfichero);
	printf("\n Ahora lo leemos y mostramos\n");
	muestrafichero(nfichero);
	fflush(stdout);

	printf ("\n Ahora lo cargamos en el arbol\n");
	T_Arbol miarbol;
	Crear (&miarbol);
	cargaFichero(nfichero, &miarbol);
	printf ("\n Y lo mostramos ordenado\n");
	Mostrar(miarbol);
	fflush(stdout);
	printf("\n Ahora lo guardamos ordenado\n");
	FILE * fich;
	fich = fopen (nfichero, "wb");
	Salvar (miarbol, fich);
	fclose (fich);
	printf("\n Y lo mostramos ordenado\n");
	muestrafichero(nfichero);
	Destruir (&miarbol);

	return EXIT_SUCCESS;
}
