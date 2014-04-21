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
    
    private void setEventType(String type) {
        this.eventType = type;
    }

    public ParserRuleContext getContext() {
        return context;
    }

    private void setContext(ParserRuleContext context) {
        this.context = context;
    }
    
    public String getEventType() {
        return this.eventType;
    }
    
    @Override
    public String toString() {
        return "Context Tree:\n" + this.getContext().toStringTree()
                + "\nEvent: " + this.getEventType();
    }
    
    private EventState() {}
    
    public static class EventStateBuilder {
        
        private EventState state;
        
        public EventStateBuilder() {
            state = new EventState();
        }
        
        public EventStateBuilder setContext(ParserRuleContext ctx) {
            state.setContext(ctx);
            return this;
        }
        
        
        public EventStateBuilder setEventType(String type) {
            state.setEventType(type);
            return this;
        }
        
        public EventState build() {
            return state;
        }
        
    }
    
}
