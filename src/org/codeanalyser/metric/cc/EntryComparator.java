package org.codeanalyser.metric.cc;

import java.util.Comparator;

/**
 *
 * @author Jack
 */
public class EntryComparator implements Comparator<Entry> {

    @Override
    public int compare(Entry o1, Entry o2) {
        if(o1.getAmountOfComplexKeywords() < o2.getAmountOfComplexKeywords()) {
            return -1;
        } else if(o1.getAmountOfComplexKeywords() > o2.getAmountOfComplexKeywords()) {
            return 1;
        } else if(o1.getAmountOfComplexKeywords() == o2.getAmountOfComplexKeywords()) {
            return 0;
        } else {
            return 0;
        }
    }
    
}
