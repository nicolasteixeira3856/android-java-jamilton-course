package com.nicolas.bahamut.ifoodclone.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.nicolas.bahamut.ifoodclone.R;
import com.nicolas.bahamut.ifoodclone.adapter.AdapterProduto;
import com.nicolas.bahamut.ifoodclone.helper.ConfiguracaoFirebase;
import com.nicolas.bahamut.ifoodclone.helper.UsuarioFirebase;
import com.nicolas.bahamut.ifoodclone.listener.RecyclerItemClickListener;
import com.nicolas.bahamut.ifoodclone.model.Empresa;
import com.nicolas.bahamut.ifoodclone.model.ItemPedido;
import com.nicolas.bahamut.ifoodclone.model.Pedido;
import com.nicolas.bahamut.ifoodclone.model.Produto;
import com.nicolas.bahamut.ifoodclone.model.Usuario;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class CardapioActivity extends AppCompatActivity {

    private RecyclerView recyclerProdutosCardapio;
    private ImageView imageEmpresaCardapio;
    private TextView textNomeEmpresaCardapio;
    private Empresa empresaSelecionada;
    private AlertDialog dialog;
    private TextView textCarrinhoQtd, textCarrinhoTotal;
    private AdapterProduto adapterProduto;
    private List<Produto> produtos = new ArrayList<>();
    private List<ItemPedido> itensCarrinho = new ArrayList<>();
    private DatabaseReference firebaseRef;
    private String idEmpresa;
    private String idUsuarioLogado;
    private Usuario usuario;
    private Pedido pedidoRecuperado;
    private TextView toolbarTitle;
    private int qtdItensCarrinho;
    private Double totalCarrinho;
    private int metodoPagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);

        //Configurações iniciais
        inicializarComponentes();
        firebaseRef = ConfiguracaoFirebase.getFirebase();
        idUsuarioLogado = UsuarioFirebase.getIdUsuario();

        //Recuperar empresa selecionada
        Bundle bundle = getIntent().getExtras();
        if( bundle != null ){
            empresaSelecionada = (Empresa) bundle.getSerializable("empresa");

            textNomeEmpresaCardapio.setText( empresaSelecionada.getNome() );
            idEmpresa = empresaSelecionada.getIdUsuario();

            String url = empresaSelecionada.getUrlImagem();
            Picasso.get().load(url).into(imageEmpresaCardapio);

        }

        //Configurações Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Cardápio");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Configura recyclerview
        recyclerProdutosCardapio.setLayoutManager(new LinearLayoutManager(this));
        recyclerProdutosCardapio.setHasFixedSize(true);
        adapterProduto = new AdapterProduto(produtos, this);
        recyclerProdutosCardapio.setAdapter( adapterProduto );

        //Configurar evento de clique
        recyclerProdutosCardapio.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this,
                        recyclerProdutosCardapio,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                confirmarQuantidade(position);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        //Recupera produtos para empresa
        recuperarProdutos();
        recuperarDadosUsuario();
    }


    private void confirmarQuantidade(final int posicao){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quantidade");
        builder.setMessage("Digite a quantidade");

        final EditText editQuantidade = new EditText(this);
        editQuantidade.setText("1");

        builder.setView( editQuantidade );

        String quantidade = editQuantidade.getText().toString();
        
        if (Integer.parseInt(quantidade) == 0) {
            Toast.makeText(this, "Preencha uma quantidade válida", Toast.LENGTH_SHORT).show();
        } else {
            builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Produto produtoSelecionado = produtos.get(posicao);
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setIdProduto( produtoSelecionado.getIdProduto() );
                    itemPedido.setNomeProduto( produtoSelecionado.getNome() );
                    itemPedido.setPreco( produtoSelecionado.getPreco() );
                    itemPedido.setQuantidade( Integer.parseInt(quantidade) );

                    itensCarrinho.add( itemPedido );

                    if( pedidoRecuperado == null ){
                        pedidoRecuperado = new Pedido(idUsuarioLogado, idEmpresa);
                    }

                    if (usuario.getNome() == null || usuario.getEndereco() == null) {
                        Toast.makeText(CardapioActivity.this, "Por favor preencha seus dados pessoais no menu configurações.", Toast.LENGTH_LONG).show();
                    } else {
                        pedidoRecuperado.setNome( usuario.getNome() );
                        pedidoRecuperado.setEndereco( usuario.getEndereco() );
                        pedidoRecuperado.setItens( itensCarrinho );
                        pedidoRecuperado.salvar();
                    }

                }
            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    private void recuperarDadosUsuario() {

        dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Carregando dados")
                .setCancelable( false )
                .build();
        dialog.show();

        DatabaseReference usuariosRef = firebaseRef
                .child("usuarios")
                .child(idUsuarioLogado);

        usuariosRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if( dataSnapshot.getValue() != null ){
                    usuario = dataSnapshot.getValue(Usuario.class);
                }
                recuperPedido();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void recuperPedido() {

        DatabaseReference pedidoRef = firebaseRef
                .child("pedidos_usuario")
                .child( idEmpresa )
                .child( idUsuarioLogado );

        pedidoRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                qtdItensCarrinho = 0;
                totalCarrinho = 0.0;
                itensCarrinho = new ArrayList<>();

                if(dataSnapshot.getValue() != null){

                    pedidoRecuperado = dataSnapshot.getValue(Pedido.class);
                    itensCarrinho = pedidoRecuperado.getItens();


                    for(ItemPedido itemPedido: itensCarrinho){

                        //Log.d("Quantidade pedido", String.valueOf(itemPedido.getQuantidade()));

                        int qtde = itemPedido.getQuantidade();
                        Double preco = itemPedido.getPreco();

                        totalCarrinho += (qtde * preco);
                        qtdItensCarrinho += qtde;

                    }

                }

                DecimalFormat df = new DecimalFormat("0.00");

                textCarrinhoQtd.setText( "qtd: " + qtdItensCarrinho );
                textCarrinhoTotal.setText("R$ " + df.format(totalCarrinho) );

                dialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void recuperarProdutos(){

        DatabaseReference produtosRef = firebaseRef
                .child("produtos")
                .child( idEmpresa );

        produtosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                produtos.clear();

                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    produtos.add( ds.getValue(Produto.class) );
                }

                adapterProduto.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cardapio, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuPedido :
                confirmarPedido();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void confirmarPedido() {

        if( pedidoRecuperado == null ){
            pedidoRecuperado = new Pedido(idUsuarioLogado, idEmpresa);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Selecione um método de pagamento");


            CharSequence[] itens = new CharSequence[]{
                    "Dinheiro", "Máquina cartão"
            };
            builder.setSingleChoiceItems(itens, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    metodoPagamento = which;
                }
            });

            final EditText editObservacao = new EditText(this);
            editObservacao.setHint("Digite uma observação");
            builder.setView( editObservacao );

            builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    String observacao = editObservacao.getText().toString();
                    pedidoRecuperado.setMetodoPagamento( metodoPagamento );
                    pedidoRecuperado.setObservacao( observacao );
                    pedidoRecuperado.setStatus("confirmado");
                    pedidoRecuperado.confimar();
                    pedidoRecuperado.remover();
                    pedidoRecuperado = null;

                    Toast.makeText(CardapioActivity.this, "Pedido confirmado", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    private void inicializarComponentes(){
        recyclerProdutosCardapio = findViewById(R.id.recyclerProdutosCardapio);
        imageEmpresaCardapio = findViewById(R.id.imageEmpresaCardapio);
        textNomeEmpresaCardapio = findViewById(R.id.textNomeEmpresaCardapio);

        textCarrinhoQtd = findViewById(R.id.textCarrinhoQtd);
        textCarrinhoTotal = findViewById(R.id.textCarrinhoTotal);
    }
}