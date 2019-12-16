package com.platzi.functional._06_reference_operator;

import com.platzi.functional.util.Utils;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class _01_Operator {
    /**
     * Un pequeño ejemplo de como el operador :: funciona
     */
    public static void main(String[] args) {
        List<String> names = new LinkedList<>();
        names.add("Fer");
        names.add("Orly");
        names.add("Sier");
        names.add("Chris");
        names.add("Eryx");

        //Procesamos en una lambda
        names.forEach(s -> System.out.println(s));

//
//
//
//
//

        //Pero sabemos algunas cosas de este punto:
        /*
        1. forEach toma un Consumer<T>
        2. System.out.println(Object obj) es un metodo que toma un elemento y no tiene retorno
        3. Los Consumer son funciones que toman un elemento y no retornan un resultado…

        … Entonces …

        System.out.println puede funcionar como un Consumer!
         */

//
//
//
//
//
//

        //En efecto…
        names.forEach(System.out::println);

//
//
//
//
//
//
///
//

        //Pero no solo eso, la cuestion esta en que toda funcion cuya definicon sea:
        //
        // void nombre(Type type)
        //
        // puede ser usada siempre que el tipo de parametro sea el mismo.
        names.forEach(_01_Operator::coolStuffWithAString);
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

    private static void coolStuffWithAString(String str) {
        System.out.println(
                str.toUpperCase()
                        .trim()
                        .replaceAll("S", "Z")
                        .replaceAll("i", "i1Ii")
        );
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

    /**
     * Hasta ahora hemos usado unicamente funciones estaticas pero existen algunos casos
     * en los que quisieramos usar un metodo de un objeto… porque tal vez el objeto ya
     * tiene un valor para operar, tiene alguna propiedad que puede ayudarnos en la operacion,
     * metodos que puedan ayudar a procesar los datos, etc.
     */
    private void weirdStuff() {
        //Podemos usar directamente el paso de una funcion
        giveMeAFunction(stringParam -> stringParam.length());

        //O usar una referencia a un metodo…
        //Lo interesante aqui es que Java se encarga de entender que `::length` va a
        //corresponder con el metodo ::length de lo que arriba definimos como `stringParam`
        //Es decir…
        //Java hace la conexion entre el objeto en memoria y hace la invocacion de su metodo
        //con el contexto del objeto en memoria…
        giveMeAFunction(String::length);
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

        //Otro ejemplo de ello, es si nosotros tenemos un objeto totalmente ajeno
        //a las operaciones, pero este objeto tiene un metodo cuya definicion coincide
        //con la definicion de la funcion que necesitamos:

        /*
        La clase tiene definido este metodo que recibe un string y retorna un int.


        class HelperOperator {
            public int sayNameAge(String s){…}
            …
        }


        La funcion que tenemos que pasar, justamente, recibe un string y retorna un int…

        giveMeAFunction(Function<String, Integer> function)


        El match perfecto!
         */
        HelperOperator sier = new HelperOperator("Sier");
        giveMeAFunction(sier::sayNameAge);
    }

//
//
//
//
//

    private class HelperOperator {
        private String name;

        public HelperOperator(String name) {
            this.name = name;
        }

        public int sayNameAge(String s) {
            System.out.println("My name is " + name);
            return s.length();
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
//
//

    private static void giveMeAFunction(Function<String, Integer> function) {
        function.apply("Hello");
    }

//
//
//
//
//
//
//
    private static void howItWorks(){
        List<String> names = Utils.getListOf("Fer", "Orly", "Sinuhe", "Ana");



        /*
        No solo es posible referenciar metodos de objetos o metodos estaticos,
        el operador :: en realidad se encarga de crear objetos sin que nosotros los
        tengamos que definir.

        Es por eso que podemos asignar el resultado del operador en variables:
         */
        Consumer<String> printer = _01_Operator::coolStuffWithAString;




        //Esto nos puede reducir las definiciones
        Consumer<String> outPrinter = s -> System.out.println(s);
        //Exactamente lo mismo que la linea de arriba
        Consumer<String> systemPrinter = System.out::println;



        //Todas las invocaciones son valid
        names.forEach(printer);
        names.forEach(outPrinter);
        names.forEach(systemPrinter);
    }
}
