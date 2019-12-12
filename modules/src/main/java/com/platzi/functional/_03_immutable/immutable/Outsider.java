package com.platzi.functional._03_immutable.immutable;

import java.util.LinkedList;
import java.util.List;

public class Outsider {
    public static void main(String[] args) {
        String firstName = "Sinuhe";
        String lastName = "Jaime";

        List<String> emails = new LinkedList<>();
        emails.add("sier@sier.com");

        ImmutablePerson sier = new ImmutablePerson(firstName, lastName, emails);

        System.out.println(sier);
        badIntentionedMethod(sier);
        System.out.println(sier);
    }

    /**
     * No importa que el metodo intente modificar a la persona, la persona esta diseñada
     * para no recibir modificaciones.
     */
    static void badIntentionedMethod(ImmutablePerson person) {
        List<String> emails = person.getEmails();
        emails.clear();
        emails.add("imnotthebadguy@mail.com");
    }
}
