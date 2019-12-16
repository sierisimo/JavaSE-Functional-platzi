package com.platzi.functional._14_optionals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Optionals {
    /*
     * La clase Optional nos ayuda en java 8 a resolver un problema comun en Java: null
     * Con Optional tendremos la posibilidad de operar sobre una clase que nos protege del infame NPE
     *
     * La idea de Optional es que previo a hacer una operacion, se haga una validacion dentro de
     * el Optional para evitar problemas.
     */
    static Optional<String> optionals() {
        //La clase optional nos ofrece diferentes maneras de crear un optional segun los datos que tengamos




        //La primera de ellas es crear un Optional de un dato que SI tenemos:
        Optional<String> optional = Optional.of("Java 8");



        //Si no estamos seguros del valor que pondremos en el Optional, podemos usar ofNullable:
        optional = Optional.ofNullable(uknownResult());




        //Y si lo que queremos es evitar devolver un null pero no tenemos un valor para regresar,
        //podemos usar simplemente:
        return Optional.empty();
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
//
//
//
//

    static void obtenerUnDato() {
        Optional<String> optional = Optional.ofNullable(uknownResult());

        //Una vez que tenemos un optional, obtener el dato puede ser tan simple como:
        String dato = optional.get();


        //Sin embargo, eso nos provoca el mismo problema que tratamos de solucionar,
        //pues si el dato es null, `get` nos devolera un null.



        //Podemos entonces, checar la presencia de dato:
        if (optional.isPresent()) {
            //Pero esto es muy similar a checar si el dato es null.
            dato = optional.get();
        }



        //Hagamoslo de manera mas funcional:
        dato = optional.orElse("");


        //Y en caso de que nuestro dato sea muy complejo:
        dato = optional.orElseGet(Optionals::complexFunction);


        //O con una lambda:
        dato = optional.orElseGet(() -> /*Cosas magicas para generar el dato*/ "");



        //Y esta es la parte donde entendemos que optional tiene mas poderes que validar contra un null.



        //No solo eso, Optional nos permite operar el dato en caso de que este presente:
        optional.ifPresent(System.out::println);
        optional.ifPresent(s -> someComplexOperation(s));



        //Incluso hacer operaciones para generar nuevos optionals segun sea necesario:
        Optional<String> subOptional = optional.filter(String::isEmpty);



        //O transformar el dato:
        Optional<Integer> integerOptional = optional.map(s -> s.length() * 2);



        //Es importante mencionar que Optional no ejecutara ninguna de estas funciones
        //en casos donde el dato no existe (null, empty() ). Asi que es una manera segura de
        //escribir codigo sin preocuparnos por la presencia del dato.

        //En casos muy especificos donde quisieramos generar un Exception cuando haya una ausencia
        //de datos, podemos usar el metodo `orElseThrow`:
        integerOptional.orElseThrow(() -> new DatoNecesarioException());

        //Optional nos da un acercamiento hacia un concepto que en FP se conoce como Monad
    }


    /**
     * Ejemplo al codigo antes de Optional
     */
    static String antesDeOptional(List<String> names) {
        //Antes de optional, era comun tener un pequeño `if` validando la presencia de valor
        // en los argumentos de nuestros metodos
        if (names == null) {

            //Y una mala practica era, en error o ausencia de datos, retornar un null.
            //Esto es una mala practica porque es una manera de evadir operaciones. Forzando que tambien
            //el codigo que invoco nuestra funcion tenga que validar si el resultado es null.
            return null;
        }

        return Arrays.toString(names.toArray());
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
//
//
//
//

    /**
     * Con la clase optional le damos una mayor seguridad a quien ejecuta nuestro codigo,
     * pues le ahorramos operaciones de validacion y puede decidir que hacer con los datos
     * de una manera mas directa.
     */
    static Optional<String> conOptional(List<String> names) {
        if (names == null || names.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(Arrays.toString(names.toArray()));
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

    /**
     * Funcion usando ambos casos:
     */
    static void outside() {
        //Invocando a un metodo que no sabemos que retorna:
        String directResult = antesDeOptional(null);
        if (directResult != null) {
            directResult = directResult.replace("Sierisimo", "Sinuhe");
        }

        //Con optional:
        Optional<String> optionalResult = conOptional(null);

        directResult = optionalResult
                .map(s -> s.replace("Sierisimo", "Sinuhe"))
                .orElse("Sinuhe");

        //Incluso podriamos hacer chaining directo:
        conOptional(null).filter(s -> !s.isEmpty())
                .map(s -> s.replace("@Sierisimo", "Sinuhe"))
                .orElse("Sinuhe");

        //Como vemos, optional nos facilita operar sobre datos
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
    static String uknownResult() {
        return null;
    }

    static String complexFunction() {
        return "Complex Result";
    }

    static void someComplexOperation(String s) {

    }

    static class DatoNecesarioException extends IllegalArgumentException {

    }
}
