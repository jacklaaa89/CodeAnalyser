package org.codeanalyser.metric.pdl;

import java.util.ArrayList;
import java.util.Collections;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.codeanalyser.language.EventState;
import org.codeanalyser.language.ParserInterface;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.metric.ParserInfo;
import org.codeanalyser.metric.Result;

/**
 *
 * @author jonathan
 */
public class ProcedureDeclarationLength implements MetricInterface {

    private String fileName, sourceLang;
    private ArrayList<Entry> entries;
    
    @Override
    public Result getResults() throws InvalidResultException {
        
        //define a default result.
        Entry worst = new Entry();
        int i = 0;
        
        //check how many are within thresholds.
        for(Entry e : this.entries) {
            i = (e.isWithinThreshold()) ? i+1 : i;
        }
        
        //check if the majority of the methods were within thresholds.
        boolean overallSuccess = (i > (this.entries.size()/2));
        
        //sort the entries to determine the worst defined procedure declaration.
        Collections.sort(entries);
        if(!this.entries.isEmpty()) {
            worst = this.entries.get(entries.size()-1);
        }
        
        //generate result HTML.
        String result = "<table><tr><td>ProcedureDeclarationLength Results: </td></tr>"
                + "<tr><td>Amount of Methods Within Threshold: "+i+"</td></tr>"
                + "<tr><td>Worst defined procedure declaration: "+worst.getMethodName()+"</td></tr>"
                + "<tr><td><span>Thresholds: </span></td></tr>"
                + "<tr><td>Method Length: "+worst.getLengthThreshold()+"</td></tr>"
                + "<tr><td>No Of Variables: "+worst.getNoOfParametersThreshold()+"</td></tr></table>";
        
        return Result.newInstance(fileName, sourceLang, this.getClass().getSimpleName(), result, overallSuccess);
    }

    @Override
    public void onParserEvent(EventState state) {
        if(state.getEventType().equals("ENTER_METHOD_DECLARATION")) {
            Entry e = new Entry();
            //get the declaration of the method text.
            e.setMethodDeclaration(state.getContext().getParent().getText().split("\\{")[0]);
            //get the method name.
            e.setMethodName(state.getContext().getChild(1).getText());
            int a = 0;
            //determine how many parameters were defined in the method name.
            ParseTree ctx = state.getContext().getChild(2).getChild(1);
            int count = ctx.getChildCount();
            for(int i = 0; i < count; i++) {
                if(!ctx.getChild(i).getText().equals(",")) {
                    a++;
                }
            }
            e.setNoOfParameters(a);
            //add to entries.
            this.entries.add(e);
        }
    }

    @Override
    public void init(ParserInfo info) throws MetricInitialisationException {
        this.fileName = info.getFileName();
        this.sourceLang = info.getSourceLanguage();
        this.entries = new ArrayList<Entry>();
    }

    @Override
    public void destroy() {}
    
}
