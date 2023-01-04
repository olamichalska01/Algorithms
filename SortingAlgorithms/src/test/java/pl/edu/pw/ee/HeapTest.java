package pl.edu.pw.ee;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class HeapTest {
    Heap<Double> heap;

    @Before
    public void setUp() {
        heap = new Heap<>();
    }

    @Rule
    public ExpectedException e = ExpectedException.none();

    @Test
    public void put_OneElem() {
        double[] array = { 1 };

        for (int i = 0; i < array.length; i++) {
            heap.put(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            int childIndex = 2 * i + 1;

            if (childIndex < array.length) {
                assertTrue(heap.getItem(i) >= heap.getItem(childIndex));
            }

            if (childIndex + 1 < array.length) {
                assertTrue(heap.getItem(i) >= heap.getItem(childIndex + 1));
            }
        }
    }

    @Test
    public void put_TwoElemsInAscendingOrder() {
        double[] array = { 1, 2 };

        for (int i = 0; i < array.length; i++) {
            heap.put(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            int childIndex = 2 * i + 1;

            if (childIndex < array.length) {
                assertTrue(heap.getItem(i) >= heap.getItem(childIndex));
            }

            if (childIndex + 1 < array.length) {
                assertTrue(heap.getItem(i) >= heap.getItem(childIndex + 1));
            }
        }
    }

    @Test
    public void put_TwoElemsInDescendingOrder() {
        double[] array = { 2, 1 };

        for (int i = 0; i < array.length; i++) {
            heap.put(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            int childIndex = 2 * i + 1;

            if (childIndex < array.length) {
                assertTrue(heap.getItem(i) >= heap.getItem(childIndex));
            }

            if (childIndex + 1 < array.length) {
                assertTrue(heap.getItem(i) >= heap.getItem(childIndex + 1));
            }
        }
    }

    @Test
    public void put_TwoIdenticalElems() {
        double[] array = { 1, 1 };

        for (int i = 0; i < array.length; i++) {
            heap.put(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            int childIndex = 2 * i + 1;

            if (childIndex < array.length) {
                assertTrue(heap.getItem(i) >= heap.getItem(childIndex));
            }

            if (childIndex + 1 < array.length) {
                assertTrue(heap.getItem(i) >= heap.getItem(childIndex + 1));
            }
        }
    }

    @Test
    public void put_ThreeIdenticalElems() {
        double[] array = { 1, 1, 1 };

        for (int i = 0; i < array.length; i++) {
            heap.put(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            int childIndex = 2 * i + 1;

            if (childIndex < array.length) {
                assertTrue(heap.getItem(i) >= heap.getItem(childIndex));
            }

            if (childIndex + 1 < array.length) {
                assertTrue(heap.getItem(i) >= heap.getItem(childIndex + 1));
            }
        }
    }

    @Test
    public void put_TwoIdenticalPairsOfElems() {
        double[] array = { 1, 3, 3, 1 };

        for (int i = 0; i < array.length; i++) {
            heap.put(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            int childIndex = 2 * i + 1;

            if (childIndex < array.length) {
                assertTrue(heap.getItem(i) >= heap.getItem(childIndex));
            }

            if (childIndex + 1 < array.length) {
                assertTrue(heap.getItem(i) >= heap.getItem(childIndex + 1));
            }
        }
    }

    @Test
    public void put_ElemsInAscendingOrder() {
        double[] array = { 1, 5, 6, 7, 12, 43, 67, 222 };

        for (int i = 0; i < array.length; i++) {
            heap.put(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            int childIndex = 2 * i + 1;

            if (childIndex < array.length) {
                assertTrue(heap.getItem(i) >= heap.getItem(childIndex));
            }

            if (childIndex + 1 < array.length) {
                assertTrue(heap.getItem(i) >= heap.getItem(childIndex + 1));
            }
        }
    }

    @Test
    public void put_ElemsInDescendingOrder() {
        double[] array = { 90, 82, 32, 11, 2, 1, -1 };

        for (int i = 0; i < array.length; i++) {
            heap.put(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            int childIndex = 2 * i + 1;

            if (childIndex < array.length) {
                assertTrue(heap.getItem(i) >= heap.getItem(childIndex));
            }

            if (childIndex + 1 < array.length) {
                assertTrue(heap.getItem(i) >= heap.getItem(childIndex + 1));
            }
        }
    }

    @Test
    public void put_RandomElems() {
        Random r = new Random(21);
        Random rSize = new Random(88);
        int size = rSize.nextInt(50);
        double[] array = new double[size];

        for (int i = 0; i < size; i++) {
            array[i] = r.nextInt(100);
            heap.put(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            int childIndex = 2 * i + 1;

            if (childIndex < array.length) {
                assertTrue(heap.getItem(i) >= heap.getItem(childIndex));
            }

            if (childIndex + 1 < array.length) {
                assertTrue(heap.getItem(i) >= heap.getItem(childIndex + 1));
            }
        }
    }

    @Test
    public void pop_WhenArrayIsEmpty() {
        e.expect(ArrayIndexOutOfBoundsException.class);
        e.expectMessage("Heap is empty!");

        heap.pop();
    }

    @Test
    public void pop_WhenArrayWithOneElem() {
        double[] array = { 1 };

        for (int i = 0; i < array.length; i++) {
            heap.put(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = heap.pop();
        }

        for (int i = 0; i < array.length - 1; i++) {
            assertTrue(array[i] >= array[i + 1]);
        }
    }

    @Test
    public void pop_WhenArrayWithTwoDifferentElems() {
        double[] array = { 1, 2 };

        for (int i = 0; i < array.length; i++) {
            heap.put(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = heap.pop();
        }

        for (int i = 0; i < array.length - 1; i++) {
            assertTrue(array[i] >= array[i + 1]);
        }
    }

    @Test
    public void pop_WhenArrayWithTwoIdenticalElems() {
        double[] array = { 1, 1 };

        for (int i = 0; i < array.length; i++) {
            heap.put(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = heap.pop();
        }

        for (int i = 0; i < array.length - 1; i++) {
            assertTrue(array[i] >= array[i + 1]);
        }
    }

    @Test
    public void pop_WhenArrayWithTwoIdenticalPairsOfElems() {
        double[] array = { 1, 1, 3, 3 };

        for (int i = 0; i < array.length; i++) {
            heap.put(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = heap.pop();
        }

        for (int i = 0; i < array.length - 1; i++) {
            assertTrue(array[i] >= array[i + 1]);
        }
    }

    @Test
    public void pop_WhenArrayWithThreeIdenticalElems() {
        double[] array = { 1, 1, 1 };

        for (int i = 0; i < array.length; i++) {
            heap.put(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = heap.pop();
        }

        for (int i = 0; i < array.length - 1; i++) {
            assertTrue(array[i] >= array[i + 1]);
        }
    }

    @Test
    public void pop_WhenArrayWithElemsInAscendingOrder() {
        double[] array = { 1, 2, 3, 4, 5, 6 };

        for (int i = 0; i < array.length; i++) {
            heap.put(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = heap.pop();
        }

        for (int i = 0; i < array.length - 1; i++) {
            assertTrue(array[i] >= array[i + 1]);
        }
    }

    @Test
    public void pop_WhenArrayWithElemsInDescendingOrder() {
        double[] array = { 23, 22, 21, 5, 2, 0, -32 };

        for (int i = 0; i < array.length; i++) {
            heap.put(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = heap.pop();
        }

        for (int i = 0; i < array.length - 1; i++) {
            assertTrue(array[i] >= array[i + 1]);
        }
    }
    
    @Test
    public void pop_WhenArrayWithRandomElems() {
        Random r = new Random(27);
        Random rSize = new Random(48);
        int size = rSize.nextInt(50);
        double[] array = new double[size];

        for (int i = 0; i < size; i++) {
            array[i] = r.nextInt(100);
            heap.put(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = heap.pop();
        }

        for (int i = 0; i < array.length - 1; i++) {
            assertTrue(array[i] >= array[i + 1]);
        }
    }

}
