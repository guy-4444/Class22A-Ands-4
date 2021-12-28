package com.guy.class22a_ands_4;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        MSPS.initHelper(this);
        Regular_MSP.initHelper(this);
    }
}
