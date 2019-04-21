package com.example.parmila.milkmanager.MySession;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class PreferenceUtils {
    public static boolean saveEmail(String email, Context context)
    {
        SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefssEditor=prefs.edit();
        prefssEditor.putString(Constants.KEY_EMAIL,email);
        prefssEditor.apply();
        return true;
    }

    public static String getEmail(Context context)
    {
        SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(Constants.KEY_EMAIL,null);
    }


    public static boolean savePassword(String pass, Context context)
    {
        SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefssEditor=prefs.edit();
        prefssEditor.putString(Constants.KEY_PASSWORD,pass);
        prefssEditor.apply();
        return true;
    }

    public static String getPassword(Context context)
    {
        SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(Constants.KEY_PASSWORD,null);
    }
}
