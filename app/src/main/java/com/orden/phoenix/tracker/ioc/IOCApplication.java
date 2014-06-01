package com.orden.phoenix.tracker.ioc;

import android.app.Application;

import roboguice.RoboGuice;

/**
 * Created by I_van on 01.06.2014.
 */
public class IOCApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();    //To change body of overridden methods use File | Settings | File Templates.

        RoboGuice.setBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE, RoboGuice.newDefaultRoboModule(this), new IOCModule());
    }
}