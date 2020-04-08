package com.example.maiajam.medcinealram.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

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
        resources.updateConfiguration(con, displayMetrics);
    }
}
