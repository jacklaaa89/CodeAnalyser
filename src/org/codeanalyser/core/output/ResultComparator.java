package org.codeanalyser.core.output;

import java.util.Comparator;
import java.util.Map;

/**
 * Comparator class used to compare Result objects to determine
 * if the OverallResult object was overallSuccessful i.e did a single file
 * pass the majority of the metric tests.
 * @author Jack Timblin - U1051575
 */
public class ResultComparator implements Comparator<Map.Entry<Boolean, Integer>> {

    @Override
    public int compare(Map.Entry<Boolean, Integer> o1, Map.Entry<Boolean, Integer> o2) {
        return o1.getValue().compareTo(o2.getValue());
    }
    
}
