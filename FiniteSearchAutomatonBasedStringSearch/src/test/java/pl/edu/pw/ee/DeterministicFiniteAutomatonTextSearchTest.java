package pl.edu.pw.ee;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DeterministicFiniteAutomatonTextSearchTest {
    DeterministicFiniteAutomatonTextSearch dfa;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private void testForFindFirstMethod(String text, String pattern, int expected) {
        dfa = new DeterministicFiniteAutomatonTextSearch(pattern);

        int actual = dfa.findFirst(text);

        assertEquals(expected, actual);
    }

    private void testForFindAllMethod(String text, String pattern, int[] expected) {
        dfa = new DeterministicFiniteAutomatonTextSearch(pattern);

        int[] actual = dfa.findAll(text);

        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    public void shouldThrowException_When_PatternIsAnEmptyString() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Pattern cannot be an empty string!");

        dfa = new DeterministicFiniteAutomatonTextSearch("");
    }

    @Test
    public void shouldThrowException_When_PatternIsNull() {
        String nullPattern = null;

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Pattern cannot be null!");

        dfa = new DeterministicFiniteAutomatonTextSearch(nullPattern);
    }

    @Test
    public void shouldThrowException_When_TextForSearchingIsNull_InFindFirstMethod() {
        String nullText = null;
        dfa = new DeterministicFiniteAutomatonTextSearch("pattern");

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Input text cannot be null!");

        dfa.findFirst(nullText);
    }

    @Test
    public void shouldThrowException_When_TextForSearchingIsNull_InFindAllMethod() {
        String nullText = null;
        dfa = new DeterministicFiniteAutomatonTextSearch("pattern");

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Input text cannot be null!");

        dfa.findAll(nullText);
    }

    @Test
    public void shouldReturnMinusOne_When_NoMatchInText_ByFindFirstMethod() {
        testForFindFirstMethod("some text", "pattern", -1);
    }

    @Test
    public void shouldReturnMinusOne_When_TextIsEmptyString_ByFindFirstMethod() {
        testForFindFirstMethod("", "pattern", -1);
    }

    @Test
    public void shouldReturnIndex_When_OneMatchInText_ByFindFirstMethod() {
        testForFindFirstMethod("some text", "me", 2);
    }

    @Test
    public void shouldReturnFirstFoundIndex_When_TwoMatchesInText_ByFindFirstMethod() {
        testForFindFirstMethod("first match, second match", "match", 6);
    }

    @Test
    public void shouldReturnFirstFoundIndex_When_ThreeMatchesInText_ByFindFirstMethod() {
        testForFindFirstMethod("first match, second match", "match", 6);
    }

    @Test
    public void shouldReturnEmptyArray_When_NoMatchInText_ByFindAllMethod() {
        int[] expected = {};

        testForFindAllMethod("first, second, last", "match", expected);
    }

    @Test
    public void shouldReturnOneElemArray_When_OneMatchInText_ByFindAllMethod() {
        int[] expected = { 6 };

        testForFindAllMethod("first match, second, last", "match", expected);
    }

    @Test
    public void shouldReturnTwoElemArray_When_TwoNotOverlapingMatchesInText_ByFindAllMethod() {
        int[] expected = { 6, 20 };

        testForFindAllMethod("first match, second match, last", "match", expected);
    }

    @Test
    public void shouldReturnTwoElemArray_When_TwoOverlapingMatchesInText_ByFindAllMethod() {
        int[] expected = { 1, 3 };

        testForFindAllMethod("banana", "ana", expected);
    }

    @Test
    public void shouldReturnThreeElemArray_When_TwoMatchesAreOverlapingAndOneIsNotInText_ByFindAllMethod() {
        int[] expected = { 1, 3, 7 };

        testForFindAllMethod("banana ana", "ana", expected);
    }

    @Test
    public void shouldReturnEmptyArray_When_TextIsEmptyString_ByFindAllMethod() {
        int[] expected = {};

        testForFindAllMethod("", "ana", expected);
    }
}
