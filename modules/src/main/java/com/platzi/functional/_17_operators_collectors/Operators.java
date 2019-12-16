package com.platzi.functional._17_operators_collectors;

import com.platzi.functional.util.Utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Operators {
    static void operadores() {
        /*
        Hasta ahora hemos usado lambdas indiscriminadamente en nuestros streams sin revisar o validar
        el estado del stream. Es decir, si ejecutas algunos ejemplos anteriores puede que te encuentres con
        algunas excepciones no explicadas. Este es el tema donde explicaremos que diferencia hay entre usar

        forEach vs map por ejemplo.
         */

        /*
        En nuestros streams, generalmente nos encontramos con elementos y realizamos operaciones sobre ellos.
         */
        Stream<Integer> numbers = Utils.getListOf(1, 2, 3, 4).stream();
        numbers.map(i -> i * 2);

        /*
        Sin embargo, ciertas operaciones nos retornan un resultado, por ejemplo, map genera un nuevo
        Stream<V> partiendo de un Stream<T>. Es decir, cada que realizamos una operacion `map` en un stream
        lo que hace en realidad es generar un nuevo Stream, donde a cada elemento se le ira aplicando
        la operacion que nosotros definimos.

        Esto puede ser representado:
         */
        Stream<Integer> numbersV2 = Utils.getListOf(1, 2, 3, 4).stream();
        Stream<Integer> numbresBy2 = numbersV2.map(i -> i * 2);

        /*
        Mas importante aun es entender que una vez aplicado un operador sobre un stream este stream no puede ser
        usado de nuevo:
         */
        Stream<Integer> squares = numbersV2.map(i -> i * i); //Genera un IllegalStateException.
        //Lo cual representa una pequeña limitante sobre el uso de streams.

        /*
        Hay metodos que modifican los datos de nuestro stream y metodos que nos dejan obtener un resultado de
        la iteracion de los elementos del stream. En ambos casos se les conoce como Operations

        Es importante identificar que muchos de estos retornan un nuevo Stream. Tras realizar una operacion
        sobre un stream, el dato que existe en el stream puede cambiar, aqui un ejemplo:
         */
        Stream<String> courses = Stream.of(
                "Java:Introductorio",
                "Python:Introductorio",
                "Machine Learning:Avanzado",
                "JavaScript:Introductorio",
                "Node.js:Intermedio",
                "Android:Intermedio",
                "iOS:Intermedio"
        );

        Stream<String> introductoryCourses = courses.filter(course -> course.contains("Introductorio"));

        Stream<String[]> partsNames = introductoryCourses.map(course -> course.split(":"));

        Stream<String[]> partsWithData = partsNames.filter(parts -> parts.length > 1);

        Stream<String> justNamesStream = partsWithData.map(courseData -> courseData[0]);

        Stream<String> justActualNamesPresent = justNamesStream.filter(name -> !name.isEmpty());

        /*
        O en una version con chaining:

        Stream<String> justNamesStream = courses.filter(c -> c.contains("Introductorio"))
                .map(c -> c.split(":"))
                .filter(parts -> parts.length > 1)
                .map(courseData -> courseData[0])
                .map(c -> c[0])
                .filter(name -> !name.isEmpty());
         */

        /*
        Existen operaciones que nos permite obtener un resultado final despues de operar sobre los elementos de un stream.

        Cuando obtenemos un resultado final de la iteracion de un stream, se dice que se invoco una Operacion Final.

        Es importante entender que aunque tengamos multiples operaciones sobre un Stream, el Stream no sera iterado
        hasta no agregar una operacion final. Esto es benefico pues podemos ir pasando el Stream por diferentes metodos/funciones
        hasta que sea necesario obtener el valor resultante.
         */
        Stream<List<String>> coursesStream = getCourses();
        Stream<String> courseDataStream = flatMapCourses(coursesStream);
        Stream<String[]> partsStream = splitInformation(courseDataStream);
        Stream<String[]> filteredPartsStream = filterAdvanceCourses(partsStream);
        Stream<String> advanceCourseNamesStream = getNamesStream(filteredPartsStream);

        /*
        hasta este punto, no se ha procesado ningun stream a pesar de las llamadas a metodos.
        Esto es porque no se ha agregado una operacion final que desencadene la iteracion en el
        ningun stream.

        Agregar una operacion final es tan sencillo como agregar una operacion no final:
         */
        //long totalAdvanceCourses = advanceCourseNamesStream.count();

        /*
          Es hasta este punto que los datos se empiezan a iterar para obtener un resultado.
          Sabemos que `count()` es una operacion final porque no retorna un Stream, retorna un
          resultado de aplicar la operacion correspondiente.

          En muchas ocasiones lo mas comun es encontrarte con una operacion de recoleccion de datos
          utilizando una de las implementaciones de Collector.

          Esta es la manera ideal de convertir un Stream a una coleccion (Set, Map, List, Collection)
          o convertir todos los datos de un Stream a un tipo en concreto (por ejemplo, concatenar todos los elementos)
         */
        List<String> advanceCourseNamesList = advanceCourseNamesStream.collect(Collectors.toList());
        
//
//
//
//

        /*
        El ejemplo anterior representa algo sucediendo entre multiples partes de un proyecto,
        una que procese el dato de una peticion web, otro que lo almacene en una base de datos
        otro que haga conversion de datos… etc.
         */
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
//
//
//
    public static Stream<List<String>> getCourses() {
        List<String> nodeCourses = Utils.getListOf("Node.js:Intermedio", "Express.js:Intermedio", "Eventloop:Avanzado");
        List<String> javaCourses = Utils.getListOf("Spring:Introductorio", "Maven:Intermedio", "Gradle:Avanzado", "Funtional:Introductorio");

        return Stream.of(nodeCourses, javaCourses);
    }

    static Stream<String> flatMapCourses(Stream<List<String>> courses) {
        return courses.flatMap(Collection::stream);
    }

    static Stream<String[]> splitInformation(Stream<String> coursesData) {
        return coursesData.map(courseData -> courseData.split(":"));
    }

    static Stream<String[]> filterAdvanceCourses(Stream<String[]> courses) {
        return courses.filter(data -> data.length > 1)
                .filter(data -> data[1] == "Avanzado");
    }

    static Stream<String> getNamesStream(Stream<String[]> coursesData) {
        return coursesData.map(courseData -> courseData[0]);
    }
}
