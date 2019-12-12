package com.platzi.functional._03_immutable.mutable;

import java.util.List;

/**
 * Clase mejorada.
 * Ahora obligamos a quien use esta clase a crear instancias usando el constructor.
 * Quitamos el setter para evitar que hagan modificaciones peligrosas…
 */
public class MutablePerson_2 {
    private String firstName;
    private String lastName;

    private List<String> emails;

    public MutablePerson_2(List<String> emails) {
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
        return "MutablePerson_2{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emails=" + emails +
                '}';
    }
}
