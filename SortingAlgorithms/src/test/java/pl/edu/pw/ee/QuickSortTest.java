package pl.edu.pw.ee;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import pl.edu.pw.ee.services.Sorting;

public class QuickSortTest {
    Sorting qsort;

    @Before
    public void setUp() {
        qsort = new QuickSort();
    }

    @Rule
    public ExpectedException e = ExpectedException.none();

    @Test
    public void shouldThrowException_When_NullArray() {
        double[] array = null;

        e.expect(IllegalArgumentException.class);
        e.expectMessage("Nums array cannot be null");

        qsort.sort(array);
    }


    @Test
    public void shouldSort_When_Array_With_ZeroElems() {
        double[] array = {};

        qsort.sort(array);
        
        for(int i = array.length - 1; i > 0 ; i--) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    public void shouldSort_When_Array_With_OneElem() {
        double[] array = {1};

        qsort.sort(array);

        for(int i = array.length - 1; i > 0 ; i--) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    public void shouldSort_When_Array_With_TwoIdenticalElems() {
        double[] array = {1, 1};

        qsort.sort(array);

        for(int i = array.length - 1; i > 0 ; i--) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    public void shouldSort_When_Array_With_TwoDifferentElems() {
        double[] array = {1, -3};

        qsort.sort(array);

        for(int i = array.length - 1; i > 0 ; i--) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    public void shouldSort_When_Array_With_ElemsInAscendingOrder() {
        double[] array = {1, 2, 3, 4};

        qsort.sort(array);

        for(int i = array.length - 1; i > 0 ; i--) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    public void shouldSort_When_Array_With_ElemsInDescendingOrder() {
        double[] array = {5, 4, 2, -1};

        qsort.sort(array);
    
        for(int i = array.length - 1; i > 0 ; i--) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    public void shouldSort_When_Array_With_AllElemsInIncorrectPositions() {
        double[] array = {5, 6, 1, 2, 4};

        qsort.sort(array);
    
        for(int i = array.length - 1; i > 0 ; i--) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    public void shouldSort_When_Array_With_PartiallySortedElems() {
        double[] array = {1, 5, 3, 2, 4, 7, 8, 6};

        qsort.sort(array);

        for(int i = array.length - 1; i > 0 ; i--) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    public void shouldSort_When_Array_With_RandomElems() {
        double[] array;
        Random r1 = new Random(51);
        Random r2 = new Random(16);
        int arraySize = r1.nextInt(100);

        array = new double[arraySize];

        for(int i = 0; i < array.length; i++) {
            array[i] = r2.nextInt(100);
        }

        qsort.sort(array);

        for(int i = array.length - 1; i > 0 ; i--) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

}
