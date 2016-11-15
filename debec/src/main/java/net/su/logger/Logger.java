package net.su.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public static SimpleDateFormat loggerFormat = new SimpleDateFormat("[yyyy-MM-dd,HH:mm:ss]");

    public static String getLoggingTime() {
        return loggerFormat.format(new Date());
    }

    public static String getCallerInfo() {
        Throwable th = new Throwable();
        StackTraceElement[] ste = th.getStackTrace();
        String className = ste[2].getClassName();
        int idx = className.lastIndexOf('.');
        if (idx > 0) {
            className = className.substring(idx + 1);
        }
        return "[" + className + "." + ste[2].getMethodName() + ":" + ste[2].getLineNumber() + "]";
    }

    /**
     * Print informative data
     *
     * @param msg
     */
    public static void info(String msg) {
        System.out.println(getLoggingTime() + "[I][" + getCallerInfo() + "] " + msg);
    }

    /**
     * Print debug data
     *
     * @param msg
     */
    public static void debug(String msg) {
        System.out.println(getLoggingTime() + "[D][" + getCallerInfo() + "] " + msg);
    }

    public static void error(String msg) {
        System.out.println(getLoggingTime() + "[E][" + getCallerInfo() + "] " + msg);
    }

    public static void warn(String msg) {
        System.out.println(getLoggingTime() + "[W][" + getCallerInfo() + "] " + msg);
    }
}
