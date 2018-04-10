/*
 * Created by jesuspa98 on 05/04/18
 */
#include <stdio.h>

// TODO inicializa el array
void create(int queue[], int size){

}

// TODO dada una prioridad y un identificador de proceso, lo añade al final de la lista que le corresponde
void newProcess(int queue[]){

}

// TODO elimina de la lista el proceso mas prioritario que le corresponde ejecutarse. si no existen procesos por ejecutar se indicara con un mensaje de aviso
void runProcess(int queue[]){

}

// TODO dado un identificador de proceso devuelve la prioridad de este. si el id del proceso no existe se devolvera -1
void search(int queue[]){

}

// TODO recorre la estructura para mostrar los procesos existentes que estan disponibles para ejecucion ordenados por prioridad
void show(int queue[]){

}

// TODO se eliminan todos los procesos de la cola de prioridad
void destroy(int queue[]){

}

// Principal Program
int main() {
    int size, queue[1];
    printf("Enter the maximum array size:");
    scanf("%d", size);

    create(&queue, size);
}