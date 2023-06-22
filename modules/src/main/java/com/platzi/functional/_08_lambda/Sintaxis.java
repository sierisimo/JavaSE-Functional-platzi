package com.platzi.functional._08_lambda;

import com.platzi.functional._06_reference_operator.NombresUtils;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class Sintaxis {
    public static void main(String[] args) {
        List<String> cursos = NombresUtils.getList("Java", "Funcional");
        cursos.forEach(System.out::println);

        usarZero(() -> 2);
        usarPredicado(String::isEmpty);
        usarBifunction((x, y) -> x * y);
        usarBifunction((x, y) -> {
            System.out.println("X: " + x + ", Y: " + y);
            return x - y;
        });
        usarNada(() -> { });

    }

    static void usarZero(ZeroArgumentos zeroArgumentos) {

    }

    static void usarPredicado(Predicate<String> predicado) {

    }

    static void usarBifunction(BiFunction<Integer, Integer, Integer> operacion) {

    }

    static void usarNada(OperarNada operarNada){

    }
    @FunctionalInterface
    interface ZeroArgumentos {
        int get();
    }

    @FunctionalInterface
    interface OperarNada {
        void nada();
    }
}
