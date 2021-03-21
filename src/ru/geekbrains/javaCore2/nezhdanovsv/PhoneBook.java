package ru.geekbrains.javaCore2.nezhdanovsv;

import java.util.HashMap;
import java.util.Map;

/**
 * For storage phone numbers of people
 */
public class PhoneBook {
    private Map<Integer, String> map = new HashMap<>();

    public void add(String name, Integer number) {
        this.map.put(number, name);
    }

    public void get(String name) {
        if (map.containsValue(name)){
            System.out.println(name + "'s number:");
            for (Map.Entry<Integer, String > pair: map.entrySet()) {
                if (name.equals(pair.getValue())) {
                    System.out.println(pair.getKey());
                }
            }
            return;
        }
        System.out.println("The phonebook has not " + name + "'s number");
    }
}
