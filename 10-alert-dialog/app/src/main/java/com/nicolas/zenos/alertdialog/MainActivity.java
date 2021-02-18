package com.nicolas.zenos.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openDialog(View view) {
        //Instanciar dialog
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        //Configurar título e mensagem
        alertDialog.setTitle("Título da Dialog");
        alertDialog.setMessage("Mensagem da Dialog");
        //Obrigar usuário a clicar em alguma opção
        alertDialog.setCancelable(false);
        alertDialog.setIcon(android.R.drawable.star_on);
        //Configurar acoes para sim e nao
        alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Executar a ação ao clicar ao botão sim", Toast.LENGTH_LONG).show();
            }
        });
        alertDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Executar a ação ao clicar ao botão não", Toast.LENGTH_LONG).show();
            }
        });
        //Criar e exibir alert dialog
        alertDialog.create();
        alertDialog.show();
    }
}