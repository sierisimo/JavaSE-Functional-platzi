package com.platzi.functional._13_streams;

import java.util.stream.IntStream;

public class TypeStream {
    public static void main(String[] args) {
        IntStream infiniteStream = IntStream.iterate(0, x -> x + 1);
        infiniteStream.limit(1000)
                .parallel()
                .filter(x -> x % 2 == 0)
                .forEach(System.out::println);
    }
}
