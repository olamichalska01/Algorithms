package pl.edu.pw.ee;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import pl.edu.pw.ee.services.Sorting;

public class SelectionSortTest {
    Sorting selSort;

    @Before
    public void setUp() {
        selSort = new SelectionSort();
    }

    @Rule
    public ExpectedException e = ExpectedException.none();

    @Test
    public void shouldThrowException_When_NullArray() {
        double[] array = null;

        e.expect(IllegalArgumentException.class);
        e.expectMessage("Array cannot be null!");

        selSort.sort(array);
    }


    @Test
    public void shouldSort_When_Array_With_ZeroElems() {
        double[] array = {};

        selSort.sort(array);
        
        for(int i = array.length - 1; i > 0 ; i--) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    public void shouldSort_When_Array_With_OneElem() {
        double[] array = {1};

        selSort.sort(array);

        for(int i = array.length - 1; i > 0 ; i--) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    public void shouldSort_When_Array_With_TwoIdenticalElems() {
        double[] array = {1, 1};

        selSort.sort(array);

        for(int i = array.length - 1; i > 0 ; i--) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    public void shouldSort_When_Array_With_TwoDifferentElems() {
        double[] array = {1, -3};

        selSort.sort(array);

        for(int i = array.length - 1; i > 0 ; i--) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    public void shouldSort_When_Array_With_ElemsInAscendingOrder() {
        double[] array = {1, 2, 3, 4};

        selSort.sort(array);

        for(int i = array.length - 1; i > 0 ; i--) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    public void shouldSort_When_Array_With_ElemsInDescendingOrder() {
        double[] array = {5, 4, 2, -1};

        selSort.sort(array);
    
        for(int i = array.length - 1; i > 0 ; i--) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    public void shouldSort_When_Array_With_AllElemsInIncorrectPositions() {
        double[] array = {5, 6, 1, 2, 4};

        selSort.sort(array);
    
        for(int i = array.length - 1; i > 0 ; i--) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    public void shouldSort_When_Array_With_PartiallySortedElems() {
        double[] array = {1, 5, 3, 2, 4, 7, 8, 6};

        selSort.sort(array);

        for(int i = array.length - 1; i > 0 ; i--) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    public void shouldSort_When_Array_With_RandomElems() {
        double[] array;
        Random r1 = new Random(34);
        Random r2 = new Random(21);
        int arraySize = r1.nextInt(50);

        array = new double[arraySize];

        for(int i = 0; i < array.length; i++) {
            array[i] = r2.nextInt(100);
        }

        selSort.sort(array);

        for(int i = array.length - 1; i > 0 ; i--) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

}
