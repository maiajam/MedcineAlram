package com.example.maiajam.medcinealram;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.maiajam.medcinealram.helper.HelperMethodes;
import com.example.maiajam.medcinealram.util.prefrenceSetting;

import java.util.Locale;

import static com.example.maiajam.medcinealram.helper.Global.English;
import static com.example.maiajam.medcinealram.helper.Global.arabic;
import static com.example.maiajam.medcinealram.helper.HelperMethodes.setSelectedLanguage;

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
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                if(newValue instanceof String)
                {

                    String value = (String)newValue ;

                    if(value.equalsIgnoreCase("العربية") )
                    {
                        setSelectedLanguage(getContext(), arabic);
                       HelperMethodes.setAppLanguage(getContext(),arabic);
                        Intent i = new Intent(getActivity(), prefrenceSetting.class);
                        startActivity(i);

                    } else {
                        if (value.equalsIgnoreCase("English")) {
                            setSelectedLanguage(getContext(),English);
                            HelperMethodes.setAppLanguage(getContext(),English);
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
