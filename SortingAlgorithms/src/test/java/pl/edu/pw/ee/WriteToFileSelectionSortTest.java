package pl.edu.pw.ee;

import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

public class WriteToFileSelectionSortTest {
    Sorting selSort;

    @Before
    public void setUp() {
        selSort = new SelectionSort();
    }

    @Test
    public void Should_Sort_When_ArrayWithElemsInAscendingOrder() {
        // For selection sort, best and worst case scenario are the same.
        // For this test, let's assume that optymistic data
        // is an array with elements in ascending order.

        double[] array;
        FileWriter file;

        Random r = new Random(65);
        int arraySize = 50;
        int numOfTests = 50;
        int numOfTestsPerArraySize = 10;
        long testDuration, sumOfTestDurations = 0;
        long averageTestDuration;

        try {
            file = new FileWriter("src\\test\\java\\pl\\edu\\pw\\ee\\SelectionSortFiles\\optymisticData_SelectionSort.txt");

            while (numOfTests-- > 0) {

                while (numOfTestsPerArraySize-- > 0) {
                    array = new double[arraySize];
                    array[0] = r.nextInt(100);

                    for (int i = 1; i < array.length; i++) {
                        array[i] = array[i - 1] + r.nextInt(100);
                    }

                    long start = System.nanoTime();
                    selSort.sort(array);
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
        // For selection sort, best and worst case scenario are the same.
        // For this test, let's assume that pessymistic data
        // is an array with elements in descending order.

        double[] array;
        FileWriter file;

        Random r = new Random(34);
        int arraySize = 50;
        int numOfTests = 50;
        int numOfTestsPerArraySize = 10;
        long testDuration, sumOfTestDurations = 0;
        long averageTestDuration;

        try {
            file = new FileWriter("src\\test\\java\\pl\\edu\\pw\\ee\\SelectionSortFiles\\pessymisticData_SelectionSort.txt");

            while (numOfTests-- > 0) {

                while (numOfTestsPerArraySize-- > 0) {
                    array = new double[arraySize];
                    array[0] = r.nextInt(100);

                    for (int i = 1; i < array.length; i++) {
                        array[i] = array[i - 1] - r.nextInt(10);
                    }

                    long start = System.nanoTime();
                    selSort.sort(array);
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

        Random r = new Random(16);
        int arraySize = 50;
        int numOfTests = 50;
        int numOfTestsPerArraySize = 10;
        long testDuration, sumOfTestDurations = 0;
        long averageTestDuration;

        try {
            file = new FileWriter("src\\test\\java\\pl\\edu\\pw\\ee\\SelectionSortFiles\\randomData_SelectionSort.txt");

            while (numOfTests-- > 0) {

                while (numOfTestsPerArraySize-- > 0) {
                    array = new double[arraySize];

                    for (int i = 0; i < array.length; i++) {
                        array[i] = r.nextInt(100);
                    }

                    long start = System.nanoTime();
                    selSort.sort(array);
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
