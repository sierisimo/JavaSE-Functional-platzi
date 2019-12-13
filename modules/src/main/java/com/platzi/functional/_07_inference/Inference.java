package com.platzi.functional._07_inference;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Inference {
    private static void typeInference() {
        List<Integer> numbersList = getNumbers();

        /*
         La parte interesante de la inferencia de tipos es que
         Java puede identificar de una funcion que tipo de parametro y retorno tiene y
         con ello darnos acceso a ese tipo en la siguiente funcion.
         */
        getOperable(numbersList)
                .filter(n -> n % 10 == 0)
                .map(n -> {
                    char[] repeater = new char[n];
                    Arrays.fill(repeater, '!');
                    return repeater;
                })
                .map(String::new)
                .forEach(System.out::println);












        /*
        Sin la inferencia el codigo de arriba se veria muchisimo mas largo y menos declarativo

        Aunque estos factores no son malos, en muchas ocasiones restan legibilidad en el codigo.
         */
        getOperable(numbersList)
                .filter((Integer n) -> n % 10 == 0)
                .map((Integer n) -> {
                    char[] repeater = new char[n];
                    Arrays.fill(repeater, '!');
                    return repeater;
                })
                .map((char[] chars) -> new String(chars))
                .forEach((String str) -> System.out.println(str));
    }













    private static List<Integer> getNumbers(){
        List<Integer> numbers = new LinkedList<>();
        numbers.add(1);
        numbers.add(10);
        numbers.add(11);
        numbers.add(100);
        numbers.add(1001);
        numbers.add(1010);
        numbers.add(1100);
        numbers.add(1111);

        return numbers;
    }




















    private static Stream<Integer> getOperable(List<Integer> list) {
        return list.stream();
    }
}
