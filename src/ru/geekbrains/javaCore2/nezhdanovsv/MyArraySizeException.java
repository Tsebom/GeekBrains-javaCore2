package ru.geekbrains.javaCore2.nezhdanovsv;

/**
 * Throw to indicate that an dimension of array is incorrect
 */
public class MyArraySizeException extends IllegalArgumentException{
    public MyArraySizeException(String s) {
        super(s);
    }
}
