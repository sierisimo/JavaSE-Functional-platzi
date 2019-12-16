package com.platzi.jobsearch.cli;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 * Clase usada internamente por JCommande para validar ciertos argumentos.
 * En nuestro caso la usamos para frenar el parseo de argumentos si encontramos que se solicito
 * la opcion de ayuda.
 */
public class CLIHelpValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {
        boolean actualValue = Boolean.parseBoolean(value);
        if (actualValue) {
            throw new ParameterException("Help passed");
        }
    }
}
