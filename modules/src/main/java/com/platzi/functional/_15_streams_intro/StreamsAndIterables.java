package com.platzi.functional._15_streams_intro;

import com.platzi.functional.util.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class StreamsAndIterables {
    static void iterables() {
        /*
        Una de las principales caracteristicas de Java fue que introdujo las Colecciones de datos.
        Listas, mapas, sets, arboles, etc. y diferentes implementaciones de ellos.

        Antes de las colecciones teniamos los arreglos. Pero recorrer/iterar los elementos de una lista
        o un arreglo era casi el mismo proceso:
         */
        List<String> namesList = Utils.getListOf("Sinuhe", "Brenda", "Ada");

        for (String name : namesList) {
            System.out.println(name);
        }

//
//
//


//
//
//
//
//
//
        /*
        Aunque esto no esta mal, tiene algunas limitantes. Ademas que hace que el codigo
        se vuelva algo grande cuando muchas operaciones se tienen que hacer sobre una misma coleccion.
         */
        for (String name : namesList) {
            if (name != null && !name.isEmpty()) {
                //Hacer algo…
            }
        }

        //E intentar hacer este tipo de iteracion en paralelo seria muy complicado.
    }

    /**
     * Java 8 introduce un nuevo elemento al juego: Streams.
     */
    static void streams() {
        /*
        Podemos ver o imaginar a un stream como un flujo de datos, un rio de datos.
        Donde los datos van moviendose sin esperar a que alguien los mueva.

        Es decir, un Stream es una estructura auto-iterable. Cuando generamos un Stream,
        puede ser con datos ya definidos o proveer una manera de ir generando los datos que pasaran
        mediante este flujo.

        Podemos generar streams de cualquier fuente.
         */
        Stream<String> stringStream = Stream.of("Hola", "Adios");

//
//
//
//

        /*
        Incluso de fuente externas:
         */
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Stream<String> bufferStream = in.lines();

//
//

//
//
//
//
//
        /*
        Algo muy importante es que podemos obtener streams de coleciones existentes:
         */
        List<String> coolNames = Utils.getListOf("Sinuhe", "Ada", "Victoria", "Brenda");
        Stream<String> coolNamesStream = coolNames.stream();


        Set<String> platziCourses = new HashSet<>(coolNames);
        Stream<String> coursesStream = platziCourses.stream();

//
//

//
//
//
//

        /*
        E incluso obtener nuestros datos desde funciones generadoras:
         */
        AtomicInteger x = new AtomicInteger();
        Stream<Integer> countingStream = Stream.generate(() -> x.getAndIncrement());



        /*
        Algo todavia mas interesante de estas nuevas estructuras es que podemos paralelizar
        la iteracion de las mismas sin preocuparnos mucho
         */
        Stream<Integer> countingInParallelStream = countingStream.parallel();

        /*
        pero lo interesante viene despues, al obtener los datos de ese stream…
         */
    }
}
