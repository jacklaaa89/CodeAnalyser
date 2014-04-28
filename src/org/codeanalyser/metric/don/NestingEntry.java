package org.codeanalyser.metric.don;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Simple class to hold a single nesting occurrence for a method.
 * @author Jack Timblin - U1051575
 */
public class NestingEntry {
    
    private int amountOfNesting = 0;
    private boolean isClosed = false;
    private String text;
    
    /**
     * get the text in the source that this nesting entry
     * evaluated.
     * @return the text from the source file.
     */
    public String getText() {
        return text;
    }
    
    /**
     * sets the text that this nesting entry is for.
     * @param ctx the context from the parse tree.
     */
    public void setText(ParserRuleContext ctx) {
        for(int i = 0; i < this.getAmountOfNesting(); i++) {
            if(ctx != null) {
                ctx = ctx.getParent();
            }
        }
        if(ctx != null) {
            this.text = ctx.getText();
        }
    }
    
    /**
     * gets the level of nesting that occurred.
     * @return the level of nesting that occurred.
     */
    public int getAmountOfNesting() {
        return amountOfNesting;
    }
    
    /**
     * sets the level of nesting that occurred.
     * @param amountOfNesting the amount of nesting that occurred.
     */
    public void setAmountOfNesting(int amountOfNesting) {
        this.amountOfNesting = amountOfNesting;
    }
    
    public boolean isClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }
    
    /**
     * returns a string representation of this object
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "[NestingLevel: " + this.getAmountOfNesting() + "]";
    }
    
}
