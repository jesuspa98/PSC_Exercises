/*
 * arbolbb.h
 *
 *  Created on: 4/4/2016
 *      Author: Jesús Parejo Aliaga
 */

#ifndef ARBOLBB_H_
#define ARBOLBB_H_
#include <stdio.h>

typedef struct T_Node* T_Tree;

struct T_Node {
	unsigned data;
	T_Tree left, right;
};

// Create the used structure to manage the available memory.
	void Creat(T_Tree *tree);

// Destroy the used structure.
	void Destroy(T_Tree *tree);

// Insert number in the tree.
	void Insertar(T_Tree* tree,unsigned number);

// Shows the tree's content in order.
	void Show(T_Tree tree);

// Save in the disk the tree's content.
	void Saves(T_Tree tree, FILE *file);

#endif //ARBOLBB_H_
