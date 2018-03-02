#include <stdio.h>

void leerTeclado(int *ptr, int size){
	int i;
	printf("Introduzca %d numeros: ", size);
	for(i = 0; i < size; i++){
		scanf("%d", &ptr[i]);
	}
}

void imprimirPantalla(int *ptr, int size){
	int i;
	printf("Los numeros son: ");
	for(i = 0; i < size; i++){
		printf("%d ", ptr[i]);
	}
}


int main(){
	int size;
	printf("Ejercicio 3:\nIntroduzca el tamaño: ");
	scanf("%d", &size);
	int array[size];
	int *ptr = &array[0];
	leerTeclado(ptr, size);
	imprimirPantalla(ptr, size);

	return 0;
}
