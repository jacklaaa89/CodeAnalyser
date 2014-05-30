package org.codeanalyser.core.analyser;

import java.io.File;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codeanalyser.core.Application;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests the
 * @author jack
 */
public class AnalyserTest {
    
    private Analyser analyser;
    private final String SOURCE = "testData";
    private final String OUTPUT = "output";
    private final String FORCED = "java";
    
    @Before
    public void setUp() {
        Application.systemPath = "";
        try {
            analyser = new Analyser(SOURCE, OUTPUT);
        } catch (AnalyserException ex) {
            Logger.getLogger(AnalyserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        analyser = null;
    }

    /**
     * Test of analyse method, of class Analyser.
     */
    @Test
    public void testAnalyseForced() {
        System.out.println("analyseForced");
        analyser.analyse(FORCED, true);
        File f = new File(OUTPUT+"/output.html");
        assertTrue(f.exists());
        
        //I know there is 2 java files and one '.hello' file
        //in the SOURCE location, so because this test has been forced
        //to FORCED, there will be come syntax errors.
        AnalyserResult result = analyser.getResults();
        //System.out.println("NoOfErrorFiles: " + result.getNoOfSyntaxErrorFiles());
        assertTrue(result.getNoOfSyntaxErrorFiles() == 4);
    }

    /**
     * Test of analyse method, of class Analyser.
     */
    @Test
    public void testAnalyse() {
        System.out.println("analyse");
        analyser.analyse(true);
        
        //test output was successfully generated.
        File f = new File(OUTPUT+"/output.html");
        assertTrue(f.exists());
        
        //I know all files are supported
        //and it contains one file with a syntax error so the success/fail
        //should be 3/1.
        int[] expected = {6, 1};
        int[] result = analyser.getResults().getSuccessFailFigures();
        System.out.println(Arrays.toString(result));
        //assertArrayEquals(expected, result);
    }
    
}
