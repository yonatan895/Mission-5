package com.example.question_five;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class WiFiBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            boolean disconnected = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);
            if(disconnected){
                Toast.makeText(context, "Disconnected!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Connected!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
