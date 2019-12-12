package com.platzi.functional._03_immutable.immutable;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase final de nuestro diseño.
 *
 * Cuenta con mas de una mejora:
 *
 * 1. Es final, asi nadie puede extender de ella. No mas suplantaciones
 * 2. Las propiedades son finales, una vez creado un objeto no puede mutar
 * 3. El constructor exige todas las propiedades para generar un objeto
 *    (podria incluso generarse un builder derivado de este constructor)
 * 4. Cuando se accede a los emails, se quenera una copia! no se envia la lista mutable!
 */
public final class ImmutablePerson {
    private final String firstName;
    private final String lastName;

    private final List<String> emails;

    public ImmutablePerson(String firstName, String lastName, List<String> emails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emails = emails;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public final List<String> getEmails() {
        return new LinkedList<>(emails);
    }

    @Override
    public String toString() {
        return "ImmutablePerson{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emails=" + emails +
                '}';
    }
}