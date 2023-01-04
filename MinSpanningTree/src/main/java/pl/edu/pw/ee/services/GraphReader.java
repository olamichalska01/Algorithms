package pl.edu.pw.ee.services;

import java.io.*;
import java.util.ArrayList;

public class GraphReader {
    private static ArrayList<String> vertices;

    public static void readGraphFromFile(String fileName, ArrayList<String[]> graph) {
        vertices = new ArrayList<>();
        File file = new File("src\\test\\java\\pl\\edu\\pw\\ee\\files\\" + fileName);

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int lineNo = 0;

            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");

                checkIfConnectionIsCorrect(words, graph);

                String start = words[0];
                String end = words[1];
                String value = words[2];
                String[] connection = { start, end, value };

                graph.add(lineNo, connection);

                addVertexIfNew(vertices, start);
                addVertexIfNew(vertices, end);

                lineNo++;
            }

            br.close();

        } catch (IOException e) {
            System.err.println("File not found!");
        }
    }

    private static void addVertexIfNew(ArrayList<String> vertices, String vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
        }
    }

    public static int countNumOfVertices() {
        return vertices.size();
    }

    private static void checkIfConnectionIsCorrect(String[] words, ArrayList<String[]> graph) {
        if (words.length != 3) {
            throw new IllegalArgumentException(
                    "Incorrect data in input file! Each row must contain exactly 3 values and cannot start with a whitespace character.");
        }

        if (Double.parseDouble(words[2]) % 1 != 0 || Double.parseDouble(words[2]) < 0) {
            throw new IllegalArgumentException(
                    "Incorrect data in input file! Connection value must be an integer and grater than 0.");
        }

        if (words.length == 3) {
            for (String[] connection : graph) {
                if (connection[0].equals(words[0])) {
                    if (connection[1].equals(words[1])) {
                        throw new IllegalArgumentException(
                                "Incorrect data in input file! More than one connection between two vertices is not allowed.");
                    }
                }
            }
        }

        if (words[0].equals(words[1])) {
            throw new IllegalArgumentException("Incorrect data in input file! Cannot connect vertex to itself.");
        }

    }

}
