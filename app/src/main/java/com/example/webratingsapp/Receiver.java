package com.example.webratingsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "Data Received from External App", Toast.LENGTH_SHORT).show();
    }
}