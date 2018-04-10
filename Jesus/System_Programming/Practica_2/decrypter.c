//
// Created by jesuspa98 on 16/03/18.
//
#include <stdio.h>
#include <stdlib.h>

const unsigned int key[4] = {128, 129, 130, 131};
const unsigned int delta = 0x9e3779b9;
const unsigned int initsum = 0xC6EF3720;

void decrypt(unsigned int* values, unsigned const int* keys){
    unsigned int sum = initsum;
    int i;

    for(i = 0; i < 32; i++){
        values[1] -= ((values[0] << 4) + keys[2]) ^ (values[0] + sum) ^ ((values[0] >> 5) + keys[3]);
        values[0] -= ((values[1] << 4) + keys[0]) ^ (values[1] + sum) ^ ((values[1] >> 5) + keys[1]);
        sum -= delta;
    }
    /*
     * After 32 iterations, in values we have decrypted block.
     */
}

void decryptFile(char* inputFile, char* outputFile){
    unsigned int orgSize, numBlocks, sizeEncrypted;
    FILE* input = fopen(inputFile, "rb");
    unsigned int* workZone;
    int i;

    if(input==NULL){
        perror("ERROR reading input file\n");
        return;
    }

    if(fread(&orgSize, sizeof(unsigned int), 1, input) != 1){
        perror("ERROR reading original size\n");
        free(input);
        fclose(input);
    }

    numBlocks = orgSize/(2* sizeof(unsigned int));  // Size of unsigned int * 2 = 8

    if(orgSize%(2* sizeof(unsigned int))!=0){
        numBlocks++;
    }

    sizeEncrypted = numBlocks * (2 * sizeof(unsigned int)); // number of encrypted bytes
    //workZone = (unsigned int*) malloc(sizeEncrypted);
    workZone = malloc(sizeEncrypted* sizeof(unsigned int));


    if(workZone == NULL){
        perror("ERROR reserving memory\n");
        free(input);
        return;
    }

    if(fread(workZone, (2* sizeof(unsigned int)), numBlocks, input) != numBlocks){
        perror("ERROR reading encrypted data\n");
        fclose(input);
        free(workZone);
        return;
    }
    fclose(input);

    //TODO Descrypt work here DONE

    /*for(i = 0; i < numBlocks; i++){
        decrypt(workZone+i*2, key);
    }*/

    unsigned int* v = workZone;
    for(i = 0; i < numBlocks; i++){
        decrypt(v, key);
        v+=2;
    }

    FILE* output = fopen(outputFile, "wb");

    if(output == NULL){
        perror("ERROR opening output file\n");
        free(workZone);
        return;
    }

    if(fwrite(workZone, 1, orgSize, output) != orgSize){
        perror("ERROR writing output file\n");
        fclose(output);
        free(workZone);
        return;
    }

    fclose(output);
    free(workZone);
}

int main(int argc, char** argv){
    if(argc < 3){
        perror("ERROR: Needed at least 2 args\n");
        return -1;
    } else {
        decryptFile(argv[1], argv[2]);
    }

    return 0;
}