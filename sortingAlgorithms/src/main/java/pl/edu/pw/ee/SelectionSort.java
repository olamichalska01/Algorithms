package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class SelectionSort implements Sorting {

    @Override
    public void sort(double[] nums) { 
        if(nums == null) {
            throw new IllegalArgumentException("Array cannot be null!!");
        }   

        selectionSort(nums);
    }

    private void selectionSort(double[] array) {
        int i, j, minIndex;
        double minVal;

        for(i = 0; i < array.length - 1; i++) {
            minVal = array[i];
            minIndex = i;

            for(j = i + 1; j < array.length; j++) {
                if(array[j] < minVal) {
                    minVal = array[j];
                    minIndex = j;
                }
            }

            array[minIndex] = array[i];
            array[i] = minVal;  
        }        
    }
    
}