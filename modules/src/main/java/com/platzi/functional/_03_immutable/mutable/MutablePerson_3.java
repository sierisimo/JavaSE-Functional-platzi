package com.platzi.functional._03_immutable.mutable;

import java.util.List;

/**
 * Mas mejoras. Ahora nuestra lista de emails es final. Eso nos garantiza que nadie sobre escribe
 * el valor de la propiedad y una vez creado siempre sera la misma lista.
 *
 * Eso deberia resolver el problema… cierto?
 */
public class MutablePerson_3 {
    private String firstName;
    private String lastName;

    private final List<String> emails;

    public MutablePerson_3(List<String> emails) {
        this.emails = emails;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getEmails() {
        return emails;
    }

    @Override
    public String toString() {
        return "MutablePerson_3{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emails=" + emails +
                '}';
    }
}
