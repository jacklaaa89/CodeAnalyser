package org.codeanalyser.metric.loc;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.codeanalyser.language.EventState;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.metric.Result;

/**
 * This metric determines the amount of lines of code that a source code file has
 * and checks to see if this is within a given threshold.
 * @author Jack Timblin - U1051575
 */
public class LinesOfCode implements MetricInterface {
    
    private String fileName, sourceLang;
    private int loc = 0;
    private final int locThreashold = 100;

    @Override
    public Result getResults() {
        
        String result = "<table><tr><td>LinesOfCode Result: </td></tr>"
                + "<tr><td>Lines Of Code: "+this.loc+"</td></tr>"
                + "<tr><td>Threshold: "+this.locThreashold+"</td></tr></table>";
        
        return Result.newInstance(fileName, sourceLang, this.getClass().getSimpleName(), result, loc <= locThreashold);
    }

    @Override
    public void start(EventState state) {
        //this metric just needs the file overall and 
        //does not need to be notified when events occurs while
        //walking the parse tree.
    }

    @Override
    public void init(String fileLocation, String sourceLanguage, String[] tokens) throws MetricInitialisationException {
        try {
            InputStream stream = new BufferedInputStream(new FileInputStream(fileLocation));
            try {
                byte[] c = new byte[1024];
                int count = 0;
                int readChars = 0;
                boolean empty = true;
                while((readChars = stream.read(c)) != -1) {
                    empty = false;
                    for(int i = 0; i < readChars; ++i) {
                        if(c[i] == '\n') {
                            ++count;
                        }
                    }
                }
                loc = (count == 0 && !empty) ? 1 : count;
            } finally {
                stream.close();
            }
        } catch (FileNotFoundException e) {
            throw new MetricInitialisationException("Cound not locate source file.");
        } catch (IOException e) {
            throw new MetricInitialisationException(e.getMessage());
        }
    }

    
    
}
