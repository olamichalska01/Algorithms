package pl.edu.pw.ee;

import java.io.*;
import java.util.ArrayList;

public class TimeMeasurements {
    private HashListChaining<String> hashListChaining;
    private ArrayList<String> array;
    private long[] timeArray;

    private void putToArrayFromFile() {
        File file = new File("src\\test\\java\\pl\\edu\\pw\\ee\\files\\words.txt");
        array = new ArrayList<>();

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (int i = 0; i < words.length; i++) {
                    array.add(words[i]);
                }
            }

            br.close();

        } catch (IOException e) {
            System.err.println("File not found!");
        }
    }

    private long averageTime() {
        long avg = 0;
        long sum = 0;

        for (int i = 10; i < 20; i++) {
            sum += timeArray[i];
        }

        avg = sum / 10;

        return avg;
    }

    public long measureTime(int hashSize) {
        timeArray = new long[30];

        putToArrayFromFile();

        for (int i = 0; i < timeArray.length; i++) {
            hashListChaining = new HashListChaining<>(hashSize);

            for (String s : array) {
                hashListChaining.add(s);
            }

            long start = System.nanoTime();

            for (String s : array) {
                hashListChaining.get(s);
            }

            long end = System.nanoTime();
            long time = end - start;

            timeArray[i] = time;
        }

        sort(timeArray);

        return averageTime();
    }

    private void sort(long[] array) {
        int i, j, minIndex;
        long minVal;

        for (i = 0; i < array.length - 1; i++) {
            minVal = array[i];
            minIndex = i;

            for (j = i + 1; j < array.length; j++) {
                if (array[j] < minVal) {
                    minVal = array[j];
                    minIndex = j;
                }
            }

            array[minIndex] = array[i];
            array[i] = minVal;
        }
    }
}
