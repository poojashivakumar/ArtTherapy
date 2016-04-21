package com.example.user.drawing;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;

public class AudioIntentService extends IntentService {

    MediaPlayer mediaPlayer;
    public AudioIntentService()
    {
        super("AudioIntentService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        synchronized(this)
        {
            mediaPlayer = MediaPlayer.create(this, R.raw.eraser);
            mediaPlayer.start();
        }

    }
}
