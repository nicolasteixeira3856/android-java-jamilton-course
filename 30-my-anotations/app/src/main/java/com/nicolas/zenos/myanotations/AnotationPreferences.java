package com.nicolas.zenos.myanotations;

import android.content.Context;
import android.content.SharedPreferences;


public class AnotationPreferences {
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String ARCHIVE_NAME = "anotacao.preferencias";
    private final String KEY_NAME = "nome";

    public AnotationPreferences(Context c) {
        this.context = c;
        preferences = context.getSharedPreferences(ARCHIVE_NAME,0);
        editor = preferences.edit();
    }

    public void saveAnotation(String anotacao){
        editor.putString(KEY_NAME, anotacao );
        editor.commit();
    }

    public String loadAnotation(){
        return preferences.getString(KEY_NAME, "");
    }
}
