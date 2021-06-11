package com.nandaiqbalh.warungku.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    String myPref = "MAIN_PREF";
    SharedPreferences sp;

    String login = "login";

    public SharedPref(Activity activity) {
        sp = activity.getSharedPreferences(myPref, Context.MODE_PRIVATE);
    }

    public void setStatusLogin(boolean statusLogin) {
        sp.edit().putBoolean(login, statusLogin).apply();

    }

    public boolean getStatusLogin() {
        return sp.getBoolean(login, false);
    }
}

