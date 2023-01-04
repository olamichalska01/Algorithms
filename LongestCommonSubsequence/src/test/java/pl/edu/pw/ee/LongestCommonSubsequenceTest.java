package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LongestCommonSubsequenceTest {
    public LongestCommonSubsequence lcs;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldThrowException_ifOneOfInputStringsIsNull() {
        String firstStr = null;
        String secondStr = "not_null";

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Input string cannot be null!");

        lcs = new LongestCommonSubsequence(firstStr, secondStr);
    }

    @Test
    public void shouldReturnEmptyString_ifInputStringsHaveNoLCS() {
        String firstStr = "we_have_nothing_in_common";
        String secondStr = ":)";

        lcs = new LongestCommonSubsequence(firstStr, secondStr);

        String expected = "";
        String actual = lcs.findLCS();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnOneLetter_ifInputStringsHaveOneLetterInCommon() {
        String firstStr = "we_have_very_little_in_common";
        String secondStr = "e";

        lcs = new LongestCommonSubsequence(firstStr, secondStr);

        String expected = "e";
        String actual = lcs.findLCS();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnWholeInputString_ifInputStringsAreIdentical() {
        String firstStr = "we_are_the_same";
        String secondStr = "we_are_the_same";

        lcs = new LongestCommonSubsequence(firstStr, secondStr);

        String expected = "we_are_the_same";
        String actual = lcs.findLCS();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnLCS_ifInputStringsHaveASpecialCharInCommon() {
        String firstStr = "i_have\na_special_character";
        String secondStr = "wow\n";

        lcs = new LongestCommonSubsequence(firstStr, secondStr);

        String expected = "\n";
        String actual = lcs.findLCS();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnLongerSequence_ifInputStringsHaveTwoCommonSequences() {
        String firstStr = "abcd_efg";
        String secondStr = "abcd-abc";

        lcs = new LongestCommonSubsequence(firstStr, secondStr);

        String expected = "abcd";
        String actual = lcs.findLCS();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnLCS_ifStringsContainWhiteSpaceCharacter() {
        String firstStr = "abcd yefg";
        String secondStr = "abcd yabc";

        lcs = new LongestCommonSubsequence(firstStr, secondStr);

        String expected = "abcd y";
        String actual = lcs.findLCS();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldRetrunSecondLCSFromFirstSequence_ifStringsHaveTwoLCS() {
        String firstStr = "cdab";
        String secondStr = "abcd yefg";

        lcs = new LongestCommonSubsequence(firstStr, secondStr);

        String expected = "ab";
        String actual = lcs.findLCS();

        assertEquals(expected, actual);
    }
}
