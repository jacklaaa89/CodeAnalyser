Generic Code Analyser
=====================

This project is a final year dissertation project which evaluates source code and determines
how well the source code has been written.

It uses the ANTLR parser generator (v4) and String Template 4 engine in order to make it generic
to all languages where the grammar is defined to have certain rules. This is obviously due to a 
'listener' being notified when ANTLR matches a rule to the grammar and if the rule definition is
different then the notifying method is different which makes it extremely difficult for the metrics
to determine what events it needs to be notified for if these change from language to language.

The modular design of the application means that language parsers are generated at runtime and any metrics
in the 'metrics' package will be initialised and used if it implements the MetricInterface interface. 

###### Version History
* **1.0 -** Initial Release. This version is run on the command line using a typical command 
            ```
            java -jar CodeAnalyser.jar analyser --source <SOURCE> --output <OUTPUT>
            ```
            This version outputs auto-generated HTML files.
* **1.1 -** This version has been highly modified in order to be used to deliver output in the JSON format so it can be used             on a webserver. Obivously the default output is still available and this feature can be toggled by using the                ```--interface``` argument when run on the command line. Example use on a PHP server could be 
            ```$json = json_decode(shell_exec('java -jar CodeAnalyser.jar analyser --interface web --source <SOURCE>'));```
            Also this version introduced the ```MetricErrorAdapter``` class which can be used so that metrics can be                    notified when an error occurs using that metric.
* **1.1.1 -** This verion streamlines some of the features that were implemented in version 1.1. The ```MetricInterface``` interface has been removed and replaced with the abstract class ```MetricAbstract``` it has the same methods that need implementing, but it also provides an easier way of reporting custom errors. This version also introduces the ```AnalyserListener``` class where we can now listen to events that are triggered during the anaylsis process. This was introduced with the idea of it being used for a graphical user interface in a later build.

###Metric Implementation

A Metric is essentially a unit of measure against the quality of written source code. This analyser has 8 defined metrics which each give a measure based on a specific feature of the source code. 

The metrics are loaded using Java's reflection API. It loads all of the classes that reside in the ```org.codeanalyser.metric``` package and then declares a metric any class that extends the ```MetricAbstract``` class.

######Example Implementation.
Below is an example implementation of a metric class which implements the ```MetricAbstract``` interface.
```java
package org.codeanalyser.metric.example;

import org.codeanalyser.metric.*;
import java.util.ArrayList;
import org.codeanalyser.core.utils.Logger;
import org.codeanalyser.language.EventState;

public class ExampleMetric extends MetricAbstract {
     @Override
     public Result getResults() throws InvalidResultException {
     }
     @Override
     public void onParserEvent(EventState state) {
     }
     @Override
     public void init(ParserInfo info) throws MetricInitialisationException {
     }
     @Override
     public void destroy() {
     }
}
```
As you can see above there are four abstract methods that have to be defined in the ```MetricAbstract``` class. ```getResults()``` is the method that is called to gather the result from the analyis. ```init()``` is called when the metric class is first initialised and passes all of the imformation regarding the file we are about to analyse, including the Parser and Lexer instances that were used to parse the source file. ```destroy()``` is called when all of the metrics have completed their analusis on a particular file.

The most important method ```onParserEvent()``` is called when the system matches rules defined in the grammar. So for instance if the system walks the tree and matches a method declaration (as defined in the grammar) this event will be fired in each of the different metrics with an ```EventState``` object which passes the event that was triggered and the segment of the source parse tree that matched the rule.

######The Result Object
The Result object that is returned can only be initialised using the static method ```Result.newInstance()``` This methods parameters take in all of the required information that is needed to generate output based on a ```Result``` object.

