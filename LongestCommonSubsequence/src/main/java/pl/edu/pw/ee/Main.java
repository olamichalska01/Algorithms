package pl.edu.pw.ee;

public class Main {
    public static void main(String[] args) {
        String str1 = "P\r\nYTANIA\t_CIĄGLE_TE_S\n\rAME";
        String str2 = "W\r\nRÓCĘ\t_CZY_Z\n\rOSTANĘ";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(str1, str2);
        System.out.println(lcs.findLCS());
        lcs.display();
    }
}
