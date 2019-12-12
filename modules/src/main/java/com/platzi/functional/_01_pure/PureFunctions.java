package com.platzi.functional._01_pure;

public class PureFunctions {
    /**
     * Aunque hoy dia conocemos a los metodos estaticos como metodos de clase o simplemente metodos estaticos,
     * en realidad un metodo puede ser considerado o visto como una funcion.
     * <p>
     * Basandonos en nuestra definicion de funcion, sabemos que para cada X genera una Y. En este caso,
     * nuestra "x" es en realidad el par (x, y) y nuestra "y" sera el resultado de sumarlas.
     * <p>
     * Habra algun cambio en el resultado si yo ejecuto 90 veces sum(3,5)?
     * <p>
     * La respuesta es: NO.
     * <p>
     * Una funcion pura no depende de estados exteriores (propiedades, objetos, variables
     * externas a su definicion, etc.) ni ve afectado su resultado por agentes externos.
     */
    public static int sum(int x, int y) {
        return x + y;
    }


    /**
     * Imagina que la siguiente clase es parte de un sistema financiero
     */
    static class Person {
        //Nos enfocaremos solo en esta propiedad por ahora…
        private double balance;

        public Person(double balance) {
            this.balance = balance;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }

    /**
     * Teniendo esta funcion, se le puede considerar pura?
     */
    public static boolean hasAvailableFunds(double funds) {
        return funds > 0.0;
    }


    /**
     * La respuesta es: Si!
     * <p>
     * porque la funcion revisa unicamente si un numero es mayor a 0, no importa la referencia de donde vengan
     * los fondos, mientras esos fondos sean menores o iguales a 0, la respuesta siempre sera false.
     * <p>
     * Por otro lado, cuando una persona consigue fondos en su cuenta, la funcion en realidad no ve ese cambio.
     * Para la funcion la respuesta cambiara hasta que le den un nuevo valor a evaluar. La funcion no depende
     * de la presencia de los datos en ningun lugar o de un contexto.
     * <p>
     * Mira el ejemplo abajo:
     */
    public static void main(String[] args) {
        Person sinuhe = new Person(-20.00);
        //False, Sinuhe esta endeudado hasta los dientes
        System.out.println(hasAvailableFunds(sinuhe.getBalance()));

        Person ricardo = new Person(1300.00);
        //True, Ricardo tiene dinero disponible!
        System.out.println(hasAvailableFunds(ricardo.getBalance()));

        //La funcion es evaluada al momento y no depende de que objeto es quien la manda a invocar,
        //es por ello que se considera pura.
    }
}
