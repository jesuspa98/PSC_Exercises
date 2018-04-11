#include "practica2b.h"




void crear(T_Arbol *arbol){
	*arbol = NULL;
}

void destruir(T_Arbol *arbol){
	if(*arbol != NULL){
		destruir(&(*arbol)->izq);
		destruir(&(*arbol)->der);
		free(*arbol);
		*arbol = NULL;
	}
}


void insertar(T_Arbol* tree, unsigned number){
    T_Arbol newNode;
    if(*tree == NULL){
        newNode = (T_Arbol) malloc(sizeof(struct TNodo));
        newNode->dato = number;
        newNode->izq = newNode->der = NULL;
        *tree = newNode;
    }else{
        if((*tree)->dato > number){
            insertar(&((*tree)->izq), number);
        }else if((*tree)->dato < number){
            insertar(&((*tree)->der), number);
        }
    }
}


void mostrar(T_Arbol arbol){
	if(arbol != NULL){
		mostrar(arbol->izq);
		printf("%d ", arbol->dato);
		mostrar(arbol->der);
	}
	fflush(stdout);
}

void salvar(T_Arbol arbol, FILE* fichero){
	if(fichero != NULL){
		if(arbol != NULL){
			salvar(arbol->izq, fichero);
			fwrite(&arbol->dato, sizeof(arbol->dato), 1, fichero);
			salvar(arbol->der, fichero);
		}
	} else {
		perror("No se ha podido abrir el fichero");
	}
}

