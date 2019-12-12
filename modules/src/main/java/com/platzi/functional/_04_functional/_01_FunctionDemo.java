package com.platzi.functional._04_functional;

import com.platzi.functional.util.Utils;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class _01_FunctionDemo {


    /**
     * La interface Function trabaja sobre generics, siendo el primero el tipo de entrada
     * y el segundo el resultado de ejecutar esa funcion.
     */
    private static <TYPE, RESULT> void metodoDeEjemplo() {
        //Podemos crear instancias bajo demanda…
        Function<TYPE, RESULT> someFun = new Function<TYPE, RESULT>() {
            /**
             * El unico metodo que hay que implementar es el metodo apply.
             * Este metodo tomara el TYPE y debera generar un RESULT
             */
            @Override
            public RESULT apply(TYPE t) {
                return null;
            }
        };

        //Y luego invocar el metodo apply con parametros del tipo correspondiente…
        someFun.apply(null);
    }


    /**
     * Veamos un ejemplo simple… una funcion que nos devuelve si un numero es par
     */
    private static void functionExample() {
        Function<Integer, Boolean> isEven = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer % 2 == 0;
            }
        };

        isEven.apply(2); // True
        isEven.apply(200); // True
        isEven.apply(18); // True

        isEven.apply(25); // False
        isEven.apply(31); // False
        isEven.apply(191); // False
    }
//


    //
//    El proceso es muy similar a tener definidos metodos o instancias anonimas como al ordenar numeros.
    private static void sortNumbers(List<Integer> numbers) {
        //Aqui generamos una instancia anonima de comparator
        numbers.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
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
//

    /**
     * Tambien podemos crear clases mas complejas que implementen esta interface…
     */
    class FunctionBy2 implements Function<Integer, Integer> {
        @Override
        public Integer apply(Integer x) {
            return x * 2;
        }
    }

    /**
     * Y despues usar esas instancias…
     * hasta ahora nada nuevo o realmente funcional…
     * en realidad esto es solo lo que ya conocemos de usar interfaces y OOP.
     */
    private static void useBy2(FunctionBy2 by2) {
        int y = by2.apply(5);
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
     * Lo interesante viene cuando entendemos que una funcion es un tipo…
     * (mantengamonos con funciones y enteros por ahora)
     */
    private static void functionsAreTypes() {
        /*
        Si una funcion es un tipo, tambien podemos usarla con generics.
        Por ejemplo, devolver una funcion como resultado de ejecutar una funcion…

        Es algo parecido a obtener una sublista de una lista:
         ->> List<X> sub = list.sublist(0, 5);
        */
        Function<Integer, Function<Integer, Integer>> multiply =
                new Function<Integer, Function<Integer, Integer>>() {
                    @Override
                    public Function<Integer, Integer> apply(Integer x) {
                        //Creamos una nueva funcion y la retornamos
                        return new Function<Integer, Integer>() {
                            @Override
                            public Integer apply(Integer y) {
                                return x * y;
                            }
                        };
                    }
                };

        //Usando nuestra nueva funcion…
        multiply.apply(5).apply(4); // Resultado: 20
        multiply.apply(15).apply(3); // Resultado: 45
        multiply.apply(2).apply(4); // Resultado: 8
        multiply.apply(9).apply(7); // Resultado: 63

        //O podemos crear funciones derivados de la primer funcion:
        Function<Integer, Integer> multiplyBy3 = multiply.apply(3);

        multiplyBy3.apply(2); //6
        multiplyBy3.apply(13); //39
        multiplyBy3.apply(9); //27
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
     * Pero la sintaxis de crear instancias de interfaces es demasiado extensa y complicada de leer…
     * usemos la nueva sintaxis de java 8 para funciones
     * (explicaremos todas las sintaxis y las diferencias mas adelante)
     */
    private static void syntaxFixing() {

        Function<Integer, Integer> multiplyBy5 = x -> x * 5;

        //Con esto nos ahorramos crear una clase que implemente la interfaz
        //Crear funciones se hace mas simple…
        multiplyBy5.apply(10); //Resulta en: 50

        //Incluso para funciones que retornan funciones (high order functions recuerdas?)…
        Function<Integer, Function<Integer, Integer>> multiplyXByY =
                x ->
                        y -> x * y;

        //Sabemos que al mandar a llamar al metodo apply obtendremos una nueva funcion…
        Function<Integer, Integer> multiply2ByY = multiplyXByY.apply(2);

        multiply2ByY.apply(7); // Nos resultara en 14

        //O podemos seguir llamando al resultado directamente
        multiplyXByY.apply(9).apply(8); //72
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
//
//
//

    /**
     * Lo bueno empieza no cuando las definimos…
     * empieza cuando las empezamos a pasar como parametros y recibir como resultados…
     */
    private static void funWithFuns() {
        List<Integer> myNumbers = Utils.getListOf(1, 2, 3, 4, 5, 6);

        //Funcion que eleva un numero al cuadrado
        Function<Integer, Integer> square = x -> x * x;

        //Funcion que eleva un numero al cubo
        Function<Integer, Integer> cube = x -> x * x * x;

        //Funcion que convierte un entero en negativo
        Function<Integer, Integer> toNegative = x -> -1 * x;

        applyMathToList(myNumbers, square); // [1, 4, 9, 16, 25, 36]
        applyMathToList(myNumbers, cube); // [1, 8, 27, 64, 125, 216]
        applyMathToList(myNumbers, toNegative); // [-1, -2, -3, -4, -5, -6]
    }

//
//
//

    /**
     * Podemos decir que algunos metodos son funciones, en este caso, este metodo es una
     * funcion de orden mayor que toma como parametro otra funcion y la usa para operar
     * sobre la lista.
     */
    private static List<Integer> applyMathToList(
            List<Integer> items,
            Function<Integer, Integer> operation
    ) {
        List<Integer> resultItems = new LinkedList<>();
        for (Integer x : items) {
            resultItems.add(operation.apply(x));
        }
        return resultItems;
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

    /**
     * Incluso podriamos hacer cosas mas interesantes como aplicar muchas funciones sobre un dato
     */
    private static String transformText(String text) {
        List<Function<String, String>> transformations = new LinkedList<>();

        transformations.add(s -> s.toUpperCase());
        transformations.add(s -> s.replaceAll("SI", "TI"));
        transformations.add(s -> s.replaceAll("RO", "YO"));
        transformations.add(s -> s.replaceAll("O", "OoO"));

        String result = text;
        for (Function<String, String> function : transformations) {
            result = function.apply(result);
        }
        return result;
    }


    private static void runTransform() {
        System.out.println(transformText("Hello")); //HELLOoO
        System.out.println(transformText("World")); //WOoORLD
        System.out.println(transformText("Claro que si roncas")); //CLAYOoO QUE TI YOoONCAS
    }

    public static void main(String[] args) {
        runTransform();
    }
}
