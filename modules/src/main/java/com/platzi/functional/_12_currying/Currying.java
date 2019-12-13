package com.platzi.functional._12_currying;

import java.util.function.Function;

public class Currying {
    @FunctionalInterface
    interface ThreeFunction<F, S, T, R> {
        R apply(F f, S s, T t);
    }

    static void curryingExample() {
        ThreeFunction<Integer, String, Double, String> threeFunction = (i, s, d) -> "";

        threeFunction.apply(1, "", 0.0);

        Function<Integer, Function<String, Function<Double, String>>> curried = curryThree(threeFunction);

        curried.apply(1)
                .apply("")
                .apply(0.0);
    }

    static <F, S, T, R> Function<F, Function<S, Function<T, R>>> curryThree(ThreeFunction<F, S, T, R> threeFunction) {
        return first ->
                second ->
                        third -> threeFunction.apply(first, second, third);
    }
}
