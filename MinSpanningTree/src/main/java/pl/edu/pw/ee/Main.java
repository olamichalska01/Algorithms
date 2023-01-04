package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.Arrays;

import pl.edu.pw.ee.services.GraphUtils;

public class Main {
    public static void main(String[] args) {
        PrimAlgorithm p = new PrimAlgorithm();
        System.out.println(p.findMST("one_connection.txt"));
        System.out.println(p.findMST("a.txt"));
        // gr.readGraphFromFile("small_data.txt", graph);
        KruskalAlgorithm k = new KruskalAlgorithm();
        System.out.println(k.findMST("a.txt"));

        // ArrayList<ArrayList<String[]>> subTreesArray = new ArrayList<>();

        // ArrayList<String[]> subTree1 = new ArrayList<>();
        // ArrayList<String[]> subTree2 = new ArrayList<>();

        // String[] subTree1Conn1 = { "A", "B", "1" };
        // String[] subTree1Conn2 = { "A", "C", "12" };
        // String[] subTree2Conn1 = { "E", "D", "13" };
        // String[] joining = { "C", "D", "5" };

        // subTree1.add(subTree1Conn2);
        // subTree1.add(subTree1Conn1);
        // subTree2.add(subTree2Conn1);
        // subTreesArray.add(subTree2);
        // subTreesArray.add(subTree1);

        // String[] conn = { "B", "E", "12" };

        // System.out.println("++++++" + GraphUtils.checkIfConnectionIsUnnecessary(conn,
        // subTreesArray));

        // for (ArrayList<String[]> subTree : subTreesArray) {
        // for (String[] con : subTree) {
        // System.out.println(Arrays.toString(con));
        // }
        // System.out.println("----");
        // }

        // GraphUtils.addConnectionToSubTree(conn, subTreesArray);

        // System.out.println("}}}}}}}}}}");

        // for (ArrayList<String[]> subTree : subTreesArray) {
        // for (String[] con : subTree) {
        // System.out.println(Arrays.toString(con));
        // }
        // System.out.println("----");
        // }

        // System.out.println("???? " + GraphUtils.verticesInSubTree(subTree1));

        // ArrayList<String[]> msp = GraphUtils.checkIfMSPCreated(subTreesArray, 3);

        // System.out.println("* " + GraphUtils.minSpanningTreeString(msp));
    }
}
