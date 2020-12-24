package com.platzi.functional._04_functional;

import java.util.function.Function;
import java.util.function.Predicate;

public class MathFuntions {

    public static void main(String[] args) {
        Function<Integer, Boolean> isOdd = x -> x % 2 == 1;
        System.out.println(isOdd.apply(5));

        //Preciate, return a boolean
        Predicate<Integer> isEven = x -> x % 2 == 0;
        System.out.println(isEven.test(4));

        Predicate<Student> isApproved = studentGrade -> studentGrade.getGrades() >= 3.0;
        Student evelyn = new Student(4.0);
    }

    static class Student{
        private double grades;

        public Student(double grades) {
            this.grades = grades;
        }

        public double getGrades() {
            return grades;
        }

        public void setGrades(double grades) {
            this.grades = grades;
        }
    }
}
