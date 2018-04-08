//
// Created by Alejandro Garau Madrigal on 09/03/2018.
//

#ifndef __Lista_H__
#define __Lista_H__

typedef struct T_Nodo* T_Lista;

struct T_Nodo{
    unsigned num;
    T_Lista sig;
};

void crear(T_Lista* lista);
void destruir(T_Lista* lista);
void rellenar(T_Lista *lista);
void mostrar(T_Lista lista);
void escribir_fichero_binario(char* nombre, T_Lista lista);
void leer_fichero(char* nombre, T_Lista* lista);
void delete_last();

#endif /* __Lista_H__ */
