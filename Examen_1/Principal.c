/*
 * Principal.c
 *
 *  Created on: 05/04/2018
 *      Author: Jesús Parejo Aliaga
 */

#include "ListaJugadores.h"
#include <stdio.h>

int main(){

	TLista lj;
	crearLista(&lj);  // Inicializo la lista
    int num;
	num=leer_fichero_lista (&lj); //leo los datos del fichero binario y los almacenos ordenados y sin duplicados
	printf("Hay un total de %d jugadores\n",num);
	fflush(stdout);

	mostrar_lista(lj);
	fflush(stdout);

	destruir (&lj);

	/***************************************/
	return 0;
}
