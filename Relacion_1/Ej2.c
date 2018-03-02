#include <stdio.h>

void change(int *p1, int *p2){
	printf("Antes de modificar: %d %d\n", *p1, *p2);
   	int aux = *p2;
    	p2 = &*p1;
    	p1 = &aux;
    	printf("Despues de modificar: %d %d\n", *p1, *p2);
}

int main(){
	int x = 10, y = 20;
	int *p1 = &x, *p2 = &y;
	printf("Ejercicio 2:\n");
	change(p1, p2);

	return 0;
}

