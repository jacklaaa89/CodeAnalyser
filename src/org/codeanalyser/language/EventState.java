package org.codeanalyser.language;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * This class is an encapsulation of an event that occurs
 * while ANTLR is walking the generated parse tree.
 * @author Jack Timblin - U1051575
 */
public class EventState {
    
    private String sourceLanguage;
    private ParserRuleContext context;
    private EventType eventType;

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    private void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }
    
    private void setEventType(EventType type) {
        this.eventType = type;
    }

    public ParserRuleContext getContext() {
        return context;
    }

    private void setContext(ParserRuleContext context) {
        this.context = context;
    }
    
    public EventType getEventType() {
        return this.eventType;
    }
    
    @Override
    public String toString() {
        return "Source Language: "
                + this.getSourceLanguage() 
                + "\nContext Tree:\n" + this.getContext().toStringTree()
                + "\nEvent: " + this.getEventType().name();
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
        
        
        public EventStateBuilder setEventType(EventType type) {
            state.setEventType(type);
            return this;
        }
        
        public EventStateBuilder setSourceLanguage(String sourceLanguage) {
            state.setSourceLanguage(sourceLanguage);
            return this;
        }
        
        public EventState build() {
            return state;
        }
        
    }
    
}
