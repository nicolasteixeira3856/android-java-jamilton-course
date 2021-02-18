package com.nicolas.zenos.components;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput, emailInput;
    private TextView resultText;

    private CheckBox checkGreen, checkWhite, checkRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inputs
        nameInput       = findViewById(R.id.inputName);
        emailInput      = findViewById(R.id.inputEmail);
        resultText      = findViewById(R.id.textResult);
        //Checkbox
        checkGreen      = findViewById(R.id.checkGreen);
        checkWhite      = findViewById(R.id.checkWhite);
        checkRed        = findViewById(R.id.checkRed);
        //Radio button é um processo bem parecido com checkbox, sendo que o requisito para deixar apenas uma
        //opção é utilizar um radio group, também pode utilizar o radiogroup como variavel, por exemplo:
        //private RadioGroup = radiogroup, com isso é possivel utilizar uma função chamada
        //setOnCheckedListener(new RadioGroup.OnCheckedChangeListener(){@override public void
        //onCheckedChanged(RadioGroup group, int checkedId) if (checkedId = o id do componente na view).
    }

    public String checkbox(){
        String text = "";

        /*
        String selectedColor = checkGreen.getText().toString();
         */
        if (checkGreen.isChecked()) {
            text = "Verde selecionado ";
        }
        if (checkWhite.isChecked()) {
            text = text + "- Branco selecionado - ";
        }
        if (checkRed.isChecked()) {
            text = text + "- Vermelho selecionado - ";
        }

        return text;
    }

    public void submit(View view) {
        String checked = checkbox();
        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();

        resultText.setText("Nome: " + name + " E-mail:" + email + " Selecionado:" + checked);
    }

    public void clear(View view) {
        nameInput.setText("");
        emailInput.setText("");
    }
}