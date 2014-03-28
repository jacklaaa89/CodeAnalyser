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
