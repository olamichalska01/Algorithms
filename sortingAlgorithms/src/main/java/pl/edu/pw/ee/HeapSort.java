package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class HeapSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if(nums == null) {
            throw new IllegalArgumentException("Array cannot be null!");
        }

        heapSort(nums);        
    }

    private void heapSort(double[] array) {
        Heap<Double> heap = new Heap<>();

        for(int i = 0; i < array.length; i++) {
            heap.put(array[i]);
        }

        for(int i = array.length - 1; i >= 0; i--) {
            array[i] = heap.pop();
        }
    }
    
}
