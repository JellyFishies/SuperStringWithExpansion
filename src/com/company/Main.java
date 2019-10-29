package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Decoder decoder = new Decoder("TestFiles/test03.swe");
        SuperString ss = decoder.getSuperString();
        printSuperString(ss);
    }

    public static void printSuperString(SuperString ss) {
        System.out.println("k = " + ss.k);
        System.out.println("s = " + ss.s);
        System.out.println("t:");
        for (String s: ss.t) {
            System.out.println(s);
        }
        System.out.println("r:");
        for (List<String> r: ss.r) {
            System.out.println(r);
        }
        System.out.println("Sigma:");
        for (char ch: ss.sigma) {
            System.out.print(ch + " ");
        }
        System.out.println();
        System.out.println("Gamma:");
        for (char ch: ss.gamma) {
            System.out.print(ch + " ");
        }
    }
}
