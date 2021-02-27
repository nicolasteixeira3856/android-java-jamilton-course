package com.nicolas.zenos.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("app", MODE_PRIVATE, null);
            //Create table
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS pessoas (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3))");
            //Insert
            //sqLiteDatabase.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Nicolas', 22)");
            //sqLiteDatabase.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Tiago', 23)");
            //sqLiteDatabase.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Vitor', 21)");
            //sqLiteDatabase.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Jackson', 20)");
            //Recuperar dados
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT nome, idade from pessoas", null);

            //sqLiteDatabase.execSQL("UPDATE pessoas SET idade = 19 WHERE nome = 'Nicolas'");
            //sqLiteDatabase.execSQL("DROP TABLE pessoas");

            //Indices da tabela
            int indexId = cursor.getColumnIndex("id");
            int indexName = cursor.getColumnIndex("nome");
            int indexAge = cursor.getColumnIndex("idade");

            cursor.moveToFirst();

            while (cursor != null) {
                String id = cursor.getString(indexId);
                String name = cursor.getString(indexName);
                String age = cursor.getString(indexAge);
                Log.i("RESULTADO: ", id + "-" + name + " - " + age);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}