package com.c02249;

import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

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
        String gammaRegex = "[";


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
        Map<Character, String[]> r = new HashMap<Character, String[]>();
        for(int i = k; i < file.size()-2; i++) {
            String ri = file.get(i+2);

            String[] gammaAndExpansions = ri.split(":");
            char gammaChar = gammaAndExpansions[0].charAt(0);
            gamma.add(gammaChar);
            gammaRegex = gammaRegex + gammaChar;

            String[] expansions = gammaAndExpansions[1].split(",");
            List<String> sortedExpansions = new ArrayList<String>();
            for (String ex : expansions) {
                if (s.contains(ex)) {
                    sortedExpansions.add(ex);
                }
            }
            r.put(gammaAndExpansions[0].charAt(0), sortedExpansions.toArray(String[]::new));
        }
        gammaRegex = gammaRegex + "]";

        return new SuperString(k,s,t,r, sigma, gamma, gammaRegex);
    }

    private List<String> readFile() {
        List<String> input = new ArrayList<String>();
        try {
            //InputStream is = new FileInputStream(this.filename);
            //BufferedReader br = new BufferedReader(new InputStreamReader(is));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//is));

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
