package org.codeanalyser.metric.cc;

import java.util.Comparator;

/**
 * A comparator used to sort a list of Entry objects, this
 * comparator bases its compare method around the number 
 * of complex keywords are found.
 * @author Jack Timblin - U1051575
 */
public class EntryComparator implements Comparator<Entry> {
    
    /**
     * compares two Entry objects by amount of complex keywords.
     * @param o1 an entry object
     * @param o2 another entry object to compare with o1.
     * @return 1, 0 or -1 dependant on the comparison made.
     */
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
