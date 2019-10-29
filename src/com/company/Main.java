package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Decoder decoder = new Decoder("TestFiles/test03.swe");
        SuperString ss = decoder.getSuperString();
        ss.print();
    }
}
