package com.paypagaar;

import android.app.Application;
import android.content.Context;

import com.paypagaar.util.LocaleHelper;

public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
}