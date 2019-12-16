package com.platzi.jobsearch.cli;

import com.beust.jcommander.Parameter;

public final class CLIArguments {
    /**
     * Constructor default para permitir que solo clases en el paquete, puedan crear objetos
     * de esta clase.
     * De esta manera obligamos a que la construccion se haga mediante funciones publicas o
     * builders.
     * <p>
     * Las instancias deberan crearse mediante: CLIArguments#newInstance
     */
    CLIArguments() {
    }

    @Parameter(
            required = true,
            descriptionKey = "KEYWORD",
            description = "KEYWORD",
            validateWith = CLIKeywordValidator.class)
    private String keyword;

    @Parameter(
            names = {"--location", "-l"},
            description = "Ciudad, codigo postal o algun otro termino para buscar una ubicacion")
    private String location;

    @Parameter(
            names = {"--page", "-p"},
            description = "Cada busqueda contiene 50 posiciones, puedes paginar mas resultados cambiando el numero, la paginacion empieza en 0")
    private int page = 0;

    @Parameter(
            names = "--full-time",
            description = "Agregar esta bandera si se desea unicamente listar trabajos de 'full time'"
    )
    private boolean isFullTime = false;

    @Parameter(
            names = "--markdown",
            description = "Agregar esta bandera si se desea obtener los resultados en markdown"
    )
    private boolean isMarkdown = false;

    @Parameter(
            names = "--help",
            help = true,
            validateWith = CLIHelpValidator.class,
            description = "Muestra esta ayuda")
    private boolean help;

    public String getKeyword() {
        return keyword;
    }

    public String getLocation() {
        return location;
    }

    public int getPage() {
        return page;
    }

    public boolean isFullTime() {
        return isFullTime;
    }

    public boolean isMarkdown() {
        return isMarkdown;
    }

    public boolean isHelp() {
        return help;
    }

    @Override
    public String toString() {
        return "CLIArguments{" +
                "keyword='" + keyword + '\'' +
                ", location='" + location + '\'' +
                ", pages=" + page +
                ", isFullTime=" + isFullTime +
                ", help=" + help +
                ", isMarkdown=" + isMarkdown +
                '}';
    }

    /**
     * Esta funcion es equivalente a CLIArguments::new, sin embargo, si en el futuro queremos agregar
     * parametros adicionales, podemos limitar la manera de construir objetos mediante esta funcion.
     */
    public static CLIArguments newInstance() {
        return new CLIArguments();
    }
}
