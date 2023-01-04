package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if(nums == null) {
            throw new IllegalArgumentException("Array cannot be null!");
        }

        insort(nums);
    }

    private void insort(double[] array) {
        double numberToPlace;
        int j, i;

        for(i = 1; i < array.length; i++) {
            numberToPlace = array[i];
            j = i - 1;

            while(j >= 0 && array[j] > numberToPlace) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = numberToPlace;
        }
    }

}
