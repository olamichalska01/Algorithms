package pl.edu.pw.ee;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        DeterministicFiniteAutomatonTextSearch dfa = new DeterministicFiniteAutomatonTextSearch("CCC");
        System.out.println(dfa.findFirst("CCCC"));
        System.out.println(Arrays.toString(dfa.findAll("CCCC")));
    } // 0123456
}
