package com.example.pawe.organizer.flow.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.example.pawe.organizer.R;
import com.example.pawe.organizer.flow.activities.SingleAlarmActivity;

public class RingtonePlayingService extends Service {

    private MediaPlayer mediaPlayer;
    private int startId;
    private boolean isRunning;

    private RemoteViews mNotificationLayout;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String state = intent.getExtras().getString("extra");
        int songId = intent.getExtras().getInt("songId");

        assert state != null;

        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        if (!isRunning && startId == 1) {
            switch (songId) {
                case 0:
                    mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    isRunning = true;
                    this.startId = 0;
                    break;
                case 1:
                    mediaPlayer = MediaPlayer.create(this, R.raw.apple_ring);
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    isRunning = true;
                    this.startId = 0;
                    break;
                case 2:
                    mediaPlayer = MediaPlayer.create(this, R.raw.best_wake_up_sound);
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    isRunning = true;
                    this.startId = 0;
                    break;
                case 3:
                    mediaPlayer = MediaPlayer.create(this, R.raw.car_lock);
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    isRunning = true;
                    this.startId = 0;
                    break;
                case 4:
                    mediaPlayer = MediaPlayer.create(this, R.raw.extreme_alarm_clock);
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    isRunning = true;
                    this.startId = 0;
                    break;
                case 5:
                    mediaPlayer = MediaPlayer.create(this, R.raw.morning_alarm);
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    isRunning = true;
                    this.startId = 0;
                    break;
                case 6:
                    mediaPlayer = MediaPlayer.create(this, R.raw.real_loud_alarm);
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    isRunning = true;
                    this.startId = 0;
                    break;
                case 7:
                    mediaPlayer = MediaPlayer.create(this, R.raw.russian_electro);
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    isRunning = true;
                    this.startId = 0;
                    break;
                case 8:
                    mediaPlayer = MediaPlayer.create(this, R.raw.siren);
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    isRunning = true;
                    this.startId = 0;
                    break;
                case 9:
                    mediaPlayer = MediaPlayer.create(this, R.raw.voo_voo_sms);
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    isRunning = true;
                    this.startId = 0;
                    break;
                default:
                    mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    isRunning = true;
                    this.startId = 0;
                    break;
            }

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Intent mainActivityIntent = new Intent(getApplicationContext(), SingleAlarmActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mainActivityIntent, 0);

            Notification notification = new Notification.Builder(this)
                    .setContentTitle(getResources().getString(R.string.alarm_turn_off))
                    .setContentText(getResources().getString(R.string.alarm_click_me))
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_alarm)
                    .setAutoCancel(true)
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_alarm))
                    .build();

            notificationManager.notify(0, notification);
        } else if (isRunning && startId == 0) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            isRunning = false;
            this.startId = 0;
        } else { }



        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }
}
