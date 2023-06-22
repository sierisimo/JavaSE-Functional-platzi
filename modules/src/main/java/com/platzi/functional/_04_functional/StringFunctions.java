package com.platzi.functional._04_functional;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

import static java.util.List.of;

public class StringFunctions {
    public static void main(String[] args) {
        UnaryOperator<String> quote = text -> "\"" + text + "\"";
        System.out.println(quote.apply("Nestor"));

        BiFunction<Integer, Integer, Integer> multiplicacion = (x, y) -> x * y;

        BiFunction<String, Integer, String> leftPad =
                (text, number) -> String.format("%" + number + "s", text);
        System.out.println(leftPad.apply("Java",10));

    }
}
