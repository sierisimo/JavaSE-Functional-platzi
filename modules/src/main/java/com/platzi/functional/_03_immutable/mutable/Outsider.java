package com.platzi.functional._03_immutable.mutable;

import java.util.LinkedList;
import java.util.List;

public class Outsider {
    public static void main(String[] args) {
        List<String> sierEmail = new LinkedList<>();
        sierEmail.add("sier@sier.com");

        MutablePerson sier = new MutablePerson();
        sier.setEmails(sierEmail);
        sier.setFirstName("Israel");
        sier.setFirstName("Sergio");


        //Veamos un poco los peligros de una clase mutable
        System.out.println(sier);
        badFunction(sier);
        System.out.println(sier);


        System.out.println("///////////////////////////////");


        MutablePerson_2 sinuhe = new MutablePerson_2(sierEmail);
        System.out.println(sinuhe);
        otherBadFunction(sinuhe);
        System.out.println(sinuhe);


        System.out.println("///////////////////////////////");


        MutablePerson_3 sinuhe_3 = new MutablePerson_3(sierEmail);
        System.out.println(sinuhe_3);
        otherBadFunctionPart3(sinuhe_3);
        System.out.println(sinuhe_3);





        System.out.println("///////////////////////////////");





        MutablePerson_3 sinuhe_4 = new MutablePerson_4(sierEmail);
        System.out.println(sinuhe_4);
    }

    /**
     * Este metodo modifica la lista mediante un setter.
     * Tener el setter es peligroso…
     */
    static void badFunction(MutablePerson person) {
        List<String> emails = new LinkedList<>();
        emails.add("imnotevil@mail.com");

        person.setEmails(emails);
    }

    /**
     * Este metodo toma el objeto devuelto por el getter… pero el
     * objeto es mutable, asi que podemos modificarlo sin restricciones…
     */
    static void otherBadFunction(MutablePerson_2 person) {
        List<String> emails = person.getEmails();
        emails.clear();

        emails.add("imnotevil@mail.com");
    }

    static void otherBadFunctionPart3(MutablePerson_3 person) {
        List<String> spammyEmails = new LinkedList<>();
        spammyEmails.add("tubanco@mibanco.banco.com");
        spammyEmails.add("cheapfoods@blackmarket.com");

        List<String> emails = person.getEmails();
        emails.clear();

        emails.add("imnotevil@mail.com");
        emails.addAll(spammyEmails);
    }
}
