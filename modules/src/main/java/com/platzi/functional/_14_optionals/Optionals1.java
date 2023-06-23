package com.platzi.functional._14_optionals;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Optionals1 {
    public static void main(String[] args) {
        List<String> names = getNames();
        if (names != null) {
            //Operar
        }
        Optional<List<String>> optionalNames = getOptionalsNames();
        if (optionalNames.isPresent()) {

        }
        optionalNames.ifPresent(namesValue -> namesValue.forEach(System.out::println));
        Optional<String> valuablePlayer = optionalValuablePlayer();
        String valuablePlayerName = valuablePlayer.orElseGet(() -> "No player");
        System.out.println(valuablePlayerName);
    }


    static List<String> getNames() {
        return new LinkedList<>();
    }

    static String mostValuablesPlayers() {
        return null;
    }

    static int mostExpensiveItem() {
        return -1;
    }

    static Optional<List<String>> getOptionalsNames() {
        List<String> namesList = new LinkedList<>();
        //Obtener los nombres
        return Optional.of(namesList);
    }

    static Optional<String> optionalValuablePlayer() {
        //return Optional.ofNullable(mostValuablesPlayers());
        try {
            return Optional.of("Nestor");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }
}
