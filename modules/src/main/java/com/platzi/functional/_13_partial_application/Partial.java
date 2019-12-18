package com.platzi.functional._13_partial_application;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Partial {

    static void partialApplication() {
        /*
        Usando currying podemos hacer uso de otra estrategia que se conoce como aplicacion parcial.

        Hacer uso de la aplicacion parcial es almacenar la version "curried" de una funcion pero
        con un parametro.

        Tomemos una BiFunction por ejemplo:
         */
        BiFunction<Integer, Integer, Integer> multiplyXbyY = (x, y) -> x * y;

        //Es facil usarla:
        System.out.println(
                multiplyXbyY.apply(5, 4) //20
        );

        //Pero si quisieramos asignar un valor fijo a una funcion? Partial application al rescate.

        /*
        Primero, definamos una funcion que hace currying:
         */
        Function<BiFunction<Integer, Integer, Integer>, Function<Integer, Function<Integer, Integer>>>
                curryBiFunction = biFun -> X -> Y -> biFun.apply(X, Y);

        /*
        Ahora podemos definir una funcion que genera mas funciones, usando la BiFunction que ya tenemos:
         */
        Function<Integer, Function<Integer, Integer>> multiplyBy =
                x -> curryBiFunction.apply(multiplyXbyY).apply(x);

        //Y crear funciones para valores especificos:
        Function<Integer, Integer> multiplyBy5 = y -> multiplyBy.apply(5).apply(y);

        System.out.println(
                multiplyBy5.apply(20)
        );

        Function<Integer, Integer> multiplyBy2 = x -> multiplyBy.apply(2).apply(x);

        System.out.println(
                multiplyBy2.apply(10)
        );
    }


//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

    /**
     * Aqui un ejemplo mas complejo:
     */
    static void ejemploDB(DBConfiguration connectionConf) {
        //Imagina que tuvieramos esta funcion, que puede recibir una configuracion
        // de conexion a una base de datos y un query a ejecutar sobre dicha conexion.
        BiFunction<DBConfiguration, Query, QueryResult> biDB = (conf, query) ->
                new DataBaseConnection(conf).executeQuery(query);

        //Por cada corrida sobre esa funcion, tendremos que pasar la configuracion
        QueryResult result1 = biDB.apply(connectionConf, new Query("SELECT …"));
        //Que aunque no esta mal, parece algo redundante…
        result1 = biDB.apply(connectionConf, new Query("INSERT …"));

        //Podemos aplicar curry a nuestra bifunction para simplificar las llamadas:
        Function<DBConfiguration, Function<Query, QueryResult>> dbFunCreator = curryBiFunction(biDB);

        //Creamos una funcion que trabajara sobre postgres…
        Function<Query, QueryResult> postgresExecutor =
                pgQuery -> dbFunCreator.apply(new DBConfiguration(/*Postgres configs*/)).apply(pgQuery);

        //Y otra funcion para MariaDB
        Function<Query, QueryResult> mariaDBExecutor =
                query -> dbFunCreator.apply(connectionConf).apply(query);

        postgresExecutor.apply(new Query("SELECT…"));
        postgresExecutor.apply(new Query("INSERT…"));

        mariaDBExecutor.apply(new Query("UPDATE…"));
    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//

    static <F, S, R> Function<F, Function<S, R>> curryBiFunction(BiFunction<F, S, R> biFunction) {
        return f -> s -> biFunction.apply(f, s);
    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//

    static class QueryResult {

    }

    static class Query {
        public Query() {

        }

        public Query(String query) {
        }
    }

    static class DBConfiguration {
        private String host;
        private String user;
        private String password;

        private int port;

        public DBConfiguration() {
        }

        public DBConfiguration(String host, String user, String password, int port) {
            this.host = host;
            this.user = user;
            this.password = password;
            this.port = port;
        }
    }

    static class DataBaseConnection {
        public DataBaseConnection(DBConfiguration dbConfiguration) {
        }

        public QueryResult executeQuery(Query query) {
            return null;
        }
    }
}
