package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PrimAlgorithmTest {
    private PrimAlgorithm primAlgorithm;

    @Before
    public void setUp() {
        primAlgorithm = new PrimAlgorithm();
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private void checkResult(String pathToFileWithGraph, String result) {
        String actual = primAlgorithm.findMST(pathToFileWithGraph);
        String expected = result;

        assertEquals(expected, actual);
    }

    private void checkForException(String pathToFileWithGraph, String message) {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(message);
        primAlgorithm.findMST(pathToFileWithGraph);
    }

    @Test
    public void shouldThrowException_When_InputFileLineStartsWithWhitespaceChar() {
        checkForException("whitespace.txt",
                "Incorrect data in input file! Each row must contain exactly 3 values and cannot start with a whitespace character.");
    }

    @Test
    public void shouldThrowException_When_InputFileLineHasMoreThanThreeValues() {
        checkForException("more_values_in_line.txt",
                "Incorrect data in input file! Each row must contain exactly 3 values and cannot start with a whitespace character.");
    }

    @Test
    public void shouldThrowException_When_InputFileLineHasLessThanThreeValues() {
        checkForException("less_values_in_line.txt",
                "Incorrect data in input file! Each row must contain exactly 3 values and cannot start with a whitespace character.");
    }

    @Test
    public void shouldThrowException_When_InputFileConnectionValueIsLessThanZero() {
        checkForException("connection_less_than_zero.txt",
                "Incorrect data in input file! Connection value must be an integer and grater than 0.");
    }

    @Test
    public void shouldThrowException_When_InputFileConnectionValueIsNotAnInteger() {
        checkForException("connection_not_int.txt",
                "Incorrect data in input file! Connection value must be an integer and grater than 0.");
    }

    @Test
    public void shouldThrowException_When_InputFileHasMoreThanOneConnectionForTwoVertices() {
        checkForException("double_connection_for_vertices.txt",
                "Incorrect data in input file! More than one connection between two vertices is not allowed.");
    }

    @Test
    public void shouldThrowException_When_VertexConnectedToItself() {
        checkForException("self_connection.txt",
                "Incorrect data in input file! Cannot connect vertex to itself.");
    }

    @Test
    public void shouldReturnWholeGraph_When_GraphHasOneConnection() {
        checkResult("one_connection.txt", "A_1_B");
    }

    @Test
    public void shouldReturnWholeGraph_When_GraphHasTwoConnectionsAndThreeVertices() {
        checkResult("two_connections_three_vertices.txt", "A_7_B | B_3_C");
    }

    @Test
    public void shouldReturnTwoConnections_When_GraphHasThreeConnectionsAndThreeVertices() {
        checkResult("three_connections_three_vertices.txt", "A_0_C | B_2_C");
    }

    @Test
    public void shouldReturnCorrectMSP_When_GraphHasAllConnectionsWithTheSameValues() {
        checkResult("same_connection_values.txt", "E_1_A | E_1_B | A_1_C | A_1_D");
    }

    @Test
    public void shouldReturnCorrectMSP_When_ConnectivityInGraphIsPreserved() {
        checkResult("connectivity_preserved.txt", "E_3_A | A_5_B | B_1_F | E_6_G | H_4_G | A_9_C | D_7_C");
    }

    @Test
    public void shouldThrowException_When_ConnectivityInGraphIsNotPreserved() {
        checkForException("connectivity_not_preserved.txt", "Connectivity in input graph is not preserved!");
    }

}
