package pl.edu.pw.ee;

import java.io.File;

import org.junit.Test;

import pl.edu.pw.ee.services.*;

public class TimeMeasurements {
    private HashOpenAdressing<String> hashOpenAdressing;
    private WordsFromFileArray words = new WordsFromFileArray("words.txt");

    @Test
    public void putTest() { 
        String[] files = new String[10];
        putElemsAndMeasureTime(AdressingType.LINEAR, "put_linear.txt", 0); 
        putElemsAndMeasureTime(AdressingType.DOUBLE, "put_double.txt", 0); 

        for(int i = 0; i < 10; i++) {
            putElemsAndMeasureTime(AdressingType.QUADRATIC, "quadraticData\\put_quadratic" + (i + 1) + ".txt", i + 1);
            files[i] = "src\\test\\java\\pl\\edu\\pw\\ee\\files\\quadraticData\\put_quadratic" + (i + 1) + ".txt";
        }

       Utils.mergeFiles(files, new File("src\\test\\java\\pl\\edu\\pw\\ee\\files\\put_quadratic_all.txt"));

    }

    @Test
    public void getTest() {
        String[] files = new String[10];
        getElemsAndMeasureTime(AdressingType.LINEAR, "get_linear.txt", 0);
        getElemsAndMeasureTime(AdressingType.DOUBLE, "get_double.txt", 0);

        for(int i = 0; i < 10; i++) {
            getElemsAndMeasureTime(AdressingType.QUADRATIC, "quadraticData\\get_quadratic" + (i + 1) + ".txt", i + 1);
            files[i] = "src\\test\\java\\pl\\edu\\pw\\ee\\files\\quadraticData\\get_quadratic" + (i + 1) + ".txt";
        }

        Utils.mergeFiles(files, new File("src\\test\\java\\pl\\edu\\pw\\ee\\files\\get_quadratic_all.txt"));
    }


    private void putElemsAndMeasureTime(AdressingType which, String filename, int coefficient) {
        long[] timeArray;
        long[] results = new long[10];
        long start, end, time;
        int i, j, k, hashSize;

        for (i = 9; i < 19; i++) { 
            timeArray = new long[30];
            hashSize = (int) Math.pow(2, i);

            for (j = 0; j < 30; j++) {
                chooseTypeOfAdressing(which, hashSize, coefficient);

                start = System.nanoTime();

                for (k = 0; k < words.size(); k++) {
                    hashOpenAdressing.put(words.get(k));
                }

                end = System.nanoTime();
                time = end - start;

                timeArray[j] = time;
            }

            results[i - 9] = Utils.countFinalResult(timeArray);
        }
       

        Utils.writeResultsToFile(filename, results);
    }

    private void getElemsAndMeasureTime(AdressingType which, String filename, int coefficient) {
        long[] timeArray;
        long[] results = new long[10];
        long start, end, time;
        int i, j, k, hashSize;

        for (i = 9; i < 19; i++) {
            timeArray = new long[30];
            hashSize = (int) Math.pow(2, i);

            chooseTypeOfAdressing(which, hashSize, coefficient);

            for (k = 0; k < words.size(); k++) {
                hashOpenAdressing.put(words.get(k));
            }

            for (j = 0; j < 30; j++) {
                start = System.nanoTime();

                for (k = 0; k < words.size(); k++) {
                    hashOpenAdressing.get(words.get(k));
                }

                end = System.nanoTime();
                time = end - start;

                timeArray[j] = time;
            }

            results[i - 9] = Utils.countFinalResult(timeArray);
        }

        Utils.writeResultsToFile(filename, results);
    }

    private void chooseTypeOfAdressing(AdressingType which, int hashSize, int coefficient) {
        switch (which) {
        case LINEAR:
            hashOpenAdressing = new HashLinearProbing<String>(hashSize);
            break;

        case DOUBLE:
            hashOpenAdressing = new HashDoubleHashing<String>(hashSize);
            break;

        case QUADRATIC:
            hashOpenAdressing = new HashQuadraticProbing<String>(hashSize, coefficient, coefficient + 13);
            break;
        }
    }

}
