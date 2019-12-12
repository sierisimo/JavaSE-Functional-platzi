package com.platzi.functional._02_sideeffects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SideEffects {

    /**
     * Funcion impura, el resultado de ejecutarla puede ser observado desde fuera
     * del codigo. Por ejemplo en una consola.
     */
    static void helloWorld() {
        System.out.println("Hello World!");
    }








    
    /**
     * Funcion impura, depende de la existencia y el estado de un archivo,
     * eso provoca que sea no determinista
     * Que sucede si esta funcion se ejecuta en mi computadora y en tu computadora?
     * - Podemos determinar la salida en ambos casos?
     * - Como nos aseguramos que nadie mas este modificando el archivo?
     */
    static boolean containsMexico(File file) {
        try (BufferedReader bfReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bfReader.readLine()) != null) {
                if (line.contains("Mexico")) {
                    return true;
                }
            }
        } catch (IOException ignored) {
            return false;
        }

        return false;
    }













    /**
     * Funcion impura. Aunque el codigo no esta implementado, con entender lo que hace
     * sabemos que es no determinista y que no podemos garantizar los resultados para
     * un cierto parametro
     */
    static String getLastNameForGivenName(String name) {
        //Obtener una conexion a la Base de datos
        //Ejecutar un query en la base de datos
        //Revisar los resultados del query…
        //retornar el valor del lastName o un valor por default en caso de ausencia
        //...
        return "";
    }
}
