package com.nicolas.bahamut.organizze.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {
    private static FirebaseAuth autenticacao;
    private static DatabaseReference database;

    //retorna a instance
    public static FirebaseAuth getFirebaseAutenticacao() {
        if (autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }

    //retorna a instance
    public static DatabaseReference getFirebaseDatabase() {
        if (database == null) {
            database = FirebaseDatabase.getInstance().getReference();
        }
        return database;
    }
}
