package pl.edu.pw.ee;

import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

public class WriteToFileQuickSortTest {
    Sorting qsort;

    @Before
    public void setUp() {
        qsort = new QuickSort();
    }

    @Test
    public void Should_Sort_When_ArrayWithPivotInTheMiddle() {
        // For quick sort, best case scenario
        // is an array with pivot in the middle.
        // (Array where first half is smaller than the first element 
        //  and second half is greater than the first element.)

        double[] array;
        FileWriter file;

        Random r = new Random(67);
        int arraySize = 50;
        int numOfTests = 50;
        int numOfTestsPerArraySize = 10;
        long testDuration, sumOfTestDurations = 0;
        long averageTestDuration;

        try {
            file = new FileWriter("src\\test\\java\\pl\\edu\\pw\\ee\\QuickSortFiles\\optymisticData_QuickSort.txt");

            while (numOfTests-- > 0) {

                while (numOfTestsPerArraySize-- > 0) {
                    array = new double[arraySize];

                    array[0] = 100;

                    for (int i = 1; i < array.length / 2; i++) {
                        array[i] = array[0] - r.nextInt(10);
                    }

                    for (int i = array.length / 2; i < array.length; i++) {
                        array[i] = array[0] + r.nextInt(20);
                    }

                    long start = System.nanoTime();
                    qsort.sort(array);
                    testDuration = System.nanoTime() - start;
                    sumOfTestDurations += testDuration;

                    for (int i = array.length - 1; i > 0; i--) {
                        assertTrue(array[i] >= array[i - 1]);
                    }
                }

                numOfTestsPerArraySize = 10;
                averageTestDuration = sumOfTestDurations / numOfTestsPerArraySize;

                file.append(arraySize + "   " + averageTestDuration + "\n");

                arraySize += 50;
            }

            file.close();

        } catch (IOException exception) {
            System.out.println("Unable to write to file.");
        }

    }

    @Test
    public void Should_Sort_When_ArrayWithElemsInDescendingOrder() {
        // For quick sort, worst case scenario
        // is an array with pivot at the beginning or end.
        // (Array in descending or ascending order)

        double[] array;
        FileWriter file;

        Random r = new Random(64);
        int arraySize = 50;
        int numOfTests = 50;
        int numOfTestsPerArraySize = 10;
        long testDuration, sumOfTestDurations = 0;
        long averageTestDuration;

        try {
            file = new FileWriter("src\\test\\java\\pl\\edu\\pw\\ee\\QuickSortFiles\\pessymisticData_QuickSort.txt");

            while (numOfTests-- > 0) {

                while (numOfTestsPerArraySize-- > 0) {
                    array = new double[arraySize];
                    array[0] = r.nextInt(100);

                    for (int i = 1; i < array.length; i++) {
                        array[i] = array[i - 1] - r.nextInt(10);
                    }

                    long start = System.nanoTime();
                    qsort.sort(array);
                    testDuration = System.nanoTime() - start;
                    sumOfTestDurations += testDuration;

                    for (int i = array.length - 1; i > 0; i--) {
                        assertTrue(array[i] >= array[i - 1]);
                    }
                }

                numOfTestsPerArraySize = 10;
                averageTestDuration = sumOfTestDurations / numOfTestsPerArraySize;

                file.append(arraySize + "   " + averageTestDuration + "\n");

                arraySize += 50;
            }

            file.close();

        } catch (IOException exception) {
            System.out.println("Unable to write to file.");
        }

    }

    @Test
    public void Should_Sort_When_ArrayWithRandomElems() {
        double[] array;
        FileWriter file;

        Random r = new Random(22);
        int arraySize = 50;
        int numOfTests = 50;
        int numOfTestsPerArraySize = 10;
        long testDuration, sumOfTestDurations = 0;
        long averageTestDuration;

        try {
            file = new FileWriter("src\\test\\java\\pl\\edu\\pw\\ee\\QuickSortFiles\\randomData_QuickSort.txt");

            while (numOfTests-- > 0) {

                while (numOfTestsPerArraySize-- > 0) {
                    array = new double[arraySize];

                    for (int i = 0; i < array.length; i++) {
                        array[i] = r.nextInt(100);
                    }

                    long start = System.nanoTime();
                    qsort.sort(array);
                    testDuration = System.nanoTime() - start;
                    sumOfTestDurations += testDuration;

                    for (int i = array.length - 1; i > 0; i--) {
                        assertTrue(array[i] >= array[i - 1]);
                    }
                }

                numOfTestsPerArraySize = 10;
                averageTestDuration = sumOfTestDurations / numOfTestsPerArraySize;

                file.append(arraySize + "   " + averageTestDuration + "\n");

                arraySize += 50;
            }

            file.close();

        } catch (IOException exception) {
            System.out.println("Unable to write to file.");
        }
    }

}
