package com.example.maiajam.medcinealram;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by maiAjam on 9/11/2017.
 */

public class reciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

       String med_name =intent.getStringExtra("med_name");
        String med_note = intent.getStringExtra("med_note");
        String med_Dose = intent.getStringExtra("med_dose");
        String med_member= intent.getStringExtra("med_member");

        Intent s = new Intent(context,notificationService.class);

        s.putExtra("med_name",med_name);
        s.putExtra("med_note",med_note);

        s.putExtra("med_member",med_member);

        context.startService(s);


    }
}
