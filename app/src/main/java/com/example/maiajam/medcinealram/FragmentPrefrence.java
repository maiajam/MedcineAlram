package com.example.maiajam.medcinealram;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

import static android.content.Context.AUDIO_SERVICE;

/**
 * Created by maiAjam on 9/12/2017.
 */

public class FragmentPrefrence extends PreferenceFragment {


    Locale locale ;
    private ListPreference listPreference ;
    private SwitchPreference switchVibrate ,switchLight ;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.setting);

        listPreference =(ListPreference)getPreferenceManager().findPreference("lang");

        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                if(newValue instanceof String)
                {

                    String value = (String)newValue ;

                    if(value == "العربية")
                    {
                        locale = new Locale("ar");
                        Resources resources = getResources();
                        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                        Configuration con = resources.getConfiguration();
                        con.locale = locale;
                        resources.updateConfiguration(con, displayMetrics);
                        Intent i = new Intent(getActivity(), prefrenceSetting.class);
                        startActivity(i);

                    } else {
                        if (value == "Engilsh") {

                            locale = new Locale("en");
                            Resources resources = getResources();
                            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                            Configuration con = resources.getConfiguration();
                            con.locale = locale;
                            resources.updateConfiguration(con, displayMetrics);
                            Intent i = new Intent(getActivity(), prefrenceSetting.class);
                            startActivity(i);

                        }
                    }
                }
                return true;
            }
        });


        switchVibrate =(SwitchPreference)getPreferenceManager().findPreference("vibrate");

        switchVibrate.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                if(newValue instanceof Boolean)
                {
                    Boolean value = (Boolean)newValue ;

                    if (value) {
                        AudioManager audioManager = (AudioManager) getActivity().getSystemService(getActivity().AUDIO_SERVICE);

                        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    } else {
                        AudioManager audioManager = (AudioManager)getActivity().getSystemService(getActivity().AUDIO_SERVICE);

                        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    }
                }
                return true;
            }
        });

        switchLight =(SwitchPreference)getPreferenceManager().findPreference("light");

        switchLight.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                if(newValue instanceof Boolean)
                {
                    Boolean value = (Boolean)newValue ;

                }

                return true;
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onPause() {
        super.onPause();

    }


}
