package com.example.maiajam.medcinealram.helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.util.DisplayMetrics;

import com.example.maiajam.medcinealram.R;

import java.util.Locale;

public class HelperMethodes {

    private static Locale locale;
    String selectedLang;

    private static String SELECTED_lANG ="selectedLanguage";
    static SharedPreferences selectedLangSP ;

    public static void setSelectedLanguage(Context context,String lang) {

        selectedLangSP = context.getSharedPreferences(SELECTED_lANG,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = selectedLangSP.edit();
        editor.putString(SELECTED_lANG,lang);
        editor.apply();
        editor.commit();
    }

    public static String getSelectedLangSP(Context context) {
        selectedLangSP = context.getSharedPreferences(SELECTED_lANG,Context.MODE_PRIVATE);
        return  selectedLangSP.getString(SELECTED_lANG,"en");
    }


    public static void setAppLanguage(Context context,String appLang) {

        locale = new Locale(appLang);
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration con = resources.getConfiguration();
        con.locale = locale;
        con.setLayoutDirection(locale);
        resources.updateConfiguration(con, displayMetrics);

    }

    public static void notifyMyAboutTheMedcine(Context context,String med_name,String med_note,String med_Dose) {

        String chanelId = "10";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence namE = context.getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(chanelId, namE, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder;

        builder = new NotificationCompat.Builder(context, "v")
                .setSmallIcon(R.mipmap.logo)
                .setContentTitle(med_name )
                .setContentText(med_note + med_Dose)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setCategory(NotificationCompat.CATEGORY_EVENT)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setChannelId(chanelId)
                .setOnlyAlertOnce(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(med_note))
                .setAutoCancel(true)
        ;


        Notification notification = builder.build();
        notification.defaults = Notification.DEFAULT_ALL;
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify((int) System.currentTimeMillis(), notification);

    }

    public static void setLayoutDirction(Context context, String locale) {

        Configuration config;
        config = new Configuration(context.getResources().getConfiguration());

        switch (locale) {
            case "ar":
                config.locale = new Locale("ar");
                config.setLayoutDirection(new Locale("ar"));
                break;
            case "en":
                config.locale = new Locale("en");
                config.setLayoutDirection(new Locale("en"));
                break;
        }

        context.getResources().updateConfiguration(config,context.getResources().getDisplayMetrics());


    }
}
