package org.codeanalyser.metric.cc;

import java.util.ArrayList;
import java.util.Collections;
import org.codeanalyser.language.EventState;
import org.codeanalyser.language.EventType;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.metric.Result;

/**
 * CyclomaticComplexity is essentially counting the amount of complexity keywords
 * per method and then determining which method/constructor was most complex and if
 * they exceeded the threshold.
 * @author Jack Timblin - U1051575
 */
public class CyclomaticComplexity implements MetricInterface {
    
    private String fileName, sourceLang;
    private String[] tokens;
    private ArrayList<Entry> entries;
    private final int complexityThreshold = 10;
    private final String[] complexityTokens = {"DEFAULT", "FOR", "WHILE", "IF", "CASE", "CONTINUE", "CATCH"};

    @Override
    public Result getResults() throws InvalidResultException {
        
        //determine which method had the most complexity and whether it went over the
        //threshold.
        Entry e = new Entry();
        
        //if there are entries to use.
        if(!entries.isEmpty()) {
            //if there is more than one method/constructor.
            if(entries.size() > 1) {
                //sort the entries in natural order based on the amount of
                //complex keywords.
                Collections.sort(entries, new EntryComparator());
                e = entries.get(entries.size()-1); //get the last object.
            } else {
                //get the only entry and see how well it done.
                e = entries.get(0);
            }
        }
        
        return Result.newInstance(fileName, sourceLang, this.getClass().getSimpleName(), e.toResult(),
                (e.getAmountOfComplexKeywords() <= this.complexityThreshold 
                        && e.getAmountOfComplexKeywords() != -1));
    }

    @Override
    public void start(EventState state) {
        if(state.getEventType().equals(EventType.ENTER_CONSTRUCTOR_DECLARATION) ||
                state.getEventType().equals(EventType.ENTER_METHOD_DECLARATION)) {
            //if were in a constructor or a method, count the amount of complexity keywords in that
            //method or constructor, store these in a ArrayList.
            Entry e = new Entry();
            e.setContext(state.getContext());
            e.setMethodName(state.getContext().getText());
            e.setType(state.getEventType());
            e.setComplexityThreshold(complexityThreshold);
            
            //determine the number of complex keywords.
            //TODO - determine complexity.
            e.setAmountOfComplexKeywords(0);
            
            //add it to entries.
            entries.add(e);
        }
    }

    @Override
    public void init(String fileLocation, String sourceLanguage, String[] tokens) throws MetricInitialisationException {
        this.fileName = fileLocation;
        this.sourceLang = sourceLanguage;
        this.tokens = tokens;
        this.entries = new ArrayList<Entry>();
    }

    @Override
    public void destroy() {
        
    }
    
}
