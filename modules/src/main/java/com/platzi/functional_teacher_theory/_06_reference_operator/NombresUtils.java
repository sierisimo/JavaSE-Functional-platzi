package com.platzi.functional_teacher_theory._06_reference_operator;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class NombresUtils {
    public static void main(String[] args) {
        List<String> profesores = getList("Nicolas", "Juan", "Zulema");

        Consumer<String> printer = System.out::println;
        profesores.forEach(printer);
        System.out.println("//////////");
        profesores.forEach(System.out::println);
    }

    @SafeVarargs
    public static <T> List<T> getList(T... elements) {
        return Arrays.asList(elements);
    }
}
