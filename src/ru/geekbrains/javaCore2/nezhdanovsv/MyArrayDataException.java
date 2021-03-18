package ru.geekbrains.javaCore2.nezhdanovsv;

import java.util.IllegalFormatException;

/**
 * Throw to indicate that an item in an array is incorrect
 */
public class MyArrayDataException extends NumberFormatException {
    private int row;
    private int cell;

    public MyArrayDataException(int row, int cell) {
        this.row = row;
        this.cell = cell;
    }

    public MyArrayDataException(String s, int row, int cell) {
        super(s);
        this.row = row + 1;
        this.cell = cell + 1;
    }

    public int getRow() {
        return row;
    }

    public int getCell() {
        return cell;
    }
}
