package com.nat.device_base;

import android.content.Context;
import android.provider.Settings;

import java.util.HashMap;

/**
 * Created by xuqinchao on 17/2/7.
 * Copyright (c) 2017 Nat. All rights reserved.
 */

public class HLBaseModule{
    private String mUuid;

    private Context mContext;
    private static volatile HLBaseModule instance = null;

    private HLBaseModule(Context context){
        mContext = context;
    }

    public static HLBaseModule getInstance(Context context) {
        if (instance == null) {
            synchronized (HLBaseModule.class) {
                if (instance == null) {
                    instance = new HLBaseModule(context);
                }
            }
        }

        return instance;
    }

    public void info(HLModuleResultListener listener){
        HashMap<String, String> result = new HashMap<>();
        result.put("model", getModel());
        result.put("vendor", getManufacturer());
        result.put("uuid", getUuid());
        result.put("platform", "Android");
        result.put("version", getOSVersion());
        listener.onResult(result);
    }
    public String getUuid() {
        if (mUuid == null) {
            mUuid = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return mUuid;
    }

    public String getModel() {
        String model = android.os.Build.MODEL;
        return model;
    }

    public String getManufacturer() {
        String manufacturer = android.os.Build.MANUFACTURER;
        return manufacturer;
    }

    public String getOSVersion() {
        String osversion = android.os.Build.VERSION.RELEASE;
        return osversion;
    }
}
