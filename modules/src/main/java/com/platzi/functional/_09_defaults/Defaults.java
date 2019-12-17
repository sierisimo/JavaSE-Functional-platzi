package com.platzi.functional._09_defaults;

import java.util.Date;

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

    /**
     * Como nota final, cabe mencionar que comenzando con Java 8 las interfaces pueden tener
     * metodos estaticos:
     */
    @FunctionalInterface
    interface DayConter {
        int countDays(Date startDate, Date endDate);

        /**
         * Esto puede ser de utilidad si queremos mantener una funcion como functional interface pero
         * mantener logica ligada a esa interfaz.
         */
        static boolean isLeapYear(int year) {
            if (year % 4 == 0) {
                if (year % 400 == 0) return true;

                return year % 100 != 0;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        repeatUsingDefaults("Perrito");
        System.out.println("");
        repeatUsingDefaults("dog");
    }
}
