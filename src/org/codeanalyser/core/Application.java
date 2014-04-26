package org.codeanalyser.core;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * This class will be initialised on startup and contain global properties that
 * can be accessed statically.
 *
 * @author Jack Timblin - U1051575
 */
public class Application {

    private static ArrayList<String> metrics;
    private static ArrayList<String> supported;
    private static final String systemPath;

    //declare static variables, i.e initialise system properties.
    static {
        metrics = new ArrayList<String>();
        try {
            metrics = Application.initMetricsList();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            supported = Application.initSupported();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        systemPath = Application.initSystemPath();
    }

    /**
     * iterates over the packages in 'languages' to determine which languages
     * are currently supported by the system.
     *
     * @version 1.1 - used reflection to determine the package names instead of
     * depending on a specific file location.
     * @return an ArrayList<String> of supported language names.
     */
    private static ArrayList<String> initSupported() throws ClassNotFoundException {
        ArrayList<String> support = new ArrayList<String>();
        String pc = "org.codeanalyser.language";
        ArrayList<Class<?>> classes = ReflectionHelper.getClassesForPackage(pc);
        for (Class c : classes) {
            try {
                String n = c.getPackage().getName();
                String ln = n.substring(pc.length() + 1, n.length());
                if (!ln.isEmpty() && !support.contains(ln)) {
                    support.add(ln);
                }
            } catch (StringIndexOutOfBoundsException e) {
            }
        }

        return support;
    }
    
    /**
     * Utility method to determine if the user has an internet connection.
     * @return true if a connection was made, false if not.
     */
    public static boolean hasInternetConnection() {
        //a site that is more than likey going to be online.
        String site = "google.com";
        Socket sock = new Socket();
        InetSocketAddress addr = new InetSocketAddress(site, 80);
        try {
            sock.connect(addr, 3000);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                sock.close();
            } catch (IOException e) {}
        }
    }

    /**
     * gets the current path of where the jar file is being run from. used to
     * set where the config/antlr folders are.
     *
     * @return the current system path where the jar file is running from.
     */
    private static String initSystemPath() {
        String command = System.getProperty("sun.java.command");
        String[] split = command.split(" analyser ");
        File f = new File(split[0]);
        return f.getParent();
    }

    /**
     * iterates through the metrics packages to find which classes are declared
     * to be 'metrics'. A metric is defined as any class that implements
     * MetricInterface.
     *
     * @version 1.1 - uses reflection instead of depending on a certain file
     * location.
     * @return an ArrayList<String> the class names of the found metrics.
     * @throws ClassNotFoundException when a class could not be found.
     */
    private static ArrayList<String> initMetricsList() throws ClassNotFoundException {
        ArrayList<String> classes = new ArrayList<String>();
        ArrayList<Class<?>> cs = ReflectionHelper.getClassesForPackage("org.codeanalyser.metric");
        for (Class c : cs) {
            Class<?>[] in = c.getInterfaces();
            if (in.length != 0) {
                if (in[0].getSimpleName().equals("MetricInterface") && !c.getSimpleName().equals("TesterMetric")) {
                    classes.add(c.getName());
                }
            }
        }
        return classes;
    }

    /**
     * System Property - gets the metrics that are currently 'attached' to the
     * application. This is the class names of the class that implement
     * MetricInterface.
     *
     * @return an ArrayList containing the class names of the metrics in the
     * system.
     */
    public static ArrayList<String> getMetricsList() {
        return Application.metrics;
    }

    /**
     * gets the current system path that the jar file is running from.
     *
     * @return the current system path.
     */
    public static String getSystemPath() {
        return Application.systemPath;
    }

    /**
     * System Property - gets the languages that the application currently
     * supports.
     *
     * @return an ArrayList containing the supported languages.
     */
    public static ArrayList<String> getSupportedLanguages() {
        return Application.supported;
    }

}
