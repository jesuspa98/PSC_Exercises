

typedef struct TNodo * TListaCircular;
struct TNodo{
	char nombre[30];
	TListaCircular sig;
};


//crea una lista circular vac�a (sin ning�n nodo)
void crear(TListaCircular *lc);

//inserta un nuevo nodo con el dato nombre al final de la lista circular
void insertar(TListaCircular *lc,char *nombre);

//recorre la lista circular escribiendo los nombres de los nodos en la
//pantalla
void recorrer(TListaCircular lc);

//devuelve el n�mero de nodos de la lista
int longitud(TListaCircular lc);

//mueve el puntero exterto de la lista n nodos (siguiendo la direcci�n de la
//lista)
void mover(TListaCircular *lc,int n);

//elimina el primer nodo de la lista, y devuelve el nombre que contiene
//a trav�s del par�metro nombre
void extraer(TListaCircular *lc,char *nombre);
