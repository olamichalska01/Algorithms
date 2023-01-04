package pl.edu.pw.ee;

import java.util.ArrayList;

import pl.edu.pw.ee.exceptions.ConnectivityNotPreservedException;
import pl.edu.pw.ee.services.GraphReader;
import pl.edu.pw.ee.services.GraphUtils;
import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithm implements MinSpanningTree {
    private ArrayList<String[]> graph;
    private ArrayList<String[]> minSpanningTree;
    private ArrayList<String> visitedVertices;

    public String findMST(String pathToFile) {
        graph = new ArrayList<>();
        minSpanningTree = new ArrayList<>();
        visitedVertices = new ArrayList<>();

        GraphReader.readGraphFromFile(pathToFile, graph);
        int numOfVertices = GraphReader.countNumOfVertices();

        String startVertex = selectStartForMST();
        String[] initializingConnection = { startVertex, startVertex, "0" };

        visitedVertices.add(startVertex);
        minSpanningTree.add(initializingConnection);

        while (visitedVertices.size() < numOfVertices) {
            String[] lowestConnection = findLowestConnection();

            if (lowestConnection == null)
                break;

            minSpanningTree.add(lowestConnection);
        }

        minSpanningTree.remove(initializingConnection);

        checkIfConnectivityPreserved(numOfVertices);

        return GraphUtils.minSpanningTreeString(minSpanningTree);
    }

    private String[] findLowestConnection() {
        int lowestConnectionValue = Integer.MAX_VALUE;
        String[] lowestConnection = null;

        for (String[] connection : graph) {
            if (!minSpanningTree.contains(connection) && areBothVerticesInMSP(connection[0], connection[1])
                    && Integer.parseInt(connection[2]) < lowestConnectionValue) {
                lowestConnectionValue = Integer.parseInt(connection[2]);
                lowestConnection = connection;
            }

        }

        if (lowestConnection != null) {
            markVertexAsVisited(lowestConnection);
        } else {
            return null;
        }

        return lowestConnection;
    }

    private String selectStartForMST() {
        String[] firstConnection = graph.get(0);

        return firstConnection[0];
    }

    private boolean areBothVerticesInMSP(String vertexOne, String vertexTwo) {
        boolean isVertexOneVisited = visitedVertices.contains(vertexOne);
        boolean isVertexTwoVisited = visitedVertices.contains(vertexTwo);

        return (isVertexOneVisited && !isVertexTwoVisited) || (!isVertexOneVisited && isVertexTwoVisited);
    }

    private void markVertexAsVisited(String[] lowestConnection) {
        if (!visitedVertices.contains(lowestConnection[1])) {
            visitedVertices.add(lowestConnection[1]);
        }

        if (!visitedVertices.contains(lowestConnection[0])) {
            visitedVertices.add(lowestConnection[0]);
        }
    }

    private void checkIfConnectivityPreserved(int numOfVertices) {
        if (numOfVertices != visitedVertices.size()) {
            throw new ConnectivityNotPreservedException("Connectivity in input graph is not preserved!");
        }
    }
}
