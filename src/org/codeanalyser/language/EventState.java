package org.codeanalyser.language;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * This class is an encapsulation of an event that occurs
 * while ANTLR is walking the generated parse tree.
 * @author Jack Timblin - U1051575
 */
public class EventState {
    
    private ParserRuleContext context;
    private String eventType;
    
    /**
     * sets the event that was triggered.
     * @param type the event type.
     */
    private void setEventType(String type) {
        this.eventType = type;
    }
    
    /**
     * gets the context, i.e the fragment of the
     * parse tree that matches the rule triggered.
     * @return the context.
     */
    public ParserRuleContext getContext() {
        return context;
    }
    
    /**
     * gets the context.
     * @param context the context.
     */
    private void setContext(ParserRuleContext context) {
        this.context = context;
    }
    
    /**
     * gets the event that was triggered. 
     * @return  the event type that was triggered.
     */
    public String getEventType() {
        return this.eventType;
    }
    
    /**
     * returns a string representation of this object.
     * @return a string representation of this object.
     */
    @Override
    public String toString() {
        return "Context Tree:\n" + this.getContext().toStringTree()
                + "\nEvent: " + this.getEventType();
    }
    
    /**
     * private constructor so that
     * this class can only be initialised
     * from other classes in this class.
     */
    private EventState() {}
    
    /**
     * A builder object used to build EventState objects.
     * @author Jack Timblin - U1051575
     */
    public static class EventStateBuilder {
        
        private EventState state;
        
        /**
         * constructor - initialises a new EventState object.
         */
        public EventStateBuilder() {
            state = new EventState();
        }
        
        /**
         * set the context.
         * @param ctx the context.
         * @return the builder so we can use method chaining.
         */
        public EventStateBuilder setContext(ParserRuleContext ctx) {
            state.setContext(ctx);
            return this;
        }
        
        /**
         * sets the event that was triggered.
         * @param type the event that was triggered.
         * @return the builder so we can use method chaining.
         */
        public EventStateBuilder setEventType(String type) {
            state.setEventType(type);
            return this;
        }
        
        /**
         * build this EventState object.
         * @return the EventState object.
         */
        public EventState build() {
            return state;
        }
        
    }
    
}
