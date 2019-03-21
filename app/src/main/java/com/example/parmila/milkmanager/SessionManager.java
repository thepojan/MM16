package com.example.parmila.milkmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.parmila.milkmanager.Activities.Catalog;
import com.example.parmila.milkmanager.Activities.login;

import java.util.HashMap;

//import java.util.HashMap;

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int mode=0;
    String Filename="sdfile";
    String Data="b";
  //  public static final String PREF_NAME="MM";
   // public static final String IS_LOGIN="IsLoggedIn";
   // public static final String KEY_EMAIL="email";
  //  public static final String KEY_PASS="pass";

        public SessionManager(Context context)
        {
            this.context=context;
            pref=context.getSharedPreferences(Filename,mode);
            editor=pref.edit();
        }

        public void secondTime()
        {
            editor.putBoolean(Data,true);
        }

    /*    public void firstTime()
        {
            if(!this.login())
            {
                Intent i=new Intent(context, login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);
            }
        }
        */
    public boolean login()
    {
        return pref.getBoolean(Data,false);
    }

}
