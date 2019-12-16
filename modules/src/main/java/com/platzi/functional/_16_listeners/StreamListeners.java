package com.platzi.functional._16_listeners;

import com.platzi.functional.util.Utils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class StreamListeners {
    static void listeners() {
        /*
        Mencionamos que los Streams son "auto iterables"
        Entonces… como podemos obtener los datos para procesarlos?

        Es facil, la clase Stream tiene un API funcional que nos permite pasar Suppliers, Consumers,
        Predicates, etc. Lambdas a final de cuentas.
         */
        Stream<String> coursesStream = Stream.of("Java", "Functional");
        coursesStream.forEach(course -> System.out.println("Curso de platzi sobre: " + course));

        /*
        A estas lambdas o funciones que agregamos para procesar los streams, se les conoce como listeners.

        Aunque este nombre/definicion depende del autor, tiene mucho sentido llamarles asi,
        pues el Stream se empezara a iterar una vez que encuentre el set de datos y las operaciones
        a aplicar e ira invocando a cada una de estas operaciones con los valores correspondientes.
         */

        /*
        Hay diferentes tipos de operaciones que aceptan diferentes tipos de listeners, por ejemplo,
        el filtrado de datos:
         */
        AtomicInteger x = new AtomicInteger();
        Stream<Integer> countingStream = Stream.generate(() -> x.getAndIncrement());

        //Filtramos los datos para tener solo los numeros pares producidos por el stream
        countingStream.filter(i -> i % 2 == 0);

        //O elevamos cada numero al cuadrado…
        countingStream.map(i -> i * i);

//
//
//
//

        /*
            Una situacion que se presenta en muchas ocasiones es tener un Stream<List<String>> donde
            tenemos un Stream que emite colecciones.
            Si intentamos operar sobre estre Stream no podremos acceder a los Strings de cada lista,
            solo a las listas como tal… operar de vuelta la lista no es una opcion si estamos haciendo
            cosas en paralelo.
         */
        Stream<List<String>> coursesModules = Stream.of(/*Obentemos los cursos de una DB*/);
        //Nos retorna un Stream de List<String> donde las listas tienen literalmente el elemento "Java"
        //Tal vez nuestra busqueda era sobre cursos que contuvieran la palabra Java como parte del nombre…
        coursesModules.filter(s -> s.contains("Java"));

        /*
        Para poder operar este tipo de streams, usaremos una operacion llamada flatMap

        flatMap toma un Stream<Collecion<T>> y nos retorna un Stream<T>.

        Es decir, flatMap se encarga de combianr todos los elementos de las colecciones de los streams
        en un solo Stream. Para hacer esto, debemos proveer una lambda que emita un stream como resultado

        Si nuestro Stream inicial tenia:
        Stream{ List{ "Node.js", "JavaScript"}, List{"Android", "Kotlin"}, List{"JavaSE 8", "Java FP"}}

        Aplicar flatMap retorna:
        Stream{ "Node.js", "JavaScript", "Android", "Kotlin", "JavaSE 8", "Java FP" }
         */
        List<String> nodeCourses = Utils.getListOf("Node.js", "Express.js", "Eventloop");
        List<String> javaCourses = Utils.getListOf("Spring", "Maven", "Gradle", "Funtional");

        Stream<List<String>> courses = Stream.of(nodeCourses, javaCourses);

        //Sin flatMap
        long jsCourses = courses.filter(course -> course.contains("js")).count();
        System.out.println(jsCourses); // 0

        jsCourses = courses.flatMap(list -> list.stream()) //Tambien Collection::stream es valido
                .filter(course -> course.contains("js"))
                .count(); // 2 (Node.js, Express.js)

//
//
//
//
//
        /*
        Hablamos un poco sobre como crear streams desde una funcion y que estos streams pueden
        emitir datos invocando a esta funcion.

        Sin embargo… que los detiene? Estos streams son infinitos, para detenerlos podemos utilizar `limit`
         */
        Stream<Integer> firstTen = Stream.iterate(0, i -> i + 1);
        firstTen.limit(10)
                .forEach(System.out::println);




        /*
        Tambien es posible que usemos objetos en lugar de lambdas. Recordemos que al final las lambdas
        son funciones y los metodos son funciones. Podemos usar objetos siempre que sus
        metodos cumplan con la necesidad de tener los parametros correctos y devolver el tipo correcto.
         */
        NumericOperator numericOperator = new NumericOperator();
        Stream<Integer> numbers = Stream.iterate(0, numericOperator::operate);
        firstTen.limit(10)
                .forEach(System.out::println);
    }
//
//
//
//
    static class NumericOperator {
        public int operate(int x) {
            return x + 3;
        }
    }
}
