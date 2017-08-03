package com.hmp.android.datapoints;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;


/**
 * Created by harsh.arora on 7/18/2017.
 */
public class SmsReceiver extends BroadcastReceiver {

    //interface
    private static SmsListner mListener;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {

            Bundle data = intent.getExtras();

            Log.d("Message", "onReceive: "+ data);

            if (data != null){

                Object[] pdus = (Object[]) data.get("pdus");

                for (int i = 0; i < pdus.length; i++) {
                    SmsMessage smsMessage = null;

                    smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);


                    String sender = smsMessage.getOriginatingAddress();

                    Log.d("Message", "onReceive:" + sender);
                    //Check the sender to filter messages which we require to read

         /*   if (sender.equals("GADGETSAINT"))
            {

                String messageBody = smsMessage.getMessageBody();

                //Pass the message text to interface
                mListener.messageReceived(messageBody);

            }   */
                    mListener.messageReceived(sender);

                }
            }
            else{
                Log.d("Message", "onReceive: Bundle null hai ");
            }


        }
    }
    public static void bindListener(SmsListner listener) {

        mListener = listener;
    }
}
