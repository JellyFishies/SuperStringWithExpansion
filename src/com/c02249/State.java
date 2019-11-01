package com.c02249;

import java.util.ArrayList;
import java.util.List;

public class State {
    public String[] t;
    int numberOExpansions = 0;
    int hashcode;
    State prevState;

    public State(String[] t, int numberOfExpansions, State prevState) {
        this.t = t;
        this.numberOExpansions = numberOfExpansions;
        this.prevState = prevState;
        hashcode = -1;
    }

    public List<State> getNeighbours(){
        char ch = Main.ss.sortedGammaByOcc[numberOExpansions];
        String[] expansions = Main.ss.r.get(ch);
        List<State> neighbourStates = new ArrayList<>();
        for(int i = 0; i < expansions.length; i++) {
            State neighbourState = new State(expandT(t, expansions[i], ch), numberOExpansions+1, this);
            if (isValid(neighbourState)) {
                neighbourStates.add(neighbourState);
            }
        }
        return neighbourStates;
    }

    private String[] expandT(String[] t, String expansion, char ch) {
        String[] result = new String[t.length];
        for(int i = 0; i < t.length; i++) {
            result[i] = t[i].replaceAll("" + ch, expansion);
        }
        return result;
    }

    private boolean isValid(State nextState)  {
        for (String t: nextState.t) {
            if (!Main.isSubSequence(t, Main.ss.s)) {
                return false;
            }
            if (canBecomeSubstringOfS(nextState.t)){
                return false;
            }
        }
        return true;
    }

    public boolean canBecomeSubstringOfS(String[] t){
        for (String ti : t) {
            String[] substringsOfT = ti.split(Main.ss.gammaRegex);
            int index = 0;
            for (int i = 0; i < substringsOfT.length; i++) {
                index = Main.ss.s.indexOf(substringsOfT[i],index);
                if (index == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    public int createHashcode() {
        if (this.hashcode != -1) { return this.hashcode; }
        int hashcode = 0;
        for(int i = 0; i < this.t.length; i++) {
            for(int j = 0; j < this.t[i].length(); j++) {
                hashcode += (int) Math.pow(this.t[i].charAt(j) * 31, this.t[i].length()-j);
            }
        }
        return this.hashcode = (int) Math.pow(hashcode * 1013, this.numberOExpansions * 1009);
    }

    public boolean isEquals(Object o) {
        if (!(o instanceof State)) {
            return false;
        }
        State otherState = (State) o;
        int thisHash = (this.hashcode == -1) ? createHashcode() : this.hashcode;
        int otherHash = (otherState.hashcode == -1) ? otherState.createHashcode() : otherState.hashcode;
        return thisHash == otherHash;
    }

    public boolean isGoalState() {
        if (numberOExpansions != Main.ss.gamma.size()) {
            return false;
        }
        return isValid(this);
    }
}
