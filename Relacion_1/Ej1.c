#include <stdio.h>

void pointer(int *ptr){
	printf("Lo que contiene la variable es: %d\n", *ptr);
}

int main(){
	int x = 20;
	int *ptr = &x;
	printf("Ejercicio 1\n");
	pointer(ptr);

	return 0;
}
