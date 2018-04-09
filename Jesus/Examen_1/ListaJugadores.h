#include <stdlib.h>
#include <stdio.h>

typedef struct TNodo * TLista;
struct Jugador{
	int codjugador;     //código que identifica al jugador
	int codequipo;      //código de equipo
	int posicion;       //la posición del jugador en el campo puede ser 1,2,3,4 o 5
	float precio;       //valor de mercado del jugador en millones de euros
};
struct TNodo{
	struct Jugador jugador;
	TLista sig;
};


//crea una lista vacía (sin ningún nodo)
void crearLista(TLista *lc);

//Devuelve 0 si j1 es igual que j2, es decir misma posición, mismo precio y mismo código. Devuelve -1 si j1 es menor que j2 según el criterio descrito y +1 en caso contrario
int compara (struct Jugador j1, struct Jugador j2);

//Inserta el jugador en la lista siempre que no esté repetido. Para insertarlo correctamente debe cumplirse el criterio de ordenación indicado al comienzo del enunciado. En ok devolverá un 1 si se ha podido insertar, y un 0 si no se pudo insertar porque estuviera repetido ese jugador en la lista
void insertar_jugador(TLista *lista, struct Jugador j, int *ok);

//Vuelca en la lista el contenido existente en el fichero JUGADOR.BIN. Cuidado: en el fichero pueden estar los jugadores almacenados en cualquier orden, y el volcado debe realizarse teniendo en cuenta el criterio de ordenación indicado con anterioridad en el enunciado. La función deberá devolver el total de jugadores insertados en este proceso. Debe controlarse que en la lista no puede haber jugadores repetidos. Solo se insertarán en la lista los jugadores no repetidos.
int leer_fichero_lista(TLista *lista);

//Muestra en pantalla el listado de jugadores
void mostrar_lista(TLista lista);

//Añade el jugador en el fichero JUGADOR.BIN. El jugador se almacenará al final del fichero. En ok devolverá un 1 si se ha podido añadir, y un 0 si no se pudo añadir por algún error en el manejo del fichero
void agregar_a_fichero (struct Jugador jugador, int *ok);

//Destruye la lista de jugadores, liberando todos los nodos de la misma de memoria.
void destruir(TLista *lista);