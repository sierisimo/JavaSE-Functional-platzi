package com.platzi.functional._11_composition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Composition {
    /**
     * Dado que las funciones son tipos, podemos almacenarlas como datos en nuestra clase
     */
    private static Function<String, File> createFile = path -> new File(path);

    /**
     * Las funciones pueden ser generadas a partir de una referencia.
     */
    private static Function<File, List<String>> linesFromFile = Composition::getLinesFromFile;

    private static Function<List<String>, List<String>> filter = list -> {
        List<String> resultList = new LinkedList<>();
        list.forEach(s -> addIfNotEmpty(resultList, s));
        return resultList;
    };

    /**
     * Creamos entonces una funcion simple, que toma un string y nos dara las lineas en ese archivo
     * que tengan contenido.
     * <p>
     * Las lineas sin caracteres no seran consideradas.
     */
    static List<String> getLinesWithContentCompose(String pathToFile) {
        //Compose toma como parametro una funcion cuyo valor de retorno sea el valor de entrada
        //de la funcion desde donde invocamos.
        //En este caso:
        // 1. `filter` necesita un `List<String>` para poder operar.
        // 2. `linesFromFile` genera ese `List` pero necesita un `File` para poder retornar la lista
        // 3. `createFile` puede generar un archivo desde un String, que es el parametro de nuestro metodo!
        //
        // La funcion resultante de invocar a `compose` desde `filter` se ejecuta con el metodo `apply`
        return filter
                .compose(linesFromFile)
                .compose(createFile)
                .apply(pathToFile);
    }

    /**
     * Viendo paso a paso cada creacion de funciones que `compose` realiza.
     */
    static List<String> stepsGetLinesWithContentCompose(String pathToFile) {
        Function<String, List<String>> createFileAndGetLines = linesFromFile.compose(createFile);

        Function<String, List<String>> createFileGetLinesFilter = filter.compose(createFileAndGetLines);

        return createFileGetLinesFilter.apply(pathToFile);

        //Tambien podriamos haber ejecutado la primer funcion y ejecutar filter con el resultado:

//        List<String> lines = createFileAndGetLines.apply(pathToFile);
//        return filter.apply(lines);
    }

    static List<String> getLinesWithContent(String pathToFile) {
        return createFile
                .andThen(linesFromFile)
                .andThen(filter)
                .apply(pathToFile);
    }

    static List<String> stepsGetLinesWithContentAndThen(String pathToFile) {
        Function<String, List<String>> createFileAndGetLines = createFile.andThen(linesFromFile);

        Function<String, List<String>> createFileGetLinesAndFilter = createFileAndGetLines.andThen(filter);

        return createFileGetLinesAndFilter.apply(pathToFile);
    }

    public static void main(String[] args) {
        String pathToFile = "/path/to/file.extension";

        System.out.println(
                getLinesWithContentCompose(pathToFile)
        );
    }


    private static List<String> getLinesFromFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return br.lines().collect(Collectors.toList());
        } catch (IOException fileNotFoundEx) {
            fileNotFoundEx.printStackTrace();
        }
        return Collections.emptyList();
    }

    private static void addIfNotEmpty(List<String> list, String s) {
        if (s != null && s.length() > 0 && s.trim().length() > 0) list.add(s);
    }
}
