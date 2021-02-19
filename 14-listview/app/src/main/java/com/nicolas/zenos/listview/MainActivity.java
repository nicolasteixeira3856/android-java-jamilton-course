package com.nicolas.zenos.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listPlaces;
    private String[] itens = {
        "Canadá",
        "México",
        "Estados Unidos",
        "Suécia",
        "Suiça",
        "Aústria",
        "Japão",
        "Argentina",
        "Itália",
        "Turquia",
        "China",
        "Chile",
        "Colombia",
        "Venezuela"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listPlaces = findViewById(R.id.listPlaces);
        //Criar um adaptador para a lista
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
            getApplicationContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            itens
        );
        //Adicionar o adaptador na lista
        listPlaces.setAdapter(adapter);

        listPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = listPlaces.getItemAtPosition(position).toString();
                Toast.makeText(
                        getApplicationContext(),
                        selected,
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}