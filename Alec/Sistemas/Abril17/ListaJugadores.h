#include <stdlib.h>
#include <stdio.h>

struct Jugador {
	int codigo_jugador; //c�digo que identifica al jugador
	int codigo_equipo; //c�digo de equipo
	int posicion; //la posici�n del jugador en el campo puede ser 1,2,3,4 o 5
	float precio; //valor de mercado del jugador en millones de euros
} ;

typedef struct TNodo * TLista;
struct TNodo{
	struct Jugador jug;;
	TLista sig;
};


//crea una lista vac�a (sin ning�n nodo)
void crearLista(TLista *lc);

int compara (struct Jugador j1, struct Jugador j2);
//Devuelve 0 si j1 es igual que j2, es decir misma posici�n, mismo precio y mismo c�digo. Devuelve -1 si j1 es menor que j2 seg�n el criterio descrito y +1 en caso contrario


void insertar_jugador(TLista *lista, struct Jugador j, int *ok);
//Inserta el jugador en la lista siempre que no est� repetido. Para insertarlo correctamente debe cumplirse el criterio de ordenaci�n indicado al comienzo del enunciado. En ok devolver� un 1 si se ha podido insertar, y un 0 si no se pudo insertar porque estuviera repetido ese jugador en la lista

int leer_fichero_lista(TLista *lista);
//Vuelca en la lista el contenido existente en el fichero JUGADOR.BIN. Cuidado: en el fichero pueden estar los jugadores almacenados en cualquier orden, y el volcado debe realizarse teniendo en cuenta el criterio de ordenaci�n indicado con anterioridad en el enunciado. La funci�n deber� devolver el total de jugadores insertados en este proceso. Debe controlarse que en la lista no puede haber jugadores repetidos. Solo se insertar�n en la lista los jugadores no repetidos.


void mostrar_lista(TLista lista);
//Muestra en pantalla el listado de jugadores


void agregar_a_fichero (struct Jugador jugador, int *ok);
//A�ade el jugador en el fichero JUGADOR.BIN. El jugador se almacenar� al final del fichero. En ok devolver� un 1 si se ha podido a�adir, y un 0 si no se pudo a�adir por alg�n error en el manejo del fichero

void destruir(TLista *lista);
//Destruye la lista de jugadores, liberando todos los nodos de la misma de memoria.

