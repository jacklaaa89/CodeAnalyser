package org.codeanalyser.core.utils;

import java.util.ArrayList;
import org.codeanalyser.core.Application;

/**
 * Simple Logger class that handles the console based on the interface type.
 * Exceptions are also automatically logged internally.
 * @author Jack Timblin
 */
public class Logger {
    
    private String mInterface = Application.getInterface();
    private boolean consoleLogging = true;
    private final ArrayList<Exception> exceptions = new ArrayList<Exception>();
    
    /**
     * default interface type. normal logging.
     */
    public static final String DEFAULT = "default";
    
    /**
     * web interface type, no logging on the console.
     */
    public static final String WEB = "web";
    
    /**
     * use System.err.println(); to print the message. 
     */
    public static final int ERROR_STREAM = 1;
    
    /**
     * use System.out.println(); to print the message. 
     */
    public static final int DEFAULT_STREAM = 2;
    
    public Logger() {
        this.consoleLogging = (mInterface.equals(DEFAULT));
    }
    
    /**
     * logs an object on to the default stream (or error stream if the object
     * is an exception). An object is logged by its toString method.
     * @param object the object to log.
     */
    public void log(Object object) {
        this.log(object, (object instanceof Exception) 
                ? ERROR_STREAM : DEFAULT_STREAM);
    }
    
    /**
     * logs an object on to the a defined stream. An object is logged by its toString method.
     * @param object the object to log.
     * @param stream the stream to print to, will print to default stream (or error stream if the object
     * is an exception) if the supplied stream is invalid.
     */
    public void log(Object object, int stream) {
        this.log(object.toString(), stream, (object instanceof Exception));
    }
    
    /**
     * logs a message on the default stream.
     * @param message the message to log.
     */
    public void log(String message) {
        this.log(message, DEFAULT_STREAM);
    }
    
    /**
     * logs a message on a defined stream.
     * @param message the message to log.
     * @param stream the stream to log the message on. either DEFAULT_STREAM or ERROR_STREAM.
     */
    public void log(String message, int stream) {
        this.log(message, stream, false);
    }
    
    /**
     * logs an exception on the error stream.
     * @param exception the exception to log.
     */
    public void log(Exception exception) {
        this.log(exception, ERROR_STREAM);
    }
    
    /**
     * logs an exception on a defined stream.
     * @param exception the exception to log.
     * @param stream the stream to log the message on. either DEFAULT_STREAM or ERROR_STREAM.
     */
    public void log(Exception exception, int stream) {
        this.log(exception.getMessage(), stream, true);
        exceptions.add(exception);
    }
    
    /**
     * helper method - logs a message to the defined stream.
     * if the defined stream is invalid, it chooses a stream based on if the
     * message came from an exception or not.
     * @param message the message to log.
     * @param stream the stream to log to.
     * @param isException if the message is derived from an exception.
     */
    private void log(String message, int stream, boolean isException) {
        
        //update mInterface && consoleLogging incase it was changed in the application.
        this.mInterface = Application.getInterface();
        this.consoleLogging = (mInterface.equals(DEFAULT));
        
        //determine if the stream is valid.
        if(stream != ERROR_STREAM || stream != DEFAULT_STREAM) {
            stream = (isException) ? ERROR_STREAM : DEFAULT_STREAM;
        }
        
        //log the message.
        if(consoleLogging) {
            switch(stream) {
                case DEFAULT_STREAM:
                    System.out.println(message);
                    break;
                case ERROR_STREAM:
                    System.err.println(message);
                    break;
            }
        }
    }
    
    /**
     * gets the exceptions that were recorded in the logger.
     * @return the exceptions recorded.
     */
    public ArrayList<Exception> getExceptions() {
        return this.exceptions;
    }
    
    /**
     * returns a string representation of this object
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        String m = "Logger:[interface: "+this.mInterface+", exceptions: [";
        for(Exception e : exceptions) {
            m += "[exceptionType:  "+e.getClass().getSimpleName()+", message: "+e.getMessage()+"]";
        }
        return m + "]";
    }
    
}
