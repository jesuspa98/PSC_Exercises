//
// Created by jaysus on 7/03/18.
//

#include "lista.h";
#include <stdlib.h>;
#include <stdio.h>

void crear(T_Lista *lista) {
    *lista = '/0';
}

void destruir(T_Lista *lista) {
//    T_Lista *ant, *ptr;
//    if((*lista) != NULL) {
//        ant = *lista;
//        ptr = (*lista)->sig;
//
//        while(ptr != NULL) {
//            free(ant);
//            ant = ptr;
//            ptr = (*ptr)->sig;
//        }
//    }

    T_Lista aux, ptr = *lista;
    while(ptr != NULL) {
        aux = ptr;
        ptr = ptr->sig;
        free(aux);
    }
    *lista = NULL;
}

void rellenar(T_Lista *lista) {
//    int tam, contador, elem;
//    T_Lista ptr, ptr2 = NULL;
//
//    printf("Introduzca el contador de elementos: ");
//    scanf("%d", &tam);
//
//    for(contador = 0; contador < tam; contador++) {
//        scanf("%d", &elem);
//        ptr = malloc(sizeof(struct T_Nodo));
//        ptr->num = elem;
//
//        if(ptr2 == NULL) {
//            *lista = ptr;
//            ptr2 = ptr;
//        } else {
//            ptr2->sig = ptr;
//            ptr2 = ptr;
//        }
//    }

    int tam, contador, elem;
    struct T_Nodo *ptr, *ant = NULL, *inicio;
    printf("Introduzca cantidad de elementos: ");
    scanf("%d", &tam);

    for(contador = 0; contador < tam; contador++) {

        printf("Introduzca elemento %d: ", contador);
        scanf("%d", &elem);

        ptr = malloc(sizeof(struct T_Nodo));

        if(ant != NULL)
            ant->sig= ptr;
        else
            inicio = ptr;

        ptr->num = elem;
        ant = ptr;
    }
    ptr->sig = lista;
    lista = inicio;
}

void mostrar(T_Lista* lista) {
    while(lista != NULL) {
        printf("%d ", (*lista)->num);
        lista = (*lista)->sig;
    }
}

void deleteLast(T_Lista* lista) {
    T_Lista *current = lista, last = NULL;
    if(current != NULL) {
        while (current != NULL) {
            last = current;
            current = (*current)->sig;
        }
        last->sig = NULL;
        free(current);
    }
}

void reverse(T_Lista *lista) {
    T_Lista prev = NULL, head = *lista, next;
    while(head != NULL) {
        next = head->sig;
        head->sig = prev;
        prev = head;
        head = next;
    }
    *lista = prev;
}

void EscribirF(char *nombre, T_Lista lista) {
    FILE *f = fopen(nombre, "wb");
    if(f != NULL)
    {
        while(lista != NULL)
        {
            fwrite(&(lista->num), sizeof(lista->num), 1, f);
            lista = lista->sig;
        }
    }

    fclose(f);
}

void LeerDeFichero(char *nombre, T_Lista *lista) {
    FILE *f = fopen(nombre, "rb");
    int num, leidos;
    T_Lista final = NULL, nuevo;
    if(f != NULL) {
        while(!feof(f)) {
            leidos = fread(&num, sizeof(num), 1, f);
            if(leidos > 0) {
                nuevo = malloc(sizeof(struct T_Nodo));
                nuevo->num = num;
                nuevo->sig = NULL;
                if(final == NULL) {
                    *lista = nuevo;
                    final = nuevo;
                } else {
                    final->sig = nuevo;
                    final = nuevo;
                }
            }
        }
    }

    fclose(f);
}