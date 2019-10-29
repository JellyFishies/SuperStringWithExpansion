package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Decoder {

    private String filename;
    private SuperString superString;

    public Decoder(String filename) {
        this.filename = filename;
        this.superString = extractSuperString(readFile());
    }

    private SuperString extractSuperString(List<String> file) {
        Set<Character> sigma = new HashSet<Character>();
        Set<Character> gamma = new HashSet<Character>();

        //Extract k
        int k = Integer.parseInt(file.get(0));

        //Extract s
        String s = file.get(1);

        //Extract sigma from s
        for (Character c: s.toCharArray()) {
            sigma.add(c);
        }

        //Extract t
        String[] t = new String[k];
        for(int i = 2; i < k+2; i++) {
            t[i-2] = file.get(i);
        }

        //Extract r/{r not in sigma}
        int size = file.size();
        List<String>[] r = new ArrayList[size-k-2];
        for(int i = k; i < file.size()-2; i++) {
            String ri = file.get(i+2);

            String[] gammaAndExpansions = ri.split(":");
            gamma.add(gammaAndExpansions[0].charAt(0));

            String[] expansions = gammaAndExpansions[1].split(",");
            List<String> sortedExpansions = new ArrayList<String>();
            for (String ex : expansions) {
                if (stringInSigma(ex, sigma)) {
                    sortedExpansions.add(ex);
                }
            }
            r[i-k] = sortedExpansions;
        }

        return new SuperString(k,s,t,r, sigma, gamma);
    }

    private boolean stringInSigma(String string, Set<Character> sigma) {
        for (char ch : string.toCharArray()) {
            if (!sigma.contains(ch)) {
                return false;
            }
        }
        return true;
    }
    private List<String> readFile() {
        List<String> input = new ArrayList<String>();
        try {
            InputStream is = new FileInputStream(this.filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = br.readLine();
            while(line != null){
                input.add(line);
                line = br.readLine();
            }
        }
        catch (Exception e) {
            System.out.println("404: File not found");
        }
        return input;
    }

    public SuperString getSuperString() {
        return this.superString;
    }
}
