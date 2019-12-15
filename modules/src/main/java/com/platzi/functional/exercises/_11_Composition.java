package com.platzi.functional.exercises;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _11_Composition {
    //TODO: Crear una funcion o grupo de funciones que puedan calcular: x^2 + y^2 + 2xy
    // usando unicamente los operadores: *, + una vez por funcion.
    // Es decir, Math.pow no esta permitido. y funciones del estilo: a * b + c no estan permitidas.
    public static BiFunction<Integer, Integer, Integer> generateEquation() {
        //Ejemplo:
        Function<Integer, Integer> toNegative = x -> -x;

        //Y aqui tienes un regalo para este ejercicio (tienes que usar esta funcion en el codigo final):
        Function<Integer, Integer> multiplyBy2 = x -> x * 2;

        return null;
    }
}
