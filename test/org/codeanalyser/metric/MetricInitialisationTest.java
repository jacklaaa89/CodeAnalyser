package org.codeanalyser.metric;

import org.codeanalyser.core.analyser.FileAnalyser;
import org.codeanalyser.language.ListenerInterface;
import org.codeanalyser.language.MetricException;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jack
 */
public class MetricInitialisationTest {
    
    private FileAnalyser file;
    private ListenerInterface listener;
    
    @Before
    public void setUp() {
        try {
            this.file = new FileAnalyser("testData/Test2.java");
            listener = (ListenerInterface)file.getSupportedListener();
        } catch (FileAnalyser.UnsupportedLanguageException e) {
            fail(e.getMessage());
        }  catch (ClassCastException e) {
            fail(e.getMessage());
        }
    }
    
    @After
    public void tearDown() {
        listener.destroy();
        this.file = null; this.listener = null;
    }
    
    @Test
    public void testMetricInitialisation() {
        System.out.println("metricInitialisation");
        try {
            //attempt to cast this to ListenerInterface to call init, 
            //which initialises all of the installed metrics.
            listener.init(file);
            
        } catch (MetricException e) {
            fail(e.getMessage());
        }
    }
}
