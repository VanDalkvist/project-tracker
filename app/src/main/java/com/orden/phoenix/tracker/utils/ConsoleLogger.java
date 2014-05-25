package com.orden.phoenix.tracker.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by I_van on 24.05.2014.
 */
public class ConsoleLogger {

    private String Tag;

    public ConsoleLogger(String tag) {
        Tag = tag;
    }

    private static String getLocation() {
        final String className = ConsoleLogger.class.getName();
        final StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        boolean found = false;

        for (StackTraceElement trace : traces) {
            try {
                String tracedClassName = trace.getClassName();
                boolean startsWith = tracedClassName.startsWith(className);
                if (found) {
                    if (!startsWith) {
                        Class<?> clazz = Class.forName(tracedClassName);
                        return "[" + getClassName(clazz) + ":" + trace.getMethodName() + ":" + trace.getLineNumber() + "]: ";
                    }
                } else if (startsWith) found = true;
            } catch (ClassNotFoundException ignored) {
            }
        }

        return "[]: ";
    }

    private static String getClassName(Class<?> clazz) {
        if (clazz == null) return "";

        String simpleName = clazz.getSimpleName();
        if (!TextUtils.isEmpty(simpleName)) return simpleName;

        return getClassName(clazz.getEnclosingClass());
    }

    public void v(String msg) {
        write(Log.VERBOSE, msg);
    }

    public void d(String msg) {
        write(Log.DEBUG, msg);
    }

    public void w(String msg) {
        write(Log.WARN, msg);
    }

    public void e(String msg) {
        write(Log.ERROR, msg);
    }

    public void i(String msg) {
        write(Log.INFO, msg);
    }

    private void write(int priority, String msg) {
        Log.println(priority, Tag, getLocation() + msg);
    }
}