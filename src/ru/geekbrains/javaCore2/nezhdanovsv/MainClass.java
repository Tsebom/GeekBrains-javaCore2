package ru.geekbrains.javaCore2.nezhdanovsv;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * Homework5
 * @author Nezhdanov Sergei
 * @version 1.0
 */
public class MainClass {
    public static final int CARS_COUNT = 4;

    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT + 1);
    private static final Semaphore semaphore = new Semaphore(CARS_COUNT / 2);

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(semaphore), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cyclicBarrier);
        }

        for (Car car : cars) {
            new Thread(car).start();
        }

        try {
            cyclicBarrier.await();

            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

            cyclicBarrier.await();
            cyclicBarrier.await();

            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
