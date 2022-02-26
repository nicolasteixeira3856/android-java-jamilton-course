package com.nicolas.bahamut.organizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.nicolas.bahamut.organizze.R;
import com.nicolas.bahamut.organizze.config.ConfiguracaoFirebase;
import com.nicolas.bahamut.organizze.helper.Base64Custom;
import com.nicolas.bahamut.organizze.helper.DateUtil;
import com.nicolas.bahamut.organizze.model.Movimentacao;
import com.nicolas.bahamut.organizze.model.Usuario;

public class DespesasActivity extends AppCompatActivity {

    private EditText camporValor;
    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private Movimentacao movimentacao;
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private Double despesaTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        camporValor = findViewById(R.id.editValor);
        campoData = findViewById(R.id.editData);
        campoCategoria = findViewById(R.id.editCategoria);
        campoDescricao = findViewById(R.id.editDescricao);

        // Preenche o campo data com a data atual
        campoData.setText(DateUtil.dataAtual());

        recuperarDespesaTotal();
    }

    public void salvarDespesa (View view) {
        if (validarCamposDespesas()) {
            String data = campoData.getText().toString();
            Double valor = Double.parseDouble(camporValor.getText().toString());
            movimentacao = new Movimentacao();
            movimentacao.setValor(valor);
            movimentacao.setCategoria(campoCategoria.getText().toString());
            movimentacao.setDescricao(campoDescricao.getText().toString());
            movimentacao.setData(data);
            movimentacao.setTipo("d");
            Double despesaAtualizada = despesaTotal + valor;
            atualizarDespesa(despesaAtualizada);
            movimentacao.salvar(data);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "É necessário preencher todos os campos", Toast.LENGTH_LONG).show();
        }
    }

    public Boolean validarCamposDespesas () {

        return !campoData.getText().toString().isEmpty() &&
                !camporValor.getText().toString().isEmpty() &&
                !campoCategoria.getText().toString().isEmpty() &&
                !campoDescricao.getText().toString().isEmpty();
    }

    public void recuperarDespesaTotal () {
        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuario = snapshot.getValue( Usuario.class );
                despesaTotal = usuario.getDespesaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void atualizarDespesa (double despesa) {
        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);
        usuarioRef.child("despesaTotal").setValue(despesa);
    }
}