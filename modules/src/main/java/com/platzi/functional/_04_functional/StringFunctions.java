package com.platzi.functional._04_functional;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

public class StringFunctions {

    public static void main(String[] args) {
        UnaryOperator<String> addMark = text -> text + "!";
        System.out.println(addMark.apply("Hola Mundo"));

        BiFunction<String, Integer, String> leftPad = (text, number) -> String.format("%" + number + "s", text);
        System.out.println(leftPad.apply("Java", 10));

        List<BiFunction<String, Integer, String>> formatText;

    }
}
