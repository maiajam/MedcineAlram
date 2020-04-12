package com.example.maiajam.medcinealram.util;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.maiajam.medcinealram.R;

/**
 * Created by maiAjam on 9/11/2017.
 */

public class notificationService extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public notificationService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


        String med_name = intent.getStringExtra("med_name");
        String med_note = intent.getStringExtra("med_note");
        String med_member = intent.getStringExtra("med_member");


        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext())
                .setSmallIcon(R.drawable.ic_menu_camera)
                .setContentTitle("")
                .setContentText("");


        Notification notification  = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        NotificationManager notifMang = (NotificationManager)getBaseContext().getSystemService(NOTIFICATION_SERVICE);


        notifMang.notify(1,builder.build() );



    }
}
