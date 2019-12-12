package com.platzi.functional._05_sam;

import java.util.List;
import java.util.Scanner;

public class SingleAbstractMethod {
    /**
     * Se le considera SAM a una interface cuando tiene un unico metodo, sin valor definido.
     * Por ejemplo:
     */
    interface SAMInterface {
        int doSomething();
    }

    /**
     * Si la interface tiene mas de un metodo sin implementacion, no es considerada una SAM
     */
    interface NotASAMInterface {
        int doFoo();

        void doBar();
    }

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

    /**
     * Este concepto es importante empezando en Java 8 por varias razones, la principal
     * es que el compilador nos permitira usar una anotacion disponible UNICAMENTE para
     * SAMs: @FunctionalInterface
     */
    @FunctionalInterface
    interface AnotherSAM {
        String getText();
    }

    /**
     * De nuevo, esto solo funciona en interfaces de tipo SAM
     */
// @FunctionalInterface
    interface NotAValidSAM {
        String getText();

        String getSubText();
    }

    //
//
//
//
//
//
//
//
//
//
//
//
//
    @FunctionalInterface
    interface MySAMInterfaceIsAlsoAFunction {
        String someMethod(int x);
    }


    /**
     * Con las interfaces anotadas, podemos usar la forma de lambdas/funciones para definir
     * el comportamiento
     */
    private static void fooSAM() {
        MySAMInterfaceIsAlsoAFunction myFunction = x -> "Result: " + x;
    }

//
//
//
//
//
//
//
//
//
//
//
//
//

    /**
     * Incluso en interfaces con muchos parametros
     */
    @FunctionalInterface
    interface OverComplicatedSAM {
        int someWeirdNameForAMethod(String s, int x, Scanner sc, List<Double> values);
    }

    public static void somethingCalling() {
        OverComplicatedSAM stillAFunction = (s, x, sc, list) -> 0;
    }
}
