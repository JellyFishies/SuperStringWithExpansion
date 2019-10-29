package com.company;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SuperString {

    int k;
    String s;
    String[] t;
    List<String>[] r;
    Set<Character> sigma = new HashSet<Character>();
    Set<Character> gamma = new HashSet<Character>();

    public SuperString(int k, String s, String[] t, List<String>[] r, Set<Character> sigma, Set<Character> gamma)
    {
        this.k = k;
        this.s = s;
        this.t = t;
        this.r = r;
        this.sigma = sigma;
        this.gamma = gamma;
    }

    private int GetK()
    {
        return this.k;
    }

    private void SetK(int k)
    {
        this.k = k;
    }

    private String GetS()
    {
        return this.s;
    }

    private void SetS(String s)
    {
        this.s = s;
    }

    private String[] GetT()
    {
        return this.t;
    }

    private void SetT(String[] t)
    {
        this.t = t;
    }

    private void AddToT(int i, String ti)
    {
        this.t[i] = ti;
    }

    private List<String>[] GetR()
    {
        return this.r;
    }

    private void SetR(List<String>[] r)
    {
        this.r = r;
    }

}
