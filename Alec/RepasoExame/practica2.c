#include <stdio.h>
#include <stdlib.h>


const unsigned int delta = 0x9e3779b9;
const unsigned int initsum = 0xC6EF3720;
const unsigned int key[4] = {128, 129, 130, 131};


void decrypt(unsigned int* values, unsigned const int* keys) {
	unsigned int counter = 0;
	unsigned int sum = initsum;
	while(counter < 32){
		values[1] = ((values[0] << 4) + keys[2]) ^ (values[0] + sum) ^ ((values[0] >> 5) + keys[3]);
		values[0] = ((values[1] << 4) + keys[0]) ^ (values[1] + sum) ^ ((values[1] >> 5) + keys[1]);
		sum -= delta;
		counter++;
	}
}


void decryptFile(char* inputFile, char* outputFile){
	unsigned int originalSize, numBlocks, sizeEncrypted;
	unsigned int* workZone;
	FILE* input;
	input = fopen(inputFile, "rb");

	if(input == NULL){
		printf("Error while opening the %s file...\n", inputFile);
		return;
	}

	if(fread(&originalSize, sizeof(originalSize), 1, input) != 1){
		perror("ERROR reading original size\n");
        free(input);
        fclose(input);
        return;
	}

	numBlocks = originalSize / 2*sizeof(unsigned int); // sizeof(unsigned int) = 4


	if(originalSize % (2* sizeof(unsigned int)) != 0) {
		numBlocks++;
	}

	sizeEncrypted = numBlocks * 2*sizeof(unsigned int) + numBlocks;
	workZone = (unsigned int*) malloc(sizeEncrypted * sizeof(unsigned int));

	if(workZone == NULL){
		perror("Error while reserving");
		fclose(input);
		return;
	}

	if(fread(workZone, (2*sizeof(unsigned int)), numBlocks, input) != numBlocks){
		perror("ERROR reading encrypted data\n");
        fclose(input);
        free(workZone);
        return;
	}
	
	fclose(input);

	unsigned int* v = workZone;
	unsigned i;

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


	if(fwrite(workZone, 1, originalSize, output) != originalSize){
		perror("ERROR writing output file\n");
        fclose(output);
        free(workZone);
        return;
	}

  	fclose(output);
    free(workZone);
}


int main(int argc, char* argv[]){
	if (argc < 3){
		printf("ERROR: needed at least 2 args\nExtiging program...");
	} else {
		decryptFile(argv[1], argv[2]);
	}
	return 0;
}