cmake_minimum_required(VERSION 3.9)

project(Concurrencia C)

set(CMAKE_C_STANDARD 90)

# add_executable(Sistemas Sistemas/Random/main.c)
# add_executable(Sistemas2 Sistemas/Random/EjerciciosTema2.c)
# add_executable(Sistemas3 Sistemas/Tema2/Random/TLista.c Sistemas/Random/TLista.h)
add_executable(Sistemas4 Sistemas/EjercicioClase4/TListaD.h Sistemas/EjercicioClase4/TListaD.c)
add_executable(Abril17 Sistemas/Abril17/Jugador.c Sistemas/Abril17/Principal.c Sistemas/Abril17/ListaJugadores.h)
add_executable(Practica1 Sistemas/practica1/gestion_memoria.h Sistemas/practica1/practica1.c Sistemas/practica1/gestion_memoria.c)
add_executable(Practica2 Sistemas/practica2/decrypter.c)
add_executable(Practica2B Sistemas/practica2b/arbolbb.c Sistemas/practica2b/Practica2B.c Sistemas/practica2b/arbolbb.h)
add_executable(Abril2016 Sistemas/Abril2016/ListaCircular.c Sistemas/Abril2016/ListaCircular.h Sistemas/Abril2016/Principal.c)