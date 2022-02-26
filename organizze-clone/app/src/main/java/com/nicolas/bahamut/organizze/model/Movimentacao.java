package com.nicolas.bahamut.organizze.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.nicolas.bahamut.organizze.config.ConfiguracaoFirebase;
import com.nicolas.bahamut.organizze.helper.Base64Custom;
import com.nicolas.bahamut.organizze.helper.DateUtil;

public class Movimentacao {

    private String key;
    private String data;
    private String categoria;
    private String descricao;
    private String tipo;
    private double valor;

    public Movimentacao() {
    }

    public void salvar (String dataEscolhida) {
        FirebaseAuth auth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("movimentacao")
                .child(Base64Custom.codificarBase64(auth.getCurrentUser().getEmail()))
                .child(DateUtil.mesAnoDataEscolhida(dataEscolhida))
                .push()
                .setValue(this);
    }

    public String getData() {
        return data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
