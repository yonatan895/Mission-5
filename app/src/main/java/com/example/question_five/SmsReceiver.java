package com.example.question_five;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
private static final  String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
private static final String TAG = "SmsBroadcastReceiver";
String msg = "";
String phoneNum = "";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "received: "+intent.getAction());
        if(intent.getAction()==SMS_RECEIVED){
            Bundle bundle = intent.getExtras();
            if(bundle!=null){
                Object[] obj = (Object[]) bundle.get("pdus");
                final SmsMessage[] message = new SmsMessage[obj.length];
                for(int i =0;i<obj.length;i++){
                    if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
                        String fm = bundle.getString("format");
                        message[i] = SmsMessage.createFromPdu((byte[])obj[i],fm);
                    }
                    else {
                        message[i] =SmsMessage.createFromPdu((byte[])obj[i]);
                    }
                    msg = message[i].getMessageBody();
                    phoneNum = message[i].getOriginatingAddress();
                }
                Toast.makeText(context, "Sender: "+phoneNum+"\nMessage: "+msg, Toast.LENGTH_SHORT).show();
            }
        }
    }
}