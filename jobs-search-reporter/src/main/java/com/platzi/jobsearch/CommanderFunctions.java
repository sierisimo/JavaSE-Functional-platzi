package com.platzi.jobsearch;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public interface CommanderFunctions {
    /**
     * Con esta funcion, facilitamos crear una configuracion inicial de JCommander, pidiendo el nombre del
     * programa y un Supplier de tipo T para los argumentos. Asi podemos usar alguna funcion que nos devuelva
     * un objeto que funcione como argumentos de JCommander.
     *
     * @param name              nombre que se mostrara en el CLI
     * @param argumentsSupplier una funcion que devuelva un objeto de argumentos de JCommander
     * @param <T>               Tipo que se usara para los argumentos
     * @return una instancia de {@link JCommander} ya configurada con el nombre y los argumentos.
     */
    static <T> JCommander buildCommanderWithName(String name, Supplier<T> argumentsSupplier) {
        // JCommander permite generar opciones de terminal de cualquier clase, por eso se le agrega un object con el metodo addObject(...)
        // argumentsSupplier.get() es la clase de la cual se generaran los argumentos de JCommander
        // Queda definida una instancia de JCommander. Idealmente con CLIArguments como objeto pasado.
        JCommander jCommander = JCommander.newBuilder().addObject(argumentsSupplier.get()).build();

        jCommander.setProgramName(name);
        return jCommander;
    }

    /**
     * Funcion utilizada para tomar los datos de JCommander, los argumentos esperados y en caso de que algo falle,
     * una funcion con el JCommander que genero el error.
     */
    static Optional<List<Object>> parseArguments(
            JCommander jCommander,
            String[] arguments,
            OnCommandError onCommandError) { // en caso que las validaciones de CLIKeywordValidaror o CLIHelpValidator fallen, se generará un ParameterException
        try {
            jCommander.parse(arguments);
            return Optional.of(jCommander.getObjects());
        } catch (ParameterException exception) { // atrapará el parameter exception y devuelve un optional vacio
            onCommandError.onError(jCommander);
            return Optional.empty();
        }
    }

    @FunctionalInterface
    interface OnCommandError {
        void onError(JCommander jCommander);
    }
}
