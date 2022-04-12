package com.example.android_apprentice;

public class QLog_JNI {
    static{
        System.loadLibrary("QLog_JNI");
    }
    //Native method
    public static native String Qlog_Start(int display_type);
}
