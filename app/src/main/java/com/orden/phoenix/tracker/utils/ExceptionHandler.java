package com.orden.phoenix.tracker.utils;

import android.app.Activity;
import android.os.Build;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by I_van on 04.05.2014.
 */
public class ExceptionHandler implements java.lang.Thread.UncaughtExceptionHandler {
    private final Activity context;
    private final String LINE_SEPARATOR = "\n";

    public ExceptionHandler(Activity context) {
        this.context = context;
    }

    public void uncaughtException(Thread thread, Throwable exception) {
        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));

        ConsoleLogger.e(String.valueOf(context)
                + ": "
                + "************ CAUSE OF ERROR ************\n\n"
                + stackTrace.toString()
                + "\n************ DEVICE INFORMATION ***********\n"
                + "Brand: " + Build.BRAND + LINE_SEPARATOR
                + "Device: " + Build.DEVICE + LINE_SEPARATOR
                + "Model: " + Build.MODEL + LINE_SEPARATOR
                + "Id: " + Build.ID + LINE_SEPARATOR
                + "Product: " + Build.PRODUCT + LINE_SEPARATOR
                + "\n************ FIRMWARE ************\n"
                + "SDK: " + Build.VERSION.SDK + LINE_SEPARATOR
                + "Release: " + Build.VERSION.RELEASE + LINE_SEPARATOR
                + "Incremental: " + Build.VERSION.INCREMENTAL + LINE_SEPARATOR);

        thread.interrupt();
    }
}