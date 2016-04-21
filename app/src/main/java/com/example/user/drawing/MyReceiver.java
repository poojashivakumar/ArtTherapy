package com.example.user.drawing;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(Intent.ACTION_USER_PRESENT))
        {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
            mBuilder.setContentTitle("Notification Alert from Art Therapy");
            mBuilder.setContentText("Do you wish to start drawing");
            mBuilder.setSmallIcon(android.R.drawable.ic_menu_day);
            mBuilder.setAutoCancel(true);

            Intent newIntent = new Intent(context,MainActivity.class);
            PendingIntent intent2 = PendingIntent.getActivity(context,0,newIntent,0);
            mBuilder.setContentIntent(intent2);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, mBuilder.build());
        }
    }
}
