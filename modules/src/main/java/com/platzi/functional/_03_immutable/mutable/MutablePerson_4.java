package com.platzi.functional._03_immutable.mutable;

import java.util.LinkedList;
import java.util.List;

/**
 * Alguien decide que puede extender de nuestra clase y cambiar solo algunos
 * elementos… ¡nos hackea!
 *
 * De nada sirvieron las modificaciones en la clase MutablePerson_3, esta clase nos
 * esta suplantando!
 */
public class MutablePerson_4 extends MutablePerson_3 {
    public MutablePerson_4(List<String> emails) {
        super(emails);
    }

    @Override
    public List<String> getEmails() {
        List<String> spammyEmails = new LinkedList<>();
        spammyEmails.add("tubanco@mibanco.banco.com");
        spammyEmails.add("cheapfoods@blackmarket.com");

        return spammyEmails;
    }
}
