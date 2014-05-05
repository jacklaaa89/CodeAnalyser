import org.codeanalyser.core.Application;
import org.codeanalyser.core.ApplicationTest;
import org.codeanalyser.core.analyser.AnalyserTest;
import org.codeanalyser.core.analyser.FileAnalyserTest;
import org.codeanalyser.core.analyser.LanguageDetectTest;
import org.codeanalyser.language.HelloParseTreeConstruction;
import org.codeanalyser.language.JavaParseTreeConstruction;
import org.codeanalyser.language.SyntaxErrorAdapterTest;
import org.codeanalyser.metric.MetricInitialisationTest;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs All of the defined UnitTests for the application.
 * @author Jack Timblin - U1051575
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ApplicationTest.class, AnalyserTest.class, FileAnalyserTest.class,
LanguageDetectTest.class, HelloParseTreeConstruction.class, JavaParseTreeConstruction.class,
SyntaxErrorAdapterTest.class, MetricInitialisationTest.class})
public class MainTestSuite {

    @Before
    public void setUp() throws Exception {
        Application.systemPath =
                "/home/jack/Documents/University/CodeAnalyser/";
    }
    
}
