package com.nicolas.zenos.taskslist.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NAME_DB = "DB_TASKS";
    public static String TASKS_TABLE = "tasks";

    public DbHelper(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TASKS_TABLE
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL);";

        try {
            db.execSQL(sql);
            Log.i("INFO DB", "Sucesso ao criar a tabela");
        } catch (Exception e) {
            Log.i("INFO DB", "Erro ao criar a tabela" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Utilizado quando a VERSION é atualizada, pode dropar tabela, refazer estruturas etc.
    }
}
