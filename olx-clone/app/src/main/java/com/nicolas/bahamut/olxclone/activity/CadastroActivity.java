package com.nicolas.bahamut.olxclone.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.nicolas.bahamut.olxclone.R;
import com.nicolas.bahamut.olxclone.helper.ConfiguracaoFirebase;

public class CadastroActivity extends AppCompatActivity {

    private Button botaoAcessar;
    private EditText campoEmail, campoSenha;
    private Switch tipoAcesso;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializarComponentes();
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        botaoAcessar.setOnClickListener(v -> {
            String email = campoEmail.getText().toString();
            String senha = campoSenha.getText().toString();

            if (!email.isEmpty() && !senha.isEmpty()) {
                if (tipoAcesso.isChecked()) { //Cadastro
                    autenticacao.createUserWithEmailAndPassword(
                            email, senha
                    ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show();
                                //Direcionar usuário
                            } else {
                                String erroExcecao = "";
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthWeakPasswordException e) {
                                    erroExcecao = "Digite uma senha mais forte!";
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    erroExcecao = "Por favor, digite um e-mail válido";
                                } catch (FirebaseAuthUserCollisionException e) {
                                    erroExcecao = "Este e-mail já foi cadastrado";
                                } catch (Exception e) {
                                    erroExcecao = "Erro ao cadastrar usuário" + e.getMessage();
                                    e.printStackTrace();
                                }
                                Toast.makeText(CadastroActivity.this, erroExcecao, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else { //Login
                    autenticacao.signInWithEmailAndPassword(
                            email, senha
                    ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(), AnunciosActivity.class));
                            } else {
                                Toast.makeText(CadastroActivity.this, "Erro ao fazer login " + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void inicializarComponentes() {
        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);
        botaoAcessar = findViewById(R.id.buttonAcesso);
        tipoAcesso = findViewById(R.id.switchAcesso);
    }
}