package com.platzi.functional._19_final_ops;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.platzi.functional._17_operators_collectors.Operators.getCourses;

public class FinalOperations {
    static void operaciones() {
        /*
        Las Operaciones finales son, como se menciono antes, las operaciones que generan un valor
        final despues de iterar los elementos del stream.

        Dichas operaciones son:
            - anyMatch()
            - allMatch()
            - noneMatch()
            - collect()
            - count()
            - findAny()
            - findFirst()
            - forEach()
            - min()
            - max()
            - reduce()
            - toArray()
         */

        //anyMatch
        //Nos indica si un stream contiene un elemento segun el Predicate que le pasemos:
        Stream<Integer> numbersStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 11);
        boolean biggerThanTen = numbersStream.anyMatch(i -> i > 10); //true porque tenemos el 11

        //allMatch
        //Nos indica si todos los elementos de un Stream cumplen con un cierto Predicate:
        Stream<Integer> agesStream = Stream.of(19, 21, 35, 45, 12);
        boolean allLegalDrinkingAge = agesStream.allMatch(age -> age > 18); //false, tenemos un menor

        //noneMatch
        //Nos indica si todos los elementos de un Stream NO CUMPLEN un cierto Predicate:
        Stream<Integer> oddNumbers = Stream.of(1, 3, 5, 7, 9, 11);
        boolean allAreOdd = oddNumbers.noneMatch(i -> i % 2 == 0);

        //collect
        //Recibe un Collector para juntar todos los elementos del Stream en un cierto tipo:
        Stream<String> studentsStream = Stream.of("Tu", "Yo");
        List<String> studentsList = studentsStream.collect(Collectors.toList());

        //count
        //Nos indica cuantos elementos tiene un Stream
        Stream<Integer> yearsStream = Stream.of(1990, 1991, 1994, 2000, 2010, 2019, 2020);
        long yearsCount = yearsStream.count(); //7, solo nos dice cuantos datos tuvo el stream.


        //findAny
        /*
        Nos retorna un Optional con un elemento, cualquiera del Stream, o Optional.empty()
        si no hay elementos en el Stream. Esta operacion puede devolver cualquier elemento
        del Stream.
         */
        Stream<List<String>> coursesStream = getCourses();
        Optional<List<String>> coursesOptional = coursesStream.findAny();

        //findFirst
        //Retorna un Optional con el primer elemento del Stream o Optional.empty() si no hay elementos en el Stream
        Stream<List<String>> availableCourses = getCourses();
        Optional<List<String>> firstCoursesOptional = coursesStream.findFirst();


        //forEach
        //Itera cada elemento del Stream pasandolo al Consumer que se da como parametro:
        Stream<List<String>> courses = getCourses();
        courses.forEach(courseList -> System.out.println("Cursos disponibles: " + courseList));

        //min
        //Nos retorna un Optioanl con el elemento con valor minimo del Stream usando un Comparator para encontrarlo
        // o Optional.empty() si el Stream no tenia elementos
        Stream<Long> bigNumbers = Stream.of(100L, 200L, 1000L, 5L);
        Optional<Long> minimumOptional = bigNumbers.min((numberX, numberY) -> (int) Math.min(numberX, numberY));

        //max
        //Escencialmente lo mismo que Stream.min, solo que retornando un Optional con el valor MAXIMO:
        Stream<Long> bigNumbersAgain = Stream.of(100L, 200L, 1000L, 5L);
        Optional<Long> maximumOptional = bigNumbers.min((numberX, numberY) -> (int) Math.max(numberX, numberY));

        //reduce
        /*
        Existe en tres formas:
            reduce(valorInicial, BinaryOperator)
            reduce(BinaryAccumulator)
            reduce(valorInicial, BinaryFunction, BinaryOperator)

        La diferencia entre los 3 tipos de invocacion:

        - reduce(BinaryAccumulator)
            retorna un Optional del mismo tipo que el Stream, con un solo valor resultante de aplicar
            el BinaryAccumulator sobre cada elemento O Optional.empty() si el stream estaba vacio.
            Puede generar un NullPointerException en casos donde el resultado de BinaryAccumulator sea null

        - reduce(valorInicial, BinaryOperator)
            retorna un valor del mismo tipo que el Stream, despues de aplicar BinaryOperator sobre cada
            elemento del Stream, en caso de un Stream vacio, el valorInicial es retornado.

        Y el caso mas interesante…

        - reduce(valorInicial, BinaryFunction<V, T, V>, BinaryOperator<V>)
            Genera un valor de tipo V, despues de aplicar BinaryFunction sobre cada elemento de tipo T en el Stream
            y obtener un resultado V. Esta version de reduce usa el BinaryFunction como map + reduce. Es decir
            por cada elemento en el Stream, se genera un valor V basado en el valorInical y el resultado anterior de
            la BinaryFunction. BinaryOperator se utiliza en streams paralelos (stream.parallel()) para determinar el
            valor que se debe mantener en cada iteracion.

         */

        //reduce(BinaryAccumulator)
        Stream<String> aLongStoryStream = Stream.of("Cuando", "despertó,", "el", "dinosaurio", "todavía", "estaba", "allí.");
        Optional<String> longStoryOptional = aLongStoryStream.reduce((previousStory, nextPart) -> previousStory + " " + nextPart);
        longStoryOptional.ifPresent(System.out::println); //"Cuando despertó, el dinosaurio todavía estaba allí."

        //reduce(valorInicial, BinaryOperator)
        Stream<Integer> firstTenNumbersStream = Stream.iterate(0, i -> i + 1).limit(10);
        int sumOfFirstTen = firstTenNumbersStream.reduce(0, Integer::sum); //45 -> 0 + 1 + … + 9

        //reduce(valorInicial, BinaryFunction<V, T, V> acumulador, BinaryOperator<V> combinador)
        Stream<String> aLongStoryStreamAgain = Stream.of("Cuando", "despertó,", "el", "dinosaurio", "todavía", "estaba", "allí.");
        int charCount = aLongStoryStreamAgain.reduce(0, (count, word) -> count + word.length(), Integer::sum);
    }
}
