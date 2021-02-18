package com.nicolas.zenos.toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirToast(View view) {
        //Toast Personalizado
        ImageView image = new ImageView(getApplicationContext());
        image.setImageResource(android.R.drawable.btn_plus);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(image);
        toast.show();

        //Toast Padrão
        /*Toast.makeText(
                getApplicationContext(),
                "Ação realizada com sucesso",
                Toast.LENGTH_LONG
        ).show();*/
    }
}