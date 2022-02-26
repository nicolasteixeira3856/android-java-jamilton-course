package com.nicolas.bahamut.olxclone.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nicolas.bahamut.olxclone.R;
import com.nicolas.bahamut.olxclone.helper.ConfiguracaoFirebase;
import com.nicolas.bahamut.olxclone.helper.Permissoes;
import com.nicolas.bahamut.olxclone.model.Anuncio;
import com.santalu.maskara.widget.MaskEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;

public class CadastrarAnuncioActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText campoTitulo, campoDescricao;
    private ImageView imagem1, imagem2, imagem3;
    private Spinner campoEstado, campoCategoria;
    private CurrencyEditText campoValor;
    private MaskEditText campoTelefone;
    private Anuncio anuncio;

    private android.app.AlertDialog alertDialog;

    private StorageReference storage;

    private String[] permissoes = new String[] {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private List<String> listaFotosRecuperadas = new ArrayList<>();
    private List<String> listaURLFotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_anuncio);

        //Configurações inicias
        storage = ConfiguracaoFirebase.getFirebaseStorage();

        //Validar permissoes
        Permissoes.validarPermissoes(permissoes, this, 1);
        inicializarComponents();
        carregarDadosSpinner();
    }

    public void salvarFotoStorage(String urlString, int totalFotos, int contador) {
        //Criar nó no firebase
        StorageReference imagemAnuncio = storage.child("imagens")
                                        .child("anuncios")
                                        .child(anuncio.getIdAnuncio())
                                        .child("imagem"+contador);
        //Fazer upload do arquivo
        UploadTask uploadTask = imagemAnuncio.putFile(Uri.parse(urlString));
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imagemAnuncio.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        Uri url = task.getResult();
                        String urlConvertida = url.toString();
                        listaURLFotos.add(urlConvertida);
                        Log.d("Imagem", "Link: " + urlConvertida);

                        if (totalFotos == listaURLFotos.size()) {
                            anuncio.setFotos(listaURLFotos);
                            anuncio.salvar();

                            alertDialog.dismiss();
                            finish();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CadastrarAnuncioActivity.this, "Falha ao fazer upload", Toast.LENGTH_LONG).show();
                Log.d("Falha upload imagem", "Exception: " + e.getMessage());
            }
        });
    }

    public void salvarAnuncio() {
        alertDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Salvando anúncio")
                .setCancelable(false)
                .build();

        alertDialog.show();

        // Salvar as imagens no storage
        int tamanhoLista = listaFotosRecuperadas.size();
        for (int i=0; i < listaFotosRecuperadas.size(); i++) {
            String urlImagem = listaFotosRecuperadas.get(i);
            //Log.d("Foto", urlImagem);
            salvarFotoStorage(urlImagem, tamanhoLista, i);
        }
    }

    public Anuncio configurarAnuncio() {
        String estado = campoEstado.getSelectedItem().toString();
        String categoria = campoCategoria.getSelectedItem().toString();
        String titulo = campoTitulo.getText().toString();
        String valor = campoValor.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String descricao = campoDescricao.getText().toString();

        anuncio = new Anuncio();
        anuncio.setEstado(estado);
        anuncio.setCategoria(categoria);
        anuncio.setTitulo(titulo);
        anuncio.setValor(valor);
        anuncio.setTelefone(telefone);
        anuncio.setDescricao(descricao);

        return anuncio;
    }

    public void validarDadosAnuncio(View view) {

        anuncio = configurarAnuncio();
        String valor = String.valueOf(campoValor.getRawValue());

        if (
            listaFotosRecuperadas.size() == 0 || anuncio.getEstado().isEmpty() ||
            anuncio.getCategoria().isEmpty() || anuncio.getTitulo().isEmpty() ||
            valor.isEmpty() || valor.equals("0") ||
            anuncio.getTelefone().isEmpty() || anuncio.getDescricao().isEmpty()
        ) {
            Toast.makeText(this, "Preencha todos os campos e faça upload de ao menos uma imagem.", Toast.LENGTH_LONG).show();
        } else {
            salvarAnuncio();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageCadastro1:
                escolherImagem(1);
                break;
            case R.id.imageCadastro2:
                escolherImagem(2);
                break;
            case R.id.imageCadastro3:
                escolherImagem(3);
                break;
        }
    }

    public void escolherImagem (int requestCode) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            Uri imagemSelecionada = data.getData();
            String caminhoImagem = imagemSelecionada.toString();

            if (requestCode == 1) {
                imagem1.setImageURI(imagemSelecionada);
            } else if (requestCode == 2) {
                imagem2.setImageURI(imagemSelecionada);
            } else if (requestCode == 3) {
                imagem3.setImageURI(imagemSelecionada);
            }

            listaFotosRecuperadas.add(caminhoImagem);
        }
    }

    private void carregarDadosSpinner() {
        //Spinner estados
        String[] estados = getResources().getStringArray(R.array.estados);
        ArrayAdapter<String> adapterEstados = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estados);
        adapterEstados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoEstado.setAdapter(adapterEstados);
        //Spinner categorias
        String[] categorias = getResources().getStringArray(R.array.categoria);
        ArrayAdapter<String> adapterCategorias = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorias);
        adapterCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoCategoria.setAdapter(adapterCategorias);
    }

    private void inicializarComponents() {
        campoTitulo = findViewById(R.id.editTitulo);
        campoDescricao = findViewById(R.id.editDescricao);
        campoValor = findViewById(R.id.editValor);
        campoTelefone = findViewById(R.id.editTelefone);
        campoEstado = findViewById(R.id.spinnerEstado);
        campoCategoria = findViewById(R.id.spinnerCategoria);
        imagem1 = findViewById(R.id.imageCadastro1);
        imagem2 = findViewById(R.id.imageCadastro2);
        imagem3 = findViewById(R.id.imageCadastro3);
        imagem1.setOnClickListener(this);
        imagem2.setOnClickListener(this);
        imagem3.setOnClickListener(this);

        //Configura a localidade para pt-br
        Locale locale = new Locale("pt", "BR");
        campoValor.setLocale(locale);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int permissaoResultado : grantResults) {
            if (permissaoResultado == PackageManager.PERMISSION_DENIED) {
                alertaValidacaoPermissao();
            }
        }

    }

    private void alertaValidacaoPermissao () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissão negadas");
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirma", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
    }
}