package com.platzi.functional._06_reference_operator;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class NombresUtils {
    public static void main(String[] args) {
        List<String> profesores = getList("Nicolas", "Juan", "Zulema");
        profesores.forEach(System.out::println);
    }
    public static <T> List<T> getList(T... elements){
        return Arrays.asList(elements);
    }
}
