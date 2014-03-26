package metrics.tester;

import main.MetricInterface;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * This class is a tester metric used to test that the generic autoloading of metrics
 * works correctly. It just prints the context to the console when an event is
 * triggered.
 * @author Jack Timblin - U1051575
 */
public class TesterMetric implements MetricInterface {
    
    @Override
    public String getResults() {
        return "";
    }
    
    /**
     * TODO - make EventState object, which is an encapsulation of an event.
     * @param context
     * @param sourceLanguage 
     */
    @Override
    public void start(ParserRuleContext context, String sourceLanguage) {
        System.out.println("Source Language: " + sourceLanguage);
        System.out.println(context.toStringTree());
    }
    
}
