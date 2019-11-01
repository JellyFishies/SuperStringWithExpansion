package com.c02249;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Main {

    public static SuperString ss;

    public static void main(String[] args) {
        Decoder decoder = new Decoder("TestFiles/test06.swe");
        ss = decoder.getSuperString();
        ss.print();
        State startState = new State(ss.t, 0, null);
        dfs(startState);
    }

    public static State dfs(State startState) {
        Set<State> visited = new HashSet<>();
        Stack<State> unexplored = new Stack<>();
        boolean goalIsReached = false;

        unexplored.add(startState);
        State currentState = startState;
        while(!goalIsReached && !unexplored.isEmpty()) {
            while(visited.contains(currentState)) {
                currentState = unexplored.pop();
            }

            visited.add(currentState);
            goalIsReached = currentState.isGoalState();

            unexplored.addAll(currentState.getNeighbours());
            if (goalIsReached) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
        return currentState;
    }


    // Returns true if t[] is a subsequence
    // of s[] m is length of t and n is
    // length of s
    public static boolean isSubSequence(String t,
            String s)
    {
        int j = 0;
        int m = t.length();
        int n = s.length();

        // Traverse s and t, and compare
        // current character of s with first
        // unmatched char of t, if matched
        // then move ahead in str1
        for (int i = 0; i < n && j < m; i++) {
            char a = t.charAt(j);
            if (ss.gamma.contains(a) || a == s.charAt(i))
                j++;
        }
        // If all characters of t were found
        // in s
        return (j == m);
    }
}
