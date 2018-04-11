/*
 * Created by jesuspa98 on 05/04/18
 */
#include <stdio.h>
typedef struct TNode* TList;
struct TNode{
    int number;
    TList next;
};

// TODO Inicializa el array
void create(int queue[], int size){
    int i;
    for(int i = 0; i < size; i++){
        queue[i] = NULL;
    }
}

// TODO Dada una prioridad y un identificador de proceso, lo añade al final de la lista que le corresponde
void newProcess(int queue[]){

}

// TODO Elimina de la lista el proceso mas prioritario que le corresponde ejecutarse. si no existen procesos por ejecutar se indicara con un mensaje de aviso
void runProcess(int queue[]){

}

// TODO Dado un identificador de proceso devuelve la prioridad de este. si el id del proceso no existe se devolvera -1
void search(int queue[]){

}

// TODO Recorre la estructura para mostrar los procesos existentes que estan disponibles para ejecucion ordenados por prioridad
void show(int queue[]){

}

// TODO Se eliminan todos los procesos de la cola de prioridad
void destroy(int queue[]){

}

// Principal Program
int main() {
    int size;
    printf("Enter the maximum array size:");
    scanf("%d", &size);
    int *queue[size];
    create(&queue, size);

}