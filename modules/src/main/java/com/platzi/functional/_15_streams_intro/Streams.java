package com.platzi.functional._15_streams_intro;

import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) {

        Stream<String> courses = Stream.of("Java", "C++", "Python");

        courses.map(course -> course + "!!")
                .filter(containsJava -> containsJava.contains("Java"))
                .forEach(System.out::println);

    }
}
