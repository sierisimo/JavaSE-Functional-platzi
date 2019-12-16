package com.platzi.jobsearch;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public interface CommanderFunctions {
    /**
     * JCommander permite generar opciones de terminal de cualquier clase, por eso el primer parametro es
     * de tipo Object.
     *
     * @param object Clase de la cual se generaran los argumentos de JCommander
     * @return una instancia de JCommander. Idealmente con CLIArguments como objeto pasado.
     */
    static JCommander buildCommander(Object object) {
        return JCommander
                .newBuilder()
                .addObject(object)
                .build();
    }

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
        JCommander jCommander = buildCommander(argumentsSupplier.get());
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
            OnCommandError onCommandError
    ) {
        List<Object> result;
        try {
            jCommander.parse(arguments);

            return Optional.of(jCommander.getObjects());
        } catch (ParameterException exception) {
            onCommandError.onError(jCommander);
        }

        return Optional.empty();
    }

    @FunctionalInterface
    interface OnCommandError {
        void onError(JCommander jCommander);
    }
}
