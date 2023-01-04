package pl.edu.pw.ee;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FileWriter f;
        try {
            f = new FileWriter("src\\test\\java\\pl\\edu\\pw\\ee\\files\\time_of_size.txt");

            for (int i = 0; i < 7; i++) {
                TimeMeasurements tm = new TimeMeasurements();

                int hashSize = (int) Math.pow(2, i + 12);
                long time = tm.measureTime(hashSize);

                f.append(hashSize + "        " + time + "\n");
            }

            f.close();
        } catch (IOException e) {
            System.err.println("Couldn't write to file.");
        }

    }
}
