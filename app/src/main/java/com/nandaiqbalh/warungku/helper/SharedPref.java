package com.nandaiqbalh.warungku.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.nandaiqbalh.warungku.model.User;

public class SharedPref {

    String myPref = "MAIN_PREF";
    SharedPreferences sp;

    String login = "login";
    public String name = "name";
    public String phone = "phone";
    public String email = "email";
    public String user = "user";

    Gson gson = new Gson();

    public SharedPref(Activity activity) {
        sp = activity.getSharedPreferences(myPref, Context.MODE_PRIVATE);
    }

    public void setStatusLogin(boolean statusLogin) {
        sp.edit().putBoolean(login, statusLogin).apply();

    }

    public boolean getStatusLogin() {
        return sp.getBoolean(login, false);
    }

    // Setter bertipe User untuk memanggil langsung semua field di dalam user, agar ga manggil satu per satu
    public void setUser(User value) {
        String data = gson.toJson(value, User.class); // ubah data dalam bentuk Object Class ke dalam bentuk String
        sp.edit().putString(String.valueOf(user), data).apply();
    }

    // Getter bertipe User untuk memanggil langsung semua field di dalam user, agar ga manggil satu per satu
    public User getUser(){
        String data;
        data = sp.getString(String.valueOf(this.user), null); // ubah data dalam bentuk String ke dalam bentuk Object Class

        if (data != null){
            return gson.fromJson(data, User.class);
        } else {
            return null;
        }
    }


    public void setString(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public String getString(String key){
        return sp.getString(key, "");
    }
}

