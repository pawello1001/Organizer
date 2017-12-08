package com.example.pawe.organizer.flow.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        String getString = intent.getExtras().getString("extra");
        Integer songId = intent.getExtras().getInt("songId");

        Intent ringtoneIntent = new Intent(context, RingtonePlayingService.class);
        ringtoneIntent.putExtra("extra", getString);
        ringtoneIntent.putExtra("songId", songId);
        context.startService(ringtoneIntent);
    }
}
