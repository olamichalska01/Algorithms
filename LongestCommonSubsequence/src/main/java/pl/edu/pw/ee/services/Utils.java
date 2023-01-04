package pl.edu.pw.ee.services;

public class Utils {
    public static void setUpFrame(char[][] symbolsArray, int[][] valuesArray, char[] firstSequence,
            char[] secondSequence) {
        symbolsArray[0][0] = ' ';
        symbolsArray[0][1] = ' ';
        symbolsArray[1][0] = ' ';

        for (int j = 1; j < firstSequence.length + 2; j++) {
            symbolsArray[1][j] = '0';
        }

        for (int j = 1; j < firstSequence.length + 1; j++) {
            valuesArray[0][j] = 0;
            symbolsArray[0][j + 1] = firstSequence[j - 1];
        }

        for (int i = 1; i < secondSequence.length + 2; i++) {
            symbolsArray[i][1] = '0';
        }

        for (int i = 1; i < secondSequence.length + 1; i++) {
            valuesArray[i][0] = 0;
            symbolsArray[i + 1][0] = secondSequence[i - 1];
        }
    }

    public static void printBottomSideFrame(char[] firstSequence) {
        System.out.print("\n|");
        for (int bottom = 0; bottom < firstSequence.length + 2; bottom++) {
            System.out.print("       |");
        }
    }

    public static void printBeginningOfFrame() {
        System.out.print("+-------+");
    }

    public static void printFrame() {
        System.out.print("-------+");
    }

    public static void printHorizontalFrame(char[] firstSequence) {
        System.out.println();
        printBeginningOfFrame();
        for (int numOfLetters = 0; numOfLetters < firstSequence.length + 1; numOfLetters++) {
            printFrame();
        }
        System.out.println();
    }

    public static String displayChars(char character) {
        String result;
        switch (character) {
            case '\t':
                result = "  \\t";
                break;
            case '\n':
                result = "  \\n";
                break;
            case '\f':
                result = "  \\f";
                break;
            case '\b':
                result = "  \\b";
                break;
            case '\r':
                result = "  \\r";
                break;
            default:
                result = "   " + Character.toString(character);
        }
        return result;
    }
}
