package com.platzi.jobsearch;

import com.beust.jcommander.JCommander;
import com.platzi.jobsearch.api.JobsAPI;
import com.platzi.jobsearch.cli.CLIArguments;
import com.platzi.jobsearch.cli.CLIFunctions;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static com.platzi.jobsearch.CommanderFunctions.buildCommanderWithName;
import static com.platzi.jobsearch.CommanderFunctions.parseArguments;
import static com.platzi.jobsearch.api.APIFunctions.buildAPI;

public class JobSearch {
    public static void main(String[] args) {
        //Creacion de nuestro CLI con JCommander
        JCommander jCommander = buildCommanderWithName("job-search", CLIArguments::newInstance);

        //Obtenemos las opciones que se le dieron a JCommander
        Stream<CLIArguments> streamOfCLI = parseArguments(jCommander, args, JCommander::usage) //Nos retorna un Optional<List<Object>>
                        .orElse(Collections.emptyList()) //En caso de un Optional.empty()
                        .stream()
                        .map(CLIArguments.class::cast); // map(obj -> (CLIArguments) obj) give same result

        //Tomamos nuestro Stream y obtenemos las opciones que se dieron en el CLI
        Optional<CLIArguments> cliOptional = streamOfCLI
                .filter(cli -> !cli.isHelp()) //Solo nos interesan los casos donde no sea la solicitud de ayuda
                .filter(cli -> cli.getKeyword() != null) //Y que contengan un keyword, en otros caso no tenemos que buscar
                .findFirst();

        //Si el Optional tiene un dato, lo convertimos a Map<String,Object>
        cliOptional.map(CLIFunctions::toMap)
                .map(JobSearch::executeRequest) //Convertimos el Map en un request
                .orElse(Stream.empty()) //Aun seguimos operando sobre un Optional… en caso de que no hubiera datos generamos un stream vacio
                .forEach(System.out::println); //Imprimos los datos por pantalla.
    }

    private static Stream<JobPosition> executeRequest(Map<String, Object> options) {
        JobsAPI api = buildAPI(JobsAPI.class, "https://jobs.github.com");

        return Stream.of(options)
                .map(api::jobs)
                .flatMap(Collection::stream);
    }
}