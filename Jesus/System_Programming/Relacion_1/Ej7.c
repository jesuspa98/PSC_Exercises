/*
 * Created by jesuspa98 on 04/04/18.
 */
#include <stdio.h>
#define SIZE 100

typedef struct{
    char name[SIZE];
    char surname1[SIZE];
    char surname2[SIZE];
}Info1;

typedef struct{
    int age;
    int height;
}Info2;

typedef struct {
    Info1 id;
    Info2 physic;
}Person;

void fill1(Info1 *inf){
    printf("Introduce the name: ");
    fgets(inf->name,SIZE,stdin);

    printf("Introduce the first surname: ");
    fgets(inf->surname1,SIZE,stdin);
    
    printf("Introduce the second surname: ");
    fgets(inf->surname2,SIZE,stdin);
}

void fill2(Info2* inf){
    printf("Introduce the age: ");
    scanf("%d",&inf->age);

    printf("Introduce the height: ");
    scanf("%d",&inf->height);
}

void showPerson(Person* person)
{
    printf("Name: ");
    printf(person->id.name);

    printf("First Surname: ");
    printf(person->id.surname1);

    printf("Second Surname: ");
    printf(person->id.surname2);

    printf("Age: %i\n", person->physic.age);
    printf("Height: %i", person->physic.height);

}

int main(){

    Person person;

    fill1(&person.id);
    fill2(&person.physic);

    showPerson(&person);

    return 0;
}
