package com.platzi.functional._05_sam;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;

public class AgeUtils {

    public static void main(String[] args) {

        Function<Integer, String> addZeros = x -> x < 10 ? "0" + x : String.valueOf(x);

        TriFunction<Integer, Integer, Integer, LocalDate> toDate =
                (days, months, year) -> LocalDate.parse(year + "-" + addZeros.apply(months) + "-" + addZeros.apply(days));

        TriFunction<Integer, Integer, Integer, Integer> calculateAge =
                (days, months, years) -> Period.between(toDate.apply(days, months, years), LocalDate.now()).getYears();

        System.out.println(calculateAge.apply(3, 3, 1985));

    }


    @FunctionalInterface
    interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }

}
