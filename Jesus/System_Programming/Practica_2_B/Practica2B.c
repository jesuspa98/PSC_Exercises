/*
 ============================================================================
 Name        : Practica2B.c
 Author      : Jesús Parejo Aliaga
 Version     : 1.1.
 Copyright   : Your copyright notice
 Description : Practice 2 B. System Programming
 ============================================================================
 */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "arbolbb.h"

/**
 * Ask for a number "size" to the user, and create a binary write file with the name "nFile"
 * in which it's used sradn(time(NULL)) to set the seed (library time.h) and rand%SIZE
 * to create a random number between 0 and SIZE - 1
 */

void createFile(char *nFile) {
    int SIZE, i, number;
    FILE* f;
    printf("Enter the number SIZE: ");
    fflush(stdout);
    scanf("%d", &SIZE);

    f = fopen(nFile, "wb");
    if(f == NULL){
        perror("Couldn't open the file");
    }else{
        srand(time(NULL));

        for(i = 0; i < SIZE; i++){
            number = rand()%SIZE;

            if(fwrite(&number, sizeof(number), 1, f) != 1){
                perror("ERROR writing the value in the file");
            }
        }

        fclose(f);
    }
}

/**
 * Show in screen the numbers' list (unsigned int) stored in the binary file "nFile"
 */
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

/**
 * Save in the tree "myTree" the stored numbers in the binary file "nFile"
 */
void loadFile(char *nFile, T_Tree *myTree) {
    FILE* f;
    int number;
    f = fopen(nFile, "rb");
    if(f == NULL){
        perror("Couldn't open the file");
    }else{
        while(fread(&number, sizeof(number), 1, f) == 1){
            Insertar(myTree, number);
        }
        fclose(f);
    }
}

/**
 * Int main to prove all methods.
 */
int main(void) {
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
	T_Tree myTree;
    Creat(&myTree);
    loadFile(nFile, &myTree);

	printf ("\n And show it organized\n");
    Show(myTree);
	fflush(stdout);
	printf("\n Now, we save it organized\n");
	FILE * file;
	file = fopen (nFile, "wb");
	Saves (myTree, file);
	fclose (file);
	printf("\n And show it organized\n");
	showFile(nFile);
    Destroy(&myTree);

	return EXIT_SUCCESS;
}