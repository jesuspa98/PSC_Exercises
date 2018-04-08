//
// Created by Alejandro Garau Madrigal on 16/03/2018.
//
#include <stdlib.h>
#include <stdio.h>

const unsigned int key[4] = { 128, 129, 130, 131};
const unsigned int delta = 0x9e3779b9;
const unsigned int initsum = 0xC6EF3720;


void decrypt(unsigned int* values, const unsigned int* keys){
    unsigned int sum = initsum;
    int i;
    for(i = 0; i < 32; i++){
        values[1] -= ((values[0] << 4) + keys[2]) ^ (values[0] + sum) ^ ((values[0] >> 5) + keys[3]);
        values[0] -= ((values[1] << 4) + keys[0]) ^ (values[1] + sum) ^ ((values[1] >> 5) + keys[1]);
        sum -= delta;
    }
    /*
     * After 32 iterations, in values we have the decrypted block.
     */
}

/*void decrypt_file(char* input_file_name, char* output_file_name){
    FILE* input = fopen(input_file_name, "rb");
    unsigned int file_size_decrypted;
    unsigned int file_size_encrypted;
    if(fread(&file_size_decrypted, sizeof(unsigned int), 1, input) == 1){
        file_size_encrypted = round(((double) file_size_decrypted) / 8) * 8;
        void* dynamic = malloc(file_size_encrypted * sizeof(FILE));
    }
    Versión Mía.
}*/

void decrypt_file(char* input_file, char* output_file){
    unsigned int original_size, blocks_to_use, size_encrypted;
    FILE* input = fopen(input_file, "rb");
    unsigned int* work; // buscar mejor nombre XD
    int i;
    if(input == NULL){
        printf("Error genérico 1");
        return;
    }
    if(fread(&original_size, sizeof(unsigned int), 1, input) != 1){
        printf("Error generérico 2");
        free(input);
        return;
    }
    blocks_to_use = original_size / (2 * sizeof(unsigned int)); // size of unsigned int * 2 = 8
    if(original_size % (2 * sizeof(unsigned int)) != 0 ){
        blocks_to_use++;
    }
    size_encrypted = blocks_to_use * (2 * sizeof(unsigned int)); // cantidad de bytes encriptados
    work = malloc(size_encrypted * sizeof(unsigned int));
    if(work == NULL){
        printf("Error genérico 3");
        free(input);
        return;
    }
    if(fread(work, 2 * sizeof(unsigned int), blocks_to_use, input)  != blocks_to_use){
        printf("Error genérico 4");
        fclose(input);
        free(work);
        return;
    }
    fclose(input);
    unsigned int* v = work;
    for(i = 0; i < blocks_to_use; i++){
        decrypt(v, key);
        v+=2;
    }

    FILE* output = fopen(output_file, "wb");
    if(output == NULL){
        printf("Error 5");
        free(work);
        return;
    }
    if(fwrite(work, 1, original_size, output) != original_size){
        printf("Error 6");
        free(work);
        fclose(output);
        return;
    }
    free(work);
    fclose(output);
}


int main(int argc, char** argv){
   if(argc < 3){
       printf("ERROR: Needed at least 2 args\n");
       return -1;
   } else {
       decrypt_file(argv[1], argv[2]);
   }
    return 0;
}