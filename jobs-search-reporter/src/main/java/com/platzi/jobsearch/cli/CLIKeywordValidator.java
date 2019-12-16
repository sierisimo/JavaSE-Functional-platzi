package com.platzi.jobsearch.cli;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 * Clase usada por JCommander para validar argumentos.
 * <p>
 * En nuestro caso la usamos para validar que el skill solicitiado (keyword) sea unicamente letras y numeros.
 */
public class CLIKeywordValidator implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        if (!value.matches("^[a-zA-Z]+[0-9]*$")) {
            System.err.println("Keyword: " + value + " no es un Keyword valido, keywords deben ser alfanumericas.\n");
            throw new ParameterException("Only alphanumerics are supported");
        }
    }
}
