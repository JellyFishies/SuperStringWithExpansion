package com.c02249;

import java.util.*;

public class SuperString {

    int k;
    String s;
    String[] t;
    Map<Character, String[]> r;
    Set<Character> sigma = new HashSet<Character>();
    Set<Character> gamma = new HashSet<Character>();
    String gammaRegex;
    char[] sortedGammaByOcc;

    public SuperString(int k, String s, String[] t, Map<Character, String[]> r, Set<Character> sigma, Set<Character> gamma, String gammaRegex)
    {
        this.k = k;
        this.s = s;
        this.t = t;
        this.r = r;
        this.sigma = sigma;
        this.gamma = gamma;
        this.gammaRegex = gammaRegex;
        sortedGammaByOcc = sortGammaByOccurences(t,gamma);
    }

    private char[] sortGammaByOccurences(String[] t, Set<Character> gamma) {
        Map<Character,Integer> numberOfGammas = new HashMap<>();

        for (String ti : t ) {
            for (char tis: ti.toCharArray()) {
                numberOfGammas.replace(tis, numberOfGammas.getOrDefault(tis,0)+1);
            }
        }
        // Create a list from elements of HashMap
        List<Map.Entry<Character, Integer> > list =
                new ArrayList<Map.Entry<Character, Integer> >(numberOfGammas.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer> >() {
            public int compare(Map.Entry<Character, Integer> o1,
                               Map.Entry<Character, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        char[] sortedCharacters = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            sortedCharacters[i] = list.get(i).getKey();
        }
        return sortedCharacters;
    }

    public void print() {
        System.out.println("k = " + this.k);
        System.out.println("s = " + this.s);
        System.out.println("t:");
        for (String s: this.t) {
            System.out.println(s);
        }
        System.out.println("r:");
        for (Map.Entry<Character, String[]> r: this.r.entrySet()) {
            System.out.println(r.getKey() + ": " + Arrays.toString(r.getValue()));
        }
        System.out.println("Sigma:");
        for (char ch: this.sigma) {
            System.out.print(ch + " ");
        }
        System.out.println();
        System.out.println("Gamma:");
        for (char ch: this.gamma) {
            System.out.print(ch + " ");
        }
        System.out.println();
    }
}
