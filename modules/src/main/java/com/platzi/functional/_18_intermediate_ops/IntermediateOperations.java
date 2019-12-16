package com.platzi.functional._18_intermediate_ops;

import com.platzi.functional.util.Utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class IntermediateOperations {
    static void operaciones() {
        /*
        Las operaciones intermedias, como se vio en el modulo anterior, son operaciones que
        retornan un nuevo Stream.

        Estas operaciones son:

           - filter()
           - map()
           - flatMap()
           - distinct()
           - limit()
           - peek()
         */

        //filter
        //Toma un Predicate que indica si debemos o no considerar el elemento para el nuevo Stream
        Stream<Integer> evenNumbersStream = Stream.iterate(0, i -> i + 1);
        evenNumbersStream.filter(i -> i % 2 == 0); //Solo los numeros pares.


//
//
//
//
//
//
//

        //map
        //Convertir un Stream de tipo T a un Stream de tipo V. Es posible que T y V sean el mismo tipo:
        Stream<String> namesStream = Stream.of("Sinuhe", "Brenn", "Ricardo", "Sebastian", "Luisa");
        Stream<Integer> lengthNameStream = namesStream.map(String::length);

        //flatMap
        /*
        Como mencionamos en el modulo sobre Listeners, flatMap convierte un Stream complejo en un Stream lineal.
        Es decir, flatMap opera sobre un Stream que contenga datos "anidados" como puede ser una Collection o un Array,
        tomando una lambda que retorne otro Stream de los datos anidados y concatenando todos los datos en un solo stream.

        Si nuestro Stream inicial tenia:
        Stream{ List{ "Node.js", "JavaScript"}, List{"Android", "Kotlin"}, List{"JavaSE 8", "Java FP"}}

        Aplicar flatMap retorna:
        Stream{ "Node.js", "JavaScript", "Android", "Kotlin", "JavaSE 8", "Java FP" }
         */
        List<String> nodeCourses = Utils.getListOf("Node.js", "Express.js", "Eventloop");
        List<String> javaCourses = Utils.getListOf("Spring", "Maven", "Gradle", "Funtional");

        Stream<List<String>> coursesListStream = Stream.of(nodeCourses, javaCourses);
        Stream<String> coursesStream = coursesListStream.flatMap(Collection::stream);


//
//
//
//
//
//
        //distinct
        //Genera un Stream de elementos unicos tomando como fuente: Object.equals
        Stream<String> heroesNamesStream = Stream.of("Peter", "Logan", "Luisa", "Clark", "Gwen", "Logan", "Peter");
        Stream<String> uniqueHeroesNamesStream = heroesNamesStream.distinct();// "Peter", "Logan", "Luisa", "Clark", "Gwen"


//
//
//
//
//
        //limit
        //Limita los elementos del Stream al numero indicado.
        Stream<String> justTwoHeroes = uniqueHeroesNamesStream.limit(2);

//
//
//
        //peek
        /*
        Recibe un Consumer para operar sobre cada elemento del Stream pero manteniendo el tipo del Stream
        Es una manera sencilla de hacer una operacion intermedia sobre el Stream sin alterarlo (idealmente)
         */
        Stream<String> choosenTwoHeroes = justTwoHeroes.peek(heroe -> System.out.println("Un heroe ha sido elegido:  " + heroe));

        /*
        Existen otras operaciones como sorted() que alteran el orden del Stream
        o mas especificas como las operaciones mapTo… que convierten el Stream de un tipo a otro

        Es importante entender que estas operaciones solo generan nuevos Stream con cada invocacion.
         */
    }
}
