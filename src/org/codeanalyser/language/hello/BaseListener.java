package org.codeanalyser.language.hello;

import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.core.Application;
import java.util.ArrayList;
import org.codeanalyser.language.MetricException;
import org.codeanalyser.language.EventState;
import org.codeanalyser.language.ListenerInterface;
import org.codeanalyser.metric.Result;
import org.codeanalyser.core.analyser.FileAnalyser;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.ParserInfo;
import org.codeanalyser.language.ParserInterface;

/**
 * This class is auto generated when the parser is generated so that 
 * metrics have a single class for each language that listens of all
 * events that are applicable for all metrics being tested.
 *
 * because all metrics implement the MetricInterface interface, 
 * this means that we can just call the start() method in this to invoke 
 * all methods.
 * 
 * {@link MetricInterface.start() }
 * @author Jack Timblin - U1051575 (via StringTemplate4)
 */
public class BaseListener extends HelloBaseListener implements ListenerInterface {

    private ArrayList<MetricInterface> metrics;
    private FileAnalyser file;


    @Override
    public void init(FileAnalyser file, ParserInterface parser) throws MetricException {
        this.file = file;
        metrics = new ArrayList<MetricInterface>();
        //initialise the metrics.
        try {
            ParserInfo info = new ParserInfo(parser, file.getAbsolutePath(),
                                            file.getSourceLanguage());
            for (String metric : Application.getMetricsList()) {
                MetricInterface m = (MetricInterface) Class.forName(metric).newInstance();
                try {
                    m.init(info);
                } catch (MetricInitialisationException e) {
                    System.out.println("Failed to Initialise Metric: \"" + metric + "\"");
                    continue;
                }
                metrics.add(m);
            }
        } catch (Exception e) {
            throw new MetricException("An error occured initialising all of the metrics.");
        }
    }

    @Override
    public void destroy() {
        for(MetricInterface metric : metrics) {
            metric.destroy();
        }
    }

    /**
     * gathers the results from the metrics in this listener.
     * @return an ArrayList<Result> of string results from each of the metrics.
     */
    @Override
    public ArrayList<Result> getResults() {
        ArrayList<Result> results = new ArrayList<Result>();
        for(int i = 0; i < metrics.size(); i++) {
            MetricInterface mi = metrics.get(i);
            try {
                Result r = mi.getResults();
                if(r != null) {
                    results.add(r);
                }
            } catch (InvalidResultException e) {
                System.out.println(e.getMessage());
            }
        }
        return results;
    }

    /**
     * generated method to call enterCompilationUnit while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterCompilationUnit(HelloParser.CompilationUnitContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_COMPILATION_UNIT").build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     

}