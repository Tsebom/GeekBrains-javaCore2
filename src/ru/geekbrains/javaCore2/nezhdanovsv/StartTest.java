package ru.geekbrains.javaCore2.nezhdanovsv;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class StartTest {
    public static void start(Class c) {
        implementTest(c);
    }

    public static void start(String c) {
        try {
            implementTest(Class.forName(c));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void implementTest(Class c) throws RuntimeException{

        Map<Method, Integer> mapMethod = new HashMap<>();

        Method[] methods = c.getDeclaredMethods();

        for (Method method : methods) {
            if(method.isAnnotationPresent(Test.class)) {
                mapMethod.put(method, method.getAnnotation(Test.class).priority());
            }
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (!mapMethod.containsValue(0)) {
                    mapMethod.put(method, 0);
                }
                else {
                    throw new RuntimeException("");
                }
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (!mapMethod.containsValue(11)) {
                    mapMethod.put(method, 11);
                }
                else {
                    throw new RuntimeException("");
                }
            }
        }

        /**
         * Sorted map by value
         */
        Map<Method, Integer> mapSort = mapMethod.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        for (Map.Entry<Method, Integer> m : mapSort.entrySet()) {
            try {
                m.getKey().invoke(null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
