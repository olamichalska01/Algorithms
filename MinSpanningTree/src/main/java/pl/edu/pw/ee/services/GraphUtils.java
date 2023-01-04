package pl.edu.pw.ee.services;

import java.util.ArrayList;

public class GraphUtils {

    public static String minSpanningTreeString(ArrayList<String[]> minSpanningTree) {
        String MSP = "";

        for (String[] connection : minSpanningTree) {
            MSP += " " + connection[0] + "_" + connection[2] + "_" + connection[1] + " |";
        }

        MSP = MSP.substring(1, MSP.length() - 2);

        return MSP;
    }

    public static void sortGraph(ArrayList<String[]> graph) {
        int i, j, minIndex;
        int minVal;

        for (i = 0; i < graph.size() - 1; i++) {
            String[] connectionI = graph.get(i);
            minVal = Integer.parseInt(connectionI[2]);
            minIndex = i;

            for (j = i + 1; j < graph.size(); j++) {
                String[] connectionJ = graph.get(j);

                if (Integer.parseInt(connectionJ[2]) < minVal) {
                    minVal = Integer.parseInt(connectionJ[2]);
                    minIndex = j;
                }
            }

            String[] tmp = graph.get(minIndex);
            graph.set(minIndex, graph.get(i));
            graph.set(i, tmp);
        }
    }

    public static void createNewSubTree(String[] connectionToAdd, ArrayList<ArrayList<String[]>> subTreesArray) {
        ArrayList<String[]> newSubTree = new ArrayList<>();
        newSubTree.add(connectionToAdd);
        subTreesArray.add(newSubTree);
    }

    public static int checkIfConnectionIsUnnecessary(String[] connection,
            ArrayList<ArrayList<String[]>> subTreesArray) {
        for (int i = 0; i < subTreesArray.size(); i++) {
            ArrayList<String[]> subTree = subTreesArray.get(i);
            ArrayList<String> subTreeVeritces = verticesInSubTree(subTree);

            if (subTreeVeritces.contains(connection[0]) && subTreeVeritces.contains(connection[1])) {
                return 1;
            }
        }

        return 0;
    }

    public static void addConnectionToSubTree(String[] connection,
            ArrayList<ArrayList<String[]>> subTreesArray) {
        ArrayList<ArrayList<String[]>> subTreesToJoin = new ArrayList<>();

        for (int i = 0; i < subTreesArray.size(); i++) {
            ArrayList<String[]> subTree = subTreesArray.get(i);
            ArrayList<String> subTreeVeritces = verticesInSubTree(subTree);

            if (subTreeVeritces.contains(connection[0]) || subTreeVeritces.contains(connection[1])) {
                subTreesToJoin.add(subTree);
            }
        }

        if (subTreesToJoin.size() == 2) {
            ArrayList<String[]> joined = joinSubTrees(subTreesToJoin, connection);
            int index = subTreesArray.indexOf(subTreesToJoin.get(0));
            subTreesArray.add(index, joined);
            subTreesArray.remove(subTreesToJoin.get(0));
            subTreesArray.remove(subTreesToJoin.get(1));
        }

        if (subTreesToJoin.size() == 1) {
            ArrayList<String[]> subTreeWithConnection = subTreesToJoin.get(0);
            int index = subTreesArray.indexOf(subTreesToJoin.get(0));
            subTreeWithConnection.add(connection);
            subTreesArray.add(index, subTreeWithConnection);
            subTreesArray.remove(subTreesToJoin.get(0));
        }

        if (subTreesToJoin.size() == 0) {
            createNewSubTree(connection, subTreesArray);
        }
    }

    private static ArrayList<String[]> joinSubTrees(ArrayList<ArrayList<String[]>> subTreesToJoin,
            String[] joiningConnection) {
        ArrayList<String[]> joined = new ArrayList<>();

        ArrayList<String[]> firstSubTree = subTreesToJoin.get(0);
        ArrayList<String[]> secondSubTree = subTreesToJoin.get(1);

        for (int i = 0; i < firstSubTree.size(); i++) {
            joined.add(firstSubTree.get(i));
        }

        for (int i = 0; i < secondSubTree.size(); i++) {
            if (!joined.contains(secondSubTree.get(i))) {
                joined.add(secondSubTree.get(i));
            }
        }

        joined.add(joiningConnection);

        return joined;
    }

    public static ArrayList<String[]> checkIfMSPCreated(ArrayList<ArrayList<String[]>> subTreesArray,
            int numOfVertices) {
        for (int i = 0; i < subTreesArray.size(); i++) {
            ArrayList<String[]> subTree = subTreesArray.get(i);

            if (countVerticesInSubTree(subTree) == numOfVertices) {
                return subTree;
            }
        }

        return null;
    }

    private static int countVerticesInSubTree(ArrayList<String[]> subTree) {
        ArrayList<String> subTreeVeritces = verticesInSubTree(subTree);
        int verticesCount = subTreeVeritces.size();

        return verticesCount;
    }

    private static ArrayList<String> verticesInSubTree(ArrayList<String[]> subTree) {
        ArrayList<String> subTreeVeritces = new ArrayList<>();

        for (int i = 0; i < subTree.size(); i++) {
            String[] subTreeConnection = subTree.get(i);

            if (!subTreeVeritces.contains(subTreeConnection[0])) {
                subTreeVeritces.add(subTreeConnection[0]);
            }

            if (!subTreeVeritces.contains(subTreeConnection[1])) {
                subTreeVeritces.add(subTreeConnection[1]);
            }
        }

        return subTreeVeritces;
    }

}
