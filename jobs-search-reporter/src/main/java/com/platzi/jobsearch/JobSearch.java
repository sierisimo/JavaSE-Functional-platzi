package com.platzi.jobsearch;

import com.beust.jcommander.JCommander;
import com.platzi.jobsearch.cli.CLIArguments;

import static com.platzi.jobsearch.CommanderFunctions.buildCommanderWithName;

public class JobSearch {
    public static void main(String[] args) {
        //Creacion de nuestro CLI con JCommander
        JCommander jCommander = buildCommanderWithName("job-search", CLIArguments::newInstance);
    }
}