package com.example.maiajam.medcinealram;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.Locale;

public class prefrenceSetting extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {


    Locale locale ;


    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

            if (key.equals("lang"))

            {

                String pre = sp.getString("lang", null);

                if (pre == "العربية") {

                    locale = new Locale("ar");
                    Resources resources = getResources();
                    DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                    Configuration con = resources.getConfiguration();
                    con.locale = locale;
                    resources.updateConfiguration(con, displayMetrics);
                    Intent i = new Intent(getBaseContext(), prefrenceSetting.class);
                    startActivity(i);

                } else {
                    if (pre == "Engilsh") {

                        locale = new Locale("en");
                        Resources resources = getResources();
                        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                        Configuration con = resources.getConfiguration();
                        con.locale = locale;
                        resources.updateConfiguration(con, displayMetrics);
                        Intent i = new Intent(getBaseContext(), prefrenceSetting.class);
                        startActivity(i);

                    }
                }
            } else if (key.equals("vibrate")) {


                if (sp.getBoolean("vibrate", false)) {
                    AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                } else {
                    AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

                    audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                }
            } else if (key.equals("light")) {

                if (sp.getBoolean("light", false)) {

                } else {

                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefrence_setting);


        getFragmentManager().beginTransaction().replace(R.id.cont,new FragmentPrefrence()).commit();

        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }


    @Override
    protected void onResume() {
        super.onResume();

        PreferenceManager.getDefaultSharedPreferences(getBaseContext()).registerOnSharedPreferenceChangeListener(listener);

    }

    @Override
    protected void onPause() {
        super.onPause();

        PreferenceManager.getDefaultSharedPreferences(getBaseContext()).unregisterOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        if (key.equals("lang"))

        {

            String pre = sp.getString("lang", null);

            if (pre == "العربية") {

                locale = new Locale("ar");
                Resources resources = getResources();
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                Configuration con = resources.getConfiguration();
                con.locale = locale;
                resources.updateConfiguration(con, displayMetrics);
                Intent i = new Intent(this, prefrenceSetting.class);
                startActivity(i);

            } else if (pre == "Engilsh") {

                locale = new Locale("en");
                Resources resources = getResources();
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                Configuration con = resources.getConfiguration();
                con.locale = locale;
                resources.updateConfiguration(con, displayMetrics);
                Intent i = new Intent(this, prefrenceSetting.class);
                startActivity(i);

            }
        } else if (key.equals("vibrate")) {


            if (sp.getBoolean("vibrate", false)) {
                AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            } else {
                AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            }
        } else if (key.equals("light")) {

            if (sp.getBoolean("light", false)) {

            } else {

            }


        }


    }
}
