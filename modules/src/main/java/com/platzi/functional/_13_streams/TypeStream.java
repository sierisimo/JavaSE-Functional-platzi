package com.platzi.functional._13_streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TypeStream {
    public static void main(String[] args) {
        IntStream infiniteStream = IntStream.iterate(0, x -> x + 1);
        List<Integer> numbersList = infiniteStream.limit(1000)
                .filter(x -> x % 2 == 0)
                .boxed()
                .collect(Collectors.toList());


    }
}
