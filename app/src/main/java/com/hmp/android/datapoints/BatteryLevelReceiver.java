package com.hmp.android.datapoints;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by harsh.arora on 7/17/2017.
 */
public class BatteryLevelReceiver extends BroadcastReceiver {

    public BatteryLevelReceiver(){

    }
    private static BatteryListner bListener;


    @Override
    public void onReceive(Context context, Intent intent) {


        Log.d("Harsh", "onReceive: Entring in to Receiver Class");
        if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
            Bundle extras = intent.getExtras();
            if (extras != null) {

                Log.d("Harsh", "onReceive: Entring in to Extras.");
                Toast.makeText(context, "Charger Connected", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(context, "Battery is Low", Toast.LENGTH_SHORT).show();
        }

        bListener.BatteryStatus("Low Battery");
    }

    public static void bindListener(BatteryListner listener) {

        bListener =  listener;
    }
    }