######Handling Multiple Output Types
Since version 1.1, the analyser now outputs in raw HTML files and also in a JSON format (dependant on the ```-i``` argument passed to the cmdline arguments) And the metric has to be able to output in each of these formats (and possibly more in the future). The second argument in the ```Result.newInstance()``` method is an instance of a ```OutputInterface``` object. This is the interface that is used to determine what output to use in the output generation stage. This class can be either implemented by any class or passed as an anonymous implementation of the interface. If you dont care about the produced output then ```null``` can be passed and the default ```OutputInterface``` will be used. An example of the use of ```Result.newInstance()``` is shown below:
```java
@Override
public Result getResults() throws InvalidResultException {
     final Object result = /* calculate result however here. */null;
     
     //params are -> the metric name, the OutputInterface result, boolean if the source code passed this metric,
     //optional ArrayList<MetricError> of errors that were recorded by this metric.
     return Result.newInstance(this.getClass().getSimpleName(), new OutputInterface(){
          @Override
          public String toHTML() {
              return result.toString();
          }
          
          @Override
          public JSONObject toJSON(){
              JSONObject root = new JSONObject();
              root.put("object", result);
              return root;
          }
     }, true);
}
```

###Handling Error
Since 1.1.1, The error handling mechanism has been improved. We can now attach an ```MetricErrorAdapter``` to the metric and we can use the ```reportError()``` method to report an error to be attached to the output. The ```reportMetric()``` method takes any implementation of a ```MetricError``` class and uses it to report the error. The HTML output from the method ```toHTML()``` is first cleaned by the system. This is shown below:
```java
package org.codeanalyser.metric.example;

import org.codeanalyser.metric.*;
import java.util.ArrayList;
import org.codeanalyser.core.utils.Logger;
import org.codeanalyser.language.EventState;

public class ExampleMetric extends MetricAbstract implements MetricErrorAdapter {
    
     ...//other class source code here.
     
     public ExampleMetric() {
         this.setErrorAdapter(this);
     }
     
     @Override
     public Result getResults() throws InvalidResultException {
     }
     @Override
     public void onParserEvent(EventState state) {
     }
     @Override
     public void init(ParserInfo info) throws MetricInitialisationException {
     }
     @Override
     public void destroy() {
     }
     @Override
     public void onInitialisationError(MetricInitialisationException e, Logger logger, ParserInfo info) {
          reportError(new MetricError() {
             @Override
             public String toHTML() {
                return "<div>error occured.</div>";
             }
             
             @Override
             public JSONObject toJSON() {
                JSONObject ob = new JSONObject();
                ob.put("error", "An error occured");
                return ob;
             }
          });
     }
     @Override
     public void onInvalidResultException(InvalidResultException e, Result result,
            Logger logger, ParserInfo info) {
     }
     
     ...//any other class source code here.
}
```
As you can see the class is exactly the same, but now it gets notified when any of the exceptions occur.

###Listening for Events in the Analysis Process.
Since 1.1.1, the analyser now has the ability to attach a ```AnalyserListener``` interface instance which is triggered at certain points in the analysis. There is also an adapter class called ```AnalyserAdapter``` which gives the ability to be able to override only the events that you want to be notified for instead of implementing all of the event triggers. We can use this mechanism as follows:
```java
package org.codeanalyser.example;

import java.util.ArrayList;
import org.codeanalyser.core.output.OverallResult;
import org.codeanalyser.language.SyntaxErrorException;
import org.codeanalyser.metric.ParserInfo;
import org.codeanalyser.core.analyser.AnalyserResult;
import org.codeanalyser.core.analyser.UnsupportedLanguageException;
import org.codeanalyser.core.analyser.AnalyserAdapter;

public class ExampleAnalyserAdapter extends AnalyserAdapter {

    @Override
    public void onStartAnalysis() {
        System.out.println("onStartAnalysis() called");
    }
    
    ...//more event overrides here.
    
    @Override
    public void onCompleteAnalysis() {
        System.out.println("onCompleteAnalysis() called");
    }

}

```

Then we'd obviously just attach an instance of this class to the ```Analyser``` instance as follows:
```java

...//more code here.

Analyser analyser = new Analyser("sourceLocation", "outputLocation");
analyser.setAnalyserListener(new ExampleAnalyserAdapter());

...//more code here.

```
