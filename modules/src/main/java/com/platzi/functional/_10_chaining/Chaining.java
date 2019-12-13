package com.platzi.functional._10_chaining;

import com.platzi.functional._10_chaining.builder.BuilderNoChaining;
import com.platzi.functional._10_chaining.builder.BuilderWithChaining;
import com.platzi.functional._10_chaining.data.Account;

import java.util.function.Function;

public class Chaining {

    /**
     * Aunque el chaining no es algo exclusivo o a conocer de la programacion funcional,
     * vale la pena mencionarlo, puesto que puede ser de ayuda al usar funciones de java o
     * en temas futuros…
     * <p>
     * Chaining es la estrategia de retornar siempre un objeto, tal que puedas invocar
     * metodos con cada invocacion.
     */
    static void examplesOfChaining() {
        //Cuando no usas chaining tu codigo se ve aburrido y repetitivo
        BuilderNoChaining builderNoChaining = new BuilderNoChaining("");
        builderNoChaining.withBalance(100.00);
        builderNoChaining.withFirstName("Sinuhe");
        builderNoChaining.withLastName("Jaime");
        builderNoChaining.withPhone("+52155555555");

        Account sinuheAccount = builderNoChaining.buildAccount();

        //Pero con un buen chaining puedes hacer las cosas mas simples.
        Account anotherAccount =
                new BuilderWithChaining("1")
                        .withBalance(100.00)
                        .withFirstName("Sinuhe")
                        .withLastName("Jaime")
                        .withPhone("+52155555555")
                        .buildAccount();

        //Es relativamente comun verlo en Strings:
        char[] saludo = "Bye Platzi!".replaceAll("Bye", "Hola")
                .toLowerCase()
                .toCharArray();

        //O como en el primer ejemplo, con builders…
        StringBuilder builderJava = new StringBuilder()
                .append("E").append("s").append("t").append("o").append("y")
                .append(" ")
                .append("A")
                .append("p")
                .append("r")
                .append("e")
                .append("n")
                .append("d")
                .append("i")
                .append("e")
                .append("n")
                .append("d")
                .append("o")
                .append(" Java ")
                .append(8);

        System.out.println(builderJava.toString());


        //La reelevancia en este curso, de esta estrategia, viene al crear funciones mas complejas
        //o que se crean a partir de otras funciones. Posteriormente veremos que es de mucha utilidad
        //con ciertos tipos de datos.

        //Por ejemplo, creamos una funcion que reemplace las "f" por asteriscos
        Function<String, String> replacer = s -> s.replaceAll("f", "*");

        
        Function<Function<String, String>, Function<String, String>> replacerPlus =
                f -> f.andThen(s -> s.replaceAll("a", "/"));

        System.out.println(
                replacerPlus
                        .apply(replacer)
                        .andThen(s -> s.replaceFirst("Ho", "Mo"))
                        .apply("Hablando de chaining, esto Hola, fucho")
        );
    }
}
