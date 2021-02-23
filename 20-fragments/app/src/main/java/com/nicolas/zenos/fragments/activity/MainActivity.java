package com.nicolas.zenos.fragments.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nicolas.zenos.fragments.R;
import com.nicolas.zenos.fragments.fragment.ChatsFragment;
import com.nicolas.zenos.fragments.fragment.ContactsFragment;

public class MainActivity extends AppCompatActivity {

    private Button buttonChat, buttonContact;
    private ChatsFragment chatsFragment;
    private ContactsFragment contactsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatsFragment = new ChatsFragment();

        //Configurar objeto para o fragmento
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContent, chatsFragment);
        transaction.commit();

        /*buttonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactsFragment = new ContactsFragment();
                //Configurar objeto para o fragmento
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameContent, contactsFragment);
                transaction.commit();
            }
        });

        buttonChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatsFragment = new ChatsFragment();
                //Configurar objeto para o fragmento
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameContent, chatsFragment);
                transaction.commit();
            }
        });*/
    }
}