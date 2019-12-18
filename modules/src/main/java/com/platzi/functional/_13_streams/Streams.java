package com.platzi.functional._13_streams;

import com.platzi.functional._06_reference_operator.NombresUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {
    public static void main(String[] args) {
        List<String> courseList = NombresUtils.getList(
                "Java",
                "FrontEnd",
                "Backend",
                "FullStack");
        for (String course : courseList) {
            String newCourseName = course.toLowerCase().replace("!", "!!");
            System.out.println("Platzi: " + newCourseName);
        }

        Stream<String> coursesStream = Stream.of("Java",
                "FrontEnd",
                "Backend",
                "FullStack");

//        Stream<Integer> courseLengthStream = coursesStream.map(course -> course.length());

//        Optional<Integer> longest = courseLengthStream.max((x, y) -> y - x);

        Stream<String> emphasisCourses = coursesStream.map(course -> course + "!");
        Stream<String> justJavaCourses = emphasisCourses.filter(course -> course.contains("Java"));
        justJavaCourses.forEach(System.out::println);

        Stream<String> coursesStream2 = courseList.stream();

        addOperator(
                coursesStream2.map(course -> course + "!!")
                .filter(course -> course.contains("Java"))
        ).forEach(System.out::println);
    }

    static <T> Stream<T> addOperator(Stream<T> stream){
        return stream.peek(data -> System.out.println("Dato: " + data));
    }
}
