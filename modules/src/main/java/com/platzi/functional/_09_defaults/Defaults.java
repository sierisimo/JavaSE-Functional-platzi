package com.platzi.functional._09_defaults;

public class Defaults {
    @FunctionalInterface
    interface Operational {
        int operate(String s);

        /**
         * Es posible usar metodos default en nuestras interfaces funcionales.
         * Con estos metodos la intencion es definir operaciones pero delegar parte
         * de la logica a otros.
         */
        default void repeat(String s) {
            int times = operate(s);
            while (times-- > 0) {
                System.out.println(s);
            }
        }
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

    /**
     * En este ejemplo, asignamos una funcion a Operational y el metodo repeat usa esta para
     * determinar el numero de veces que debe repetir un texto.
     */
    private static void repeatUsingDefaults(String str) {
        Operational operational = String::length;
        operational.repeat(str);

        System.out.println("<------------->");

        Operational twice = s -> 2;
        twice.repeat(str);
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

    public static void main(String[] args) {
        repeatUsingDefaults("Perrito");
        System.out.println("");
        repeatUsingDefaults("dog");
    }
}
