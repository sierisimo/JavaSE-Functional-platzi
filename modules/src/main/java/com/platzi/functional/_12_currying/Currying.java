package com.platzi.functional._12_currying;

import java.util.function.Function;

public class Currying {
    /**
     * Empecemos con algo que ya conocemos: una FunctionalInterface que toma 3 tipos de datos
     * F, S, T y retorna un tipo de dato R cuando se manda a llamar su metodo apply.
     * <p>
     * Hasta que no deberia haber nada nuevo.
     */
    @FunctionalInterface
    interface ThreeFunction<F, S, T, R> {
        R apply(F f, S s, T t);
    }

    static void curryingExample() {
        /*
        Tener tres parametros puede hacer que nos confundamos en el orden mientras programamos.
        Y tenemos un detalle, tal vez, por alguna razon, al momento de querer ejecutar esta funcion
        no contamos con los 3 datos.
         */
        ThreeFunction<Integer, String, Double, String> threeFunction = (i, s, d) -> "";
        threeFunction.apply(1, "", 0.0);

        /*
        Que tal si pudieramos simplificar nuestra funcion?

        A reducir la complejidad de una funcion partiendola en subfunciones, se le conoce como currying.

        Currying es una manera de crear funciones mas dinamicas basados en al reduccion de parametros.
         */
        Function<Integer, Function<String, Function<Double, String>>> curried = curryThree(threeFunction);

        curried.apply(1)
                .apply("")
                .apply(0.0);
    }

    /**
     * Esta funcion, se encarga de tomar una funcion compleja de 3 parametros y simplificarla a una funcion de un
     * solo parametro.
     * <p>
     * Desafortunadamente, los generics de java complican un poco su lectura. pero es relativamente sencillo entender
     * lo que pasa:
     * <p>
     * curryThree toma una funcion de tres parametros ThreeFunction<F,S,T,R> y genera una version mas "simple".
     *
     * Esa version "simple" es una funcion que retorna otra funcion que retorna otra funcion.
     *
     * Es decir, tendremos 3 funciones que se pueden encadenar y generar el mismo resultado o ir ejecutando
     * una funcion a la vez conservando un estado anterior.
     *
     * El beneficio es que ahora tenemos una funcion mas simple de un solo parametro y que podemos reutilizar
     * para generar funciones "intermedias".
     */
    static <F, S, T, R> Function<F, Function<S, Function<T, R>>> curryThree(ThreeFunction<F, S, T, R> threeFunction) {
        return first ->
                second ->
                        third -> threeFunction.apply(first, second, third);
    }
}
