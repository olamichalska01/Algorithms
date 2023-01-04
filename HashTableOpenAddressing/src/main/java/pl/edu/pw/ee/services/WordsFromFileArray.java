package pl.edu.pw.ee.services;

import java.io.*;
import java.util.ArrayList;

public class WordsFromFileArray extends ArrayList<String> {
    private String fileName;

    public WordsFromFileArray(String fileName) {
        this.fileName = fileName;
        readFromFile();
    }

    private void readFromFile() {
        String f = "src\\test\\java\\pl\\edu\\pw\\ee\\files\\" + fileName;
        File file = new File(f);

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (int i = 0; i < words.length; i++) {
                    add(words[i]);
                }
            }

            br.close();

        } catch (IOException e) {
            System.out.println(f);
            System.err.println("File not found!");
        }
    }
    
}
