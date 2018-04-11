#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "practica2b.c"


void createFile(char *fileName){
	int size, i, number;
	FILE* f;
	printf("Enter the number SIZE: ");
	fflush(stdout);
	scanf("%d", &size);

	f = fopen(fileName, "wb");

	if(f == NULL){
		perror("Couldn't open the file");
	} else {
		srand(time(NULL));

		for(i = 0; i < size; i++){
			number = rand()%size;

            if(fwrite(&number, sizeof(number), 1, f) != 1){
                perror("ERROR writing the value in the file");
            }
		}
		fclose(f);
	}
}

void showFile(char *nFile){
    FILE* f;
    int number;

    f = fopen(nFile, "rb");

    if(f == NULL){
        perror("Couldn't open the file");
    }else{
        while(fread(&number, sizeof(number), 1, f) == 1){
            printf(" %d", number);
        }
        fclose(f);
    }

}

void loadFile(char *nFile, T_Arbol *myTree) {
    FILE* f;
    int number;
    f = fopen(nFile, "rb");
    if(f == NULL){
        perror("Couldn't open the file");
    }else{
        while(fread(&number, sizeof(number), 1, f) == 1){
            insertar(myTree, number);
        }
        fclose(f);
    }
}

int main() {
	char nFile[50];

	printf("Enter the binary file's name:\n");
	fflush(stdout);
	scanf ("%s",nFile);
	fflush(stdin);
    createFile(nFile);

	printf("\n Now, we reed the file and show it\n");
    showFile(nFile);
	fflush(stdout);

	printf ("\n Now, we load in the tree\n");
	T_Arbol myTree;
    crear(&myTree);
    loadFile(nFile, &myTree);

	printf ("\n And show it organized\n");
    mostrar(myTree);
    printf("\n");
	fflush(stdout);
	printf("\n Now, we save it organized\n");
	FILE * file;
	file = fopen (nFile, "wb");
	salvar (myTree, file);
	fclose (file);
	printf("\n And show it organized\n");
	showFile(nFile);
	printf("\n");
    destruir(&myTree);

	return EXIT_SUCCESS;
}