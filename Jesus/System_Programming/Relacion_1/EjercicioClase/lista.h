//
// Created by jaysus on 7/03/18.
//

#ifndef __LISTA_H__
#define __LISTA_H__
typedef
struct T_Nodo* T_Lista
;
struct T_Nodo {
    unsigned num;
    T_Lista sig;
};
// Crea la estructura utilizada
void Crear(T_Lista* lista);
// Destruye la estructura utilizada.
void Destruir(T_Lista* lista);
// Rellenar lista
void Rellenar (T_Lista *lista);
void Mostrar (T_Lista lista);
void EscribirF(char *nombre,T_Lista lista);
void LeerDeFichero(char *nombre,T_Lista *l);
#endif
/* __LISTA_H__ */
