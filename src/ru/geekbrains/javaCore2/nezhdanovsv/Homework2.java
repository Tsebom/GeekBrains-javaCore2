package ru.geekbrains.javaCore2.nezhdanovsv;

import java.util.Arrays;

/**
 * Homework2
 * @author Nezhdanov Sergei
 * @version 1.0
 */
public class Homework2 {

    public static void main(String[] args) {
        String[][] array = {{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "/"}};
        try {
            System.out.println(sumIntFromArray(array));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
            System.out.println("The cell " + e.getRow() + "/" + e.getCell() + " has incorrect data");
            array[e.getRow() - 1][e.getCell() - 1] = Integer.toString(e.getCell());
        } finally {
            System.out.println(sumIntFromArray(array));
        }
    }

    /**
     * Calculate of sum all items in an array
     * @param array - initial array
     * @throws MyArraySizeException if dimensions of array are incorrect
     * @throws MyArrayDataException if data of array is not integer number
     * */
    public static int sumIntFromArray(String [][] array) throws MyArraySizeException, MyArrayDataException{
        int result = 0;
        if (array.length != 4) {
            throw new MyArraySizeException("Incorrect size the array of the first dimension");
        }else if (array[0].length != 4) {
            throw new MyArraySizeException("Incorrect size the array of a second dimension");
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    result += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Incorrect data in a cell", i, j);
                }
            }
        }
        return result;
    }
}