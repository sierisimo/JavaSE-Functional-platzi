package com.platzi.functional._04_functional;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class _03_ConsumerSupplierDemos {
    /**
     * Con un Consumer podemos generar una funcion simple que toma un dato y no regresa ningun valor
     * El ejemplo mas comun es tomar un valor e imprimirlo por pantalla, o en nuestro ejemplo
     * tomar un dato y almacenarlo en un archivo y una base de datos.
     */
    private static void persistAccount(Account account) {
        Consumer<Account> toFile = acnt -> saveToFile(acnt);

        Consumer<Account> toDB = acnt -> getDataBaseConnection().insert(acnt);

        //saveAccountTo no le interesa lo que el Consumer haga, simplemente necesita
        //una manera de pasarle el dato para almacenarlo.
        saveAccountTo(account, toFile);
        saveAccountTo(account, toDB);
    }

    private static void saveAccountTo(Account account, Consumer<Account> saveFunction) {
        //Ejecutar validaciones antes de almacenar
        //…
        //
        //Despues almacenamos sin preocuparnos como
        saveFunction.accept(account);
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

    /**
     * Un Supplier es una funcion que sabe como conseguir datos.
     * Este tipo de funciones se utiliza principalmente cuando tenemos secuencias
     * o requerimos de algun proceso mas que un simple constructor para generar un dato.
     * <p>
     * No existe una regla o condicion de si el Supplier debe generar un nuevo objeto
     * o si puede devolver siempre el mismo o generar alguno de manera aleatoria.
     */
    private void supplierDemo() {
        Supplier<Double> numberSupplier = () -> Math.random();

        Supplier<DataBaseExecutor> dbSupplier = () -> getDataBaseConnection();

        Function<String, Integer> dbFunction = sId -> dbSupplier.get().select(sId);

        //La principal razon para usar Suppliers es crear funciones que no sepan la procedencia
        //de los datos. En la programacion funcional debes preocuparte mas por el "como" hacer algo
        //no por el "que" debe hacerse con algo.

        //Adicionalmente, un supplier al ser un objeto, puede ser usado en diferentes lados
        //manteniendo solo la referencia.
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


    private static void saveToFile(Account account) {

    }

    private static DataBaseExecutor getDataBaseConnection() {
        return null;
    }

    private class Account {
        private final String id;
        private final double balance;

        public Account(String id, double balance) {
            this.id = id;
            this.balance = balance;
        }
    }

    private interface DataBaseExecutor {
        <T> void insert(T instance);

        <T> T select(String id);
    }
}
