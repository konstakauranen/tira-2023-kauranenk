package oy.interact.tira.student;

import java.util.Comparator;

import oy.interact.tira.util.Pair;

public class CodeWordsCounterComparator implements Comparator<Pair<String, Integer>>{

    @Override
    public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
        return(o2.getValue().compareTo(o1.getValue()));
    }

    
}
