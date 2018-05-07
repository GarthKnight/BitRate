package com.bitrate;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by seishu on 05.05.18.
 */

public class BitApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
