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
