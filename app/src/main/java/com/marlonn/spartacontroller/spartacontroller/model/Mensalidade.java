package com.marlonn.spartacontroller.spartacontroller.model;

import android.widget.TextView;

import java.io.Serializable;

public class Mensalidade implements Serializable {

    private String id;
    private String valor;

    public Mensalidade(String id, String valor) {

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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
