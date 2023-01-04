package pl.edu.pw.ee.services;

import java.io.*;

public class Utils {

    public static void writeResultsToFile(String fileName, long[] timeArray) {
        FileWriter f;
        try {
            f = new FileWriter("src\\test\\java\\pl\\edu\\pw\\ee\\files\\" + fileName);

            for (int i = 0; i < 10; i++) {
                int hashSize = (int) Math.pow(2, i + 9);
                f.append(hashSize + "        " + timeArray[i] + "\n");
            }

            f.close();
        } catch (IOException e) {
            System.err.println("Couldn't write to file.");
        }
    }

    public static long countFinalResult(long[] array) {
        long sum = 0;

        sort(array);

        for (int i = 10; i < 20; i++) {
            sum += array[i];
        }

        return sum / 10;
    }

    public static void sort(long[] array) {
        int i, j, minIndex;
        long minVal;

        for (i = 0; i < array.length - 1; i++) {
            minIndex = i;
            minVal = array[i];

            for (j = i + 1; j < array.length; j++) {
                if (array[j] <= minVal) {
                    minVal = array[j];
                    minIndex = j;
                }
            }

            array[minIndex] = array[i];
            array[i] = minVal;
        }
    }

    public static void mergeFiles(String[] files, File outFile) {
        try {
            OutputStream out = new FileOutputStream(outFile);
            byte[] buf = new byte[1000];
            for (String file : files) {
                InputStream in = new FileInputStream(file);
                int b = 0;
                while ((b = in.read(buf)) >= 0) 
                    out.write(buf, 0, b);
                in.close();
            }
            out.close();
        } catch (IOException e) {
            System.err.println("Couldn't write to file!");
        }
    }

}
