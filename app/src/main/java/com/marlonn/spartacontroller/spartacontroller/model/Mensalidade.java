package com.marlonn.spartacontroller.spartacontroller.model;

import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class Mensalidade implements Serializable {

    private String id;
    private int valor;

    public Mensalidade(String id, int valor) {

        this.id = id;
        this.valor = valor;

    }

    public Mensalidade() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
