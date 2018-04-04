/*
 *  Created by jesuspa98 on 4/04/18.
 */

#include <stdio.h>
#include <stdlib.h>
#include "arbolbb.h"

/*
 * Create the used structure to manage the available memory.
 */
void Creat(T_Tree *tree){
    *tree = NULL;
}

/*
 * DDestroy the used structure.
 */
void Destroy(T_Tree *tree){
    if(*tree != NULL){
        Destroy(&(*tree)->left);
        Destroy(&(*tree)->right);
        free(*tree);
        *tree = NULL;
    }
}

/*
 * Insert number in the tree.
 */
void Insertar(T_Tree* tree, unsigned number){
    T_Tree newNode;
    if(*tree == NULL){
        newNode = (T_Tree) malloc(sizeof(struct T_Node));
        newNode->data = number;
        newNode->left = newNode->right = NULL;
        *tree = newNode;
    }else{
        if((*tree)->data > number){
            Insertar(&((*tree)->left), number);
        }else if((*tree)->data < number){
            Insertar(&((*tree)->right), number);
        }
    }
}

/*
 * Shows the tree's content in order.
 */
void Show(T_Tree tree){
    if(tree != NULL){
        Show(tree->left);
        printf("%d ", tree->data);
        Show(tree->right);
    }
}

/*
 * Save in the disk the tree's content.
 *
 * Coge un arbol binario y lo escribe en un fichero ordenadamente,
 * subarbol izquierdo, nodo, subarbol derecho.
 */
void Saves(T_Tree tree, FILE *file){
    if(file != NULL){
        if(tree != NULL){
            Saves(tree->left, file);
            fwrite(&tree->data, sizeof(tree->data), 1, file);
            Saves(tree->right, file);
        }
    }else{
        perror("Couldn't open the file.");
    }
}
