package com.example.maiajam.medcinealram.util.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.example.maiajam.medcinealram.helper.NotificationHelper.notifyMyAboutTheMedcine;

/**
 * Created by maiAjam on 9/11/2017.
 */

public class AlarmReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        resetTheNextRepetedAlarm();
        getMedInfoAndNotifyUser(context, intent);
    }

    private void resetTheNextRepetedAlarm() {

    }

    private void getMedInfoAndNotifyUser(Context context, Intent intent) {
        String med_name = intent.getStringExtra("med_name");
        String med_note = intent.getStringExtra("med_note");
        String med_Dose = intent.getStringExtra("med_dose");
        String med_member = intent.getStringExtra("med_member");

        notifyMyAboutTheMedcine(context, med_name, med_note, med_member);
    }

}
