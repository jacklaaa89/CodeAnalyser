package org.codeanalyser.metric.don;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.codeanalyser.language.EventState;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricErrorAdapter;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.metric.ParserInfo;
import org.codeanalyser.metric.Result;

/**
 * This metric determines the where the deepest level occurs in a method or constructor.
 * @author Jack Timblin - U1051575
 */
public class DepthOfNesting implements MetricInterface {
    
    private ArrayList<Entry> entries;
    private final String[] methodEvents = {"ENTER_CONSTRUCTOR_DECLARATION", "ENTER_METHOD_DECLARATION"};

    @Override
    public Result getResults() throws InvalidResultException {
        
        Entry e = new Entry();
        
        //if there are entries to use.
        if(!entries.isEmpty()) {
            //if there is more than one method/constructor.
            if(entries.size() > 1) {
                //sort the entries in natural order based on the amount of
                //complex keywords.
                Collections.sort(entries);
                e = entries.get(entries.size()-1); //get the last object.
            } else {
                //get the only entry and see how well it done.
                e = entries.get(0);
            }
        }
        
        return Result.newInstance(this.getClass().getSimpleName(),
                e.toResult(), e.getDeepestNestingOccuranceAmount() <= e.getNestingThreshold());
    }

    @Override
    public void onParserEvent(EventState state) {
        if(Arrays.asList(methodEvents).contains(state.getEventType())) {
            Entry e = new Entry(state.getContext().getChild(1).getText());
            this.entries.add(e);
        } else if(state.getEventType().equals("ENTER_BLOCK")) {
            if(!entries.isEmpty()) {
                Entry e = this.entries.get(this.entries.size()-1);
                NestingEntry ne = e.getLastNestingEntry();
                if(ne != null && !ne.isClosed()) {
                    //we are still in a nest.
                    ne.setAmountOfNesting(ne.getAmountOfNesting()+1);
                } else {
                    //this is a new nest.
                    ne = new NestingEntry();
                    ne.setAmountOfNesting(1);
                    e.setNestingEntry(ne);
                }
            }
        } else if(state.getEventType().equals("EXIT_BLOCK")) {
            //get the Entry.
            if(!entries.isEmpty()) {
                Entry e = this.entries.get(this.entries.size()-1);
                NestingEntry ne = e.getLastNestingEntry();
                if(ne != null) { //if nesting occured in the block.
                    e.setDeepestNestingOccurance(ne);
                    //set this nesting occurance to 'closed'.
                    ne.setIsClosed(true);
                    ne.setText(state.getContext());
                }
            }
            
        }
    }

    @Override
    public void init(ParserInfo info) throws MetricInitialisationException {
        this.entries = new ArrayList<Entry>();
    }

    @Override
    public void destroy() {}
    
}
