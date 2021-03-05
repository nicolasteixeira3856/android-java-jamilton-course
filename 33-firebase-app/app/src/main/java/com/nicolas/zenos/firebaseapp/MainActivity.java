package com.nicolas.zenos.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private ImageView imageFoto;
    private Button buttonUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageFoto = findViewById(R.id.imageFoto);
        buttonUpload = findViewById(R.id.buttonUpload);

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Configurando imagem para salvar na memória
                imageFoto.setDrawingCacheEnabled(true);
                imageFoto.buildDrawingCache();
                // Recupera o bitmap
                Bitmap bitmap = imageFoto.getDrawingCache();
                // Comprimindo bitmap para jpg/png
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream);
                // converter o bytearray em uma matriz de bytes
                byte[] dadosImg = byteArrayOutputStream.toByteArray();

                // Define nós para o storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("imagens");
                StorageReference imagemRef = imagens.child("celular.jpeg");

                // Loading de imagem do firebase dentro de uma imageview
                Glide.with(MainActivity.this)
                        .using(new FirebaseImageLoader())
                        .load(imagemRef)
                        .into(imageFoto);

                /*imagemRef.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,
                                "Erro ao deletar arquivo: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this,
                                "Sucesso ao deletar arquivo",
                                Toast.LENGTH_LONG).show();
                    }
                });*/

                //String nomeArquivo = UUID.randomUUID().toString();
                //StorageReference imagemRef = imagens.child(nomeArquivo);
                //StorageReference imagemRef = imagens.child("celular.jpeg");

                // Upload da imagem
                /*UploadTask uploadTask = imagemRef.putBytes(dadosImg);

                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,
                                "Upload de imagem falhou: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                Uri uri = task.getResult();
                                Toast.makeText(MainActivity.this,
                                        "Sucesso ao fazer upload de imagem: " + uri,
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });*/
            }
        });

        // Referência
        //DatabaseReference usuarios = databaseReference.child("usuarios");

        // Criando usuários no database com id único
        /*Usuario usuario = new Usuario();
        usuario.setName("Rodrigo");
        usuario.setSobrenome("Matos");
        usuario.setIdade(35);
        usuarios.push().setValue(usuario);*/

        // Filtros
        //DatabaseReference usuarioPesquisa = usuarios.child("-MUxDD034x9goyDUOiRb");
        //Query usuarioPesquisa = usuarios.orderByChild("name").equalTo("Rodrigo");
        //Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(2);
        //Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(20).endAt(35);
        /*Query usuarioPesquisa = usuarios.orderByChild("name").startAt("N").endAt("N" + "\uf8ff");
        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Usuario dadosUsuario = snapshot.getValue(Usuario.class);
                //Log.i("Dados usuário", dadosUsuario.getName());
                Log.i("Dados usuário", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        // Deslogar usuário
        //firebaseAuth.signOut();

        // Logar usuário
        /*firebaseAuth.signInWithEmailAndPassword("nicolasteixeira3856@outlook.com", "123456")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login realizado com sucesso", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Não foi possível logar", Toast.LENGTH_LONG).show();
                        }
                    }
                });
         */

        // Verifica usuário logado
        /*if(firebaseAuth.getCurrentUser() != null) {
            Toast.makeText(MainActivity.this, "Usuário logado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Usuário não logado", Toast.LENGTH_LONG).show();
        }*/

        // Cadastro de usuário (login)
        /*firebaseAuth.createUserWithEmailAndPassword("nicolasteixeira3856@outlook.com", "123456")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Não foi possível cadastrar", Toast.LENGTH_LONG).show();
                        }
                    }
                });*/

        //DatabaseReference usuarios = databaseReference.child("usuarios");

        // Criando usuários no database
        /*Usuario usuario = new Usuario();
        usuario.setName("Nicolas");
        usuario.setSobrenome("Teixeira");
        usuario.setIdade(22);
        usuarios.child("001").setValue(usuario);*/

        // Recuperando usuários com listener
        /*usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("FIREBASE", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }
}