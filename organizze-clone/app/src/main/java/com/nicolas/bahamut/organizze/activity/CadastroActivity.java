package com.nicolas.bahamut.organizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.nicolas.bahamut.organizze.R;
import com.nicolas.bahamut.organizze.config.ConfiguracaoFirebase;
import com.nicolas.bahamut.organizze.helper.Base64Custom;
import com.nicolas.bahamut.organizze.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    private Button botaoCadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().setTitle("Cadastro");

        campoNome = findViewById(R.id.editNome);
        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);

        botaoCadastrar = findViewById(R.id.botaoCadastro);
        botaoCadastrar.setOnClickListener(view -> {
            String textoNome = campoNome.getText().toString();
            String textoEmail = campoEmail.getText().toString();
            String textoSenha = campoSenha.getText().toString();

            // Validacao de campos preenchidos

            if (textoNome.isEmpty() || textoEmail.isEmpty() || textoSenha.isEmpty()) {
                Toast.makeText(
                        getApplicationContext(),
                        "Por favor preencha todos os campos",
                        Toast.LENGTH_LONG
                ).show();
            } else {
                usuario = new Usuario();
                usuario.setNome(textoNome);
                usuario.setEmail(textoEmail);
                usuario.setSenha(textoSenha);
                cadastrarUsuario();
            }
        });
    }

    public void cadastrarUsuario() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
        .addOnCompleteListener(this, task -> {
            if ( task.isSuccessful() ) {
                Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                usuario.setIdUsuario(idUsuario);
                usuario.salvar();
                finish();
            } else {
                String excecao = "Erro ao cadastrar usuário";
                try {
                    throw task.getException();
                } catch (FirebaseAuthWeakPasswordException e) {
                    excecao = "Digite uma senha mais forte!";
                } catch (FirebaseAuthInvalidCredentialsException e) {
                    excecao = "Por favor digite um e-mail válido";
                } catch (FirebaseAuthUserCollisionException e) {
                    excecao = "Esta conta já foi cadastrada";
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Toast.makeText(
                    getApplicationContext(),
                    excecao,
                    Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}