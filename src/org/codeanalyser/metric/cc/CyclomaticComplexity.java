package org.codeanalyser.metric.cc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.antlr.v4.runtime.ParserRuleContext;
import org.codeanalyser.language.EventState;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.metric.ParserInfo;
import org.codeanalyser.metric.Result;

/**
 * CyclomaticComplexity is essentially counting the amount of complexity keywords
 * per method and then determining which method/constructor was most complex and if
 * they exceeded the threshold.
 * @author Jack Timblin - U1051575
 */
public class CyclomaticComplexity implements MetricInterface {
    
    private String fileName, sourceLang;
    private ArrayList<Entry> entries;
    private final int complexityThreshold = 10;
    
    //events and tokens.
    private final String[] statementEvents = {"ENTER_STATEMENT", "ENTER_SWITCH_BLOCK_STATEMENT_GROUP", "ENTER_CATCH_CLAUSE"};
    private final String[] methodEvents = {"ENTER_CONSTRUCTOR_DECLARATION", "ENTER_METHOD_DECLARATION"};
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
                Collections.sort(entries);
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
    public void onParserEvent(EventState state) {
        if(Arrays.asList(methodEvents).contains(state.getEventType())) {
            //if were in a constructor or a method, count the amount of complexity keywords in that
            //method or constructor, store these in a ArrayList.
            Entry currentEntry = new Entry();
            currentEntry.setContext(state.getContext());
            currentEntry.setMethodName(state.getContext().getChild(1).getText());
            currentEntry.setType(state.getEventType());
            currentEntry.setAmountOfComplexKeywords(0);
            currentEntry.setComplexityThreshold(complexityThreshold);
            this.entries.add(currentEntry);
            
        } else if (Arrays.asList(statementEvents).contains(state.getEventType())) {
            //statements only appear inside constructors or methods
            //so we can safety assume the last entry in our entries is the
            //current method/constructor.
            if(!this.entries.isEmpty()) {
                Entry e = this.entries.get(this.entries.size()-1);
                this.determineComplexity(state.getContext(), e);
            }
        }
    }
    
    /**
     * determines the complexity of the class as a whole.
     * @param context the statement context.
     */
    private void determineComplexity(ParserRuleContext context, Entry entry) {
        for(String s : this.complexityTokens) {
            if(s.equalsIgnoreCase(context.getStart().getText().toUpperCase())) {
                int c = entry.getAmountOfComplexKeywords();
                entry.setAmountOfComplexKeywords(c+1);
                entry.addKeywordOccurrence(context.getStart().getText());
                break;
            }
        }
    }

    @Override
    public void init(ParserInfo initialInformation) throws MetricInitialisationException {
        this.fileName = initialInformation.getFileName();
        this.sourceLang = initialInformation.getSourceLanguage();
        this.entries = new ArrayList<Entry>();
    }

    @Override
    public void destroy() {}
    
}
