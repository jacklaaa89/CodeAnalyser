package org.codeanalyser.core;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the system property Application class.
 * @author Jack Timblin - U1051575
 */
public class ApplicationTest {

    /**
     * Test of getMetricsList method, of class Application.
     */
    @Test
    public void testGetMetricsList() {
        System.out.println("getMetricsList");
        ArrayList<String> result = Application.getMetricsList();
        if(result.isEmpty()) {
            fail("metricList should not be empty");
        }
        System.out.println(result);
    }

    /**
     * Test of getSupportedLanguages method, of class Application.
     */
    @Test
    public void testGetSupportedLanguages() {
        System.out.println("getSupportedLanguages");
        ArrayList<String> result = Application.getSupportedLanguages();
        if(result.isEmpty()) {
            fail("supportedLanguages should not be empty");
        }
        System.out.println(result);
    }
    
    /**
     * tests hasInternetConnection of class Application.
     */
    @Test
    public void testHasInternetConnection() {
        System.out.println("hasInternetConnection");
        boolean hasInternet = Application.hasInternetConnection();
        assertTrue(hasInternet);
    }
    
}
