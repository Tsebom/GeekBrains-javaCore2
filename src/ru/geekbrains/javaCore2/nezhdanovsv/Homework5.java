package ru.geekbrains.javaCore2.nezhdanovsv;

/**
 * Homework5
 * @author Nezhdanov Sergei
 * @version 1.0
 */
public class Homework5 {
    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {


        System.out.println("Время обработкм массива в одном потоке: " + firstMethod());
        System.out.println("Время обработкм массива в двух потоках: " + secondMethod());

    }

    /**
     * Create an array
     * @return - An array that was being created
     */
    public static float[] createArray() {
        float[] array = new float[size];
        for (int i = 0; i < size; i++) {
            array[i] = 1.0f;
        }

//        for (float a :
//                array) {
//            System.out.print(a + " ");
//        }

        return array;
    }

    /**
     * Calculate new values of an array
     * @param array - An initial array
     */
    public static void newValue(float[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//            System.out.println(array[i]);
        }

//        for (float a :
//                array) {
//            System.out.print(a + " ");
//        }
    }

    /**
     * Checking of time processing by one thread
     * @return The time of processing
     */
    public static long firstMethod() {
        long time = System.currentTimeMillis();
        newValue(createArray());
        return System.currentTimeMillis() - time;
    }

    /**
     * Create new a thread for calculate new values of an array
     * @param array An array that is processed
     * @return Thread
     */
    public static Thread createThread(float[] array) {
        return new Thread(() -> newValue(array));
    }

    /**
     * Checking of time processing by two thread
     * @return The time of processing
     */
    public static long secondMethod() {
        float[] shardLeft = new float[h];
        float[] shardRight = new float[h];
        float[] array = createArray();

        long time = System.currentTimeMillis();

        System.arraycopy(array, 0, shardLeft, 0, h);
        System.arraycopy(array, h, shardRight, 0, h);

//        System.out.println(shardLeft.length);
//        System.out.println(shardRight.length);

        Thread firstThread = createThread(shardLeft);
        Thread secondThread = createThread(shardRight);

        firstThread.start();
        secondThread.start();

        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(shardLeft, 0, array, 0, h);
        System.arraycopy(shardRight, 0, array, h, h);
//        System.out.println(array.length);

        return System.currentTimeMillis() - time;
    }
}
