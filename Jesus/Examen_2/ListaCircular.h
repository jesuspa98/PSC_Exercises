

typedef struct TNodo * TListaCircular;
struct TNodo{
	char nombre[30];
	TListaCircular sig;
};


//crea una lista circular vacía (sin ningún nodo)
void crear(TListaCircular *lc);

//inserta un nuevo nodo con el dato nombre al final de la lista circular
void insertar(TListaCircular *lc,char *nombre);

//recorre la lista circular escribiendo los nombres de los nodos en la
//pantalla
void recorrer(TListaCircular lc);

//devuelve el número de nodos de la lista
int longitud(TListaCircular lc);

//mueve el puntero exterto de la lista n nodos (siguiendo la dirección de la
//lista)
void mover(TListaCircular *lc,int n);

//elimina el primer nodo de la lista, y devuelve el nombre que contiene
//a través del parámetro nombre
void extraer(TListaCircular *lc,char *nombre);
