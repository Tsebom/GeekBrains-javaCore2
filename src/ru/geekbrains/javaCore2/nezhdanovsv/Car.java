package ru.geekbrains.javaCore2.nezhdanovsv;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {
    private static int CARS_COUNT;

    private final CyclicBarrier cyclicBarrier;

    private static final Lock gold = new ReentrantLock();
    private static final Lock silver = new ReentrantLock();
    private static final Lock bronze = new ReentrantLock();

    static {
        CARS_COUNT = 0;
    }

    private final Race race;
    private final int speed;
    private final String name;
    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier cyclicBarrier) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.cyclicBarrier = cyclicBarrier;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cyclicBarrier.await();
            cyclicBarrier.await();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }

            if (gold.tryLock()) {
                System.out.println(this.name + " GOLD");
                cyclicBarrier.await();
                return;
            }

            if (silver.tryLock()) {
                System.out.println(this.name + " SILVER");
                cyclicBarrier.await();
                return;
            }

            if (bronze.tryLock()) {
                System.out.println(this.name + " BRONZE");
                cyclicBarrier.await();
                return;
            }

            System.out.println(this.name + " LOSER");
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
