package pl.edu.pw.ee;

import java.util.ArrayList;

import pl.edu.pw.ee.exceptions.ConnectivityNotPreservedException;
import pl.edu.pw.ee.services.GraphReader;
import pl.edu.pw.ee.services.MinSpanningTree;
import pl.edu.pw.ee.services.GraphUtils;

public class KruskalAlgorithm implements MinSpanningTree {
    private ArrayList<String[]> graph;
    private ArrayList<String[]> minSpanningTree;
    private ArrayList<ArrayList<String[]>> subTrees;

    @Override
    public String findMST(String pathToFile) {
        graph = new ArrayList<>();
        minSpanningTree = new ArrayList<>();
        subTrees = new ArrayList<>();

        GraphReader.readGraphFromFile(pathToFile, graph);
        int numOfVertices = GraphReader.countNumOfVertices();

        GraphUtils.sortGraph(graph);

        GraphUtils.createNewSubTree(graph.get(0), subTrees);

        for (int i = 0; i < graph.size(); i++) {
            String[] connection = graph.get(i);

            minSpanningTree = GraphUtils.checkIfMSPCreated(subTrees, numOfVertices);

            if (GraphUtils.checkIfConnectionIsUnnecessary(connection, subTrees) == 1) {
                continue;
            }

            GraphUtils.addConnectionToSubTree(connection, subTrees);

            if (minSpanningTree != null) {
                break;
            }
        }

        minSpanningTree = GraphUtils.checkIfMSPCreated(subTrees, numOfVertices);

        checkIfConnectivityPreserved(minSpanningTree, numOfVertices);

        return GraphUtils.minSpanningTreeString(minSpanningTree);
    }

    private void checkIfConnectivityPreserved(ArrayList<String[]> minSpanningTree, int numOfVertices) {
        if (minSpanningTree == null) {
            throw new ConnectivityNotPreservedException("Connectivity in input graph is not preserved!");
        }
    }

}
