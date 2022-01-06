package com.example.android_apprentice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class Apprentice_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Receiver","Receive BroadCast");
        switch(intent.getAction())
        {
            case "com.example.android_apprentice.MY_BC":
                Toast.makeText(context, "Received broadcase from android_apprentice", Toast.LENGTH_SHORT).show();
                break;
            case "android.intent.action.AIRPLANE_MODE":
                Toast.makeText(context, "Airplane mode changed", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
