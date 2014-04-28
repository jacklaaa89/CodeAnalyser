/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.codeanalyser.metric.don;

import java.util.Comparator;

/**
 *
 * @author jonathan
 */
public class EntryComparator implements Comparator<Entry> {

    @Override
    public int compare(Entry o1, Entry o2) {
        if(o1.getDeepestNestingOccuranceAmount() < o2.getDeepestNestingOccuranceAmount()) {
            return -1;
        } else if(o1.getDeepestNestingOccuranceAmount() > o2.getDeepestNestingOccuranceAmount()) {
            return 1;
        } else if(o1.getDeepestNestingOccuranceAmount() == o2.getDeepestNestingOccuranceAmount()) {
            return 0;
        } else {
            return 0;
        }
    }
    
}
