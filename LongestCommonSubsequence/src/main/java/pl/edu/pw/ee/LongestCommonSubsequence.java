package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Utils;

class LongestCommonSubsequence {
    private char[] firstSequence;
    private char[] secondSequence;
    private char[][] symbolsArray;
    private int[][] valuesArray;
    private char[][] lcsPath;
    private final String TEXT_PATH = "\u001B[32m";
    private final String TEXT_RESET = "\u001B[0m";
    private final String TEXT_LCS = "\u001B[33m";

    public LongestCommonSubsequence(String firstStr, String secondStr) {
        checkForNullStringInput(firstStr);
        checkForNullStringInput(secondStr);

        firstSequence = firstStr.toCharArray();
        secondSequence = secondStr.toCharArray();
        valuesArray = new int[secondSequence.length + 1][firstSequence.length + 1];
        symbolsArray = new char[secondSequence.length + 2][firstSequence.length + 2];
        lcsPath = new char[secondSequence.length + 2][firstSequence.length + 2];

        Utils.setUpFrame(symbolsArray, valuesArray, firstSequence, secondSequence);
        calculateValues();
    }

    public String findLCS() {
        String lcs = "";
        String finalLCS = "";
        int j = firstSequence.length + 1;
        int i = secondSequence.length + 1;

        while (j > 0 && i > 0) {
            if (symbolsArray[i][j] == '\\') {
                lcsPath[i][j] = symbolsArray[i][j];
                lcs += firstSequence[j - 2];
                i--;
                j--;
            } else if (symbolsArray[i][j] == '^') {
                lcsPath[i][j] = symbolsArray[i][j];
                i--;
            } else if (symbolsArray[i][j] == '<') {
                lcsPath[i][j] = symbolsArray[i][j];
                j--;
            } else
                break;
        }

        for (int c = 0; c < lcs.length(); c++) {
            char ch = lcs.charAt(c);
            finalLCS = ch + finalLCS;
        }

        return finalLCS;
    }

    private void calculateValues() {
        for (int j = 0; j < firstSequence.length; j++) {
            for (int i = 0; i < secondSequence.length; i++) {
                if (firstSequence[j] == secondSequence[i]) {
                    valuesArray[i + 1][j + 1] = valuesArray[i][j] + 1;
                    symbolsArray[i + 2][j + 2] = '\\';
                } else {
                    valuesArray[i + 1][j + 1] = maxNeighbour(i + 1, j + 1);
                }
            }
        }
    }

    private int maxNeighbour(int i, int j) {
        int maxNeighbour = valuesArray[i - 1][j];
        int leftNeighbour = valuesArray[i][j - 1];

        maxNeighbour = maxNeighbour < leftNeighbour ? leftNeighbour : maxNeighbour;

        if (maxNeighbour == valuesArray[i - 1][j]) {
            symbolsArray[i + 1][j + 1] = '^';
        } else {
            symbolsArray[i + 1][j + 1] = '<';
        }

        return maxNeighbour;
    }

    public void display() {

        System.out.print("LCS: " + findLCS());

        Utils.printHorizontalFrame(firstSequence);

        for (int i = 0; i < secondSequence.length + 2; i++) {
            System.out.print("|");
            for (int top = 0; top < firstSequence.length + 2; top++) {
                if (lcsPath[i][top] == '^') {
                    System.out.print(TEXT_PATH + "   ^" + TEXT_RESET + "   |");
                } else if (lcsPath[i][top] == '\\') {
                    System.out.print(TEXT_LCS + "\\" + TEXT_RESET + "      |");
                } else {
                    System.out.print("       |");
                }
            }

            System.out.print("\n|");
            for (int j = 0; j < firstSequence.length + 2; j++) {
                if (i > 1 && j > 1) {
                    if (lcsPath[i][j] == '<') {
                        System.out.print(TEXT_PATH + "<  " + valuesArray[i - 1][j - 1] + TEXT_RESET + "   |");
                    } else if (lcsPath[i][j] == '^') {
                        System.out.print("   " + TEXT_PATH + valuesArray[i - 1][j - 1] + TEXT_RESET + "   |");
                    } else if (lcsPath[i][j] == '\\') {
                        System.out.print("   " + TEXT_LCS + valuesArray[i - 1][j - 1] + TEXT_RESET + "   |");
                    } else {
                        System.out.print("   " + valuesArray[i - 1][j - 1] + "   |");
                    }
                } else {
                    System.out.print(Utils.displayChars(symbolsArray[i][j]) + "   |");
                }
            }

            Utils.printBottomSideFrame(firstSequence);
            Utils.printHorizontalFrame(firstSequence);
        }

    }

    private void checkForNullStringInput(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string cannot be null!");
        }
    }
}
