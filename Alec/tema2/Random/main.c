#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct TNodo *TListaC;
struct TNodo{
    char nombre[30];
    TListaC sig;
};


void cargarFichero(char* nombre, TListaC* lista);
void insertar(TListaC* lista, char* nombre);
void extraer(TListaC* lista, char* nombre);
int longitud(TListaC lista);

int main() {
    printf("Hello, World!\n");
    return 0;
}


void cargarFichero(char* nombre, TListaC* lista){
    char nom[30];
    FILE* file = fopen(nombre, "rt");
    if(file != NULL) {
        while (fscanf(file, "%s", nom) != 0) {
            insertar(lista, nombre);
        }
        fclose(file);
    }
}

void insertar(TListaC* lista, char* nombre){
    TListaC nuevo;
    nuevo = malloc(sizeof(struct TNodo));
    strcpy(nuevo->nombre, nombre);
    if(*lista == NULL){
        *lista = nuevo;
        nuevo->sig = nuevo;
    } else{
        nuevo->sig = (*lista)->sig;
        (*lista)->sig = nuevo;
        *lista = nuevo;
    }
}

int longitud(TListaC lista){
    int len = 0; TListaC aux;
    if(lista != NULL){
        aux = lista->sig;
        len = 1;
        while(aux != lista){
            len++;
            aux = lista->sig;
        }
    }
    return len;
}


void extraer(TListaC* lista, char* nombre){
    TListaC aux;
    if(*lista == NULL){
        nombre[0] = '\0';
    } else {
        strcpy(nombre, (*lista)->sig->nombre);
        aux = (*lista)->sig;
        if(aux == *lista){
            
        }
    }
}