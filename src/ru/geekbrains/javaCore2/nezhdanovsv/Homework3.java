package ru.geekbrains.javaCore2.nezhdanovsv;

import java.util.HashMap;
import java.util.Map;

/**
 * Homework3
 * @author Nezhdanov Sergei
 * @version 1.0
 */
public class Homework3 {

    public static void main(String[] args) {
        String[] array = {
                "bla bla",
                "blo blo",
                "blu blu",
                "bli bli",
                "bla bla",
                "bla bla",
                "bla bla",
                "blo blo",
                "bla bla",
                "bli bli",
                "bla bla",
                "blo blo",
                "bla bla",
                "blu blu",
                "bla bla",
        };
        System.out.println("Exercise 1:");
        printMap(uniqueWords(array));

        System.out.println("\nExercise 2:");
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Vova", 888888888);
        phoneBook.add("Lola", 777777777);
        phoneBook.add("Sova", 666666666);
        phoneBook.add("Kova", 555555555);
        phoneBook.add("Vova", 444444444);

        phoneBook.get("Vova");
        System.out.println();
        phoneBook.get("Tola");
    }

    /**
     * Calculate the number of same words in an array
     * @param array - initial array
     * @return - Map collection, there a word is key and an quantity is value
     */
    public static HashMap<String, Integer> uniqueWords(String[] array) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : array) {
            if (!map.containsKey(s)) {
                map.put(s, 1);
            }
            else {
                map.put(s, map.get(s) + 1);
            }
        }
        return map;
    }

    /**
     * Print a Map with quantity of words
     * @param map - initial Map
     */
    public static void printMap(HashMap<String, Integer> map) {
        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }
    }
}
