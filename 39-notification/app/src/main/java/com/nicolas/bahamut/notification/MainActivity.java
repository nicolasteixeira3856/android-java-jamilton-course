package com.nicolas.bahamut.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Ativar tópicos
            Brasileira
            Japonesa
            Chinesa
         */

        FirebaseMessaging.getInstance().subscribeToTopic("japonesa");
        //FirebaseMessaging.getInstance().unsubscribeFromTopic("japonesa");
    }

    public void recuperarToken(View view) {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String token = instanceIdResult.getToken();
                Log.d("Token", "onSuccess: " + token);
            }
        });
    }
}