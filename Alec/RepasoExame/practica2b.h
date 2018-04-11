#include <stdlib.h>
#include <stdio.h>

typedef struct TNodo* T_Arbol;

struct TNodo{
	unsigned dato;
	T_Arbol izq, der;
};


void crear(T_Arbol *arbol);

void destruir(T_Arbol *arbol);

void insertar(T_Arbol *arbol, unsigned num);

void mostrar(T_Arbol arbol);

void salvar(T_Arbol arbol, FILE* fichero);