package com.marlonn.spartacontroller.spartacontroller.model;

import java.io.Serializable;

public class Alunos implements Serializable {

    private String id;
    private String nomeAluno;
    private String dataVencimento;
    private boolean pago = false;

    public Alunos(String id, String nomeAluno, String dataVencimento, boolean pago) {

        this.id = id;
        this.nomeAluno = nomeAluno;
        this.dataVencimento = dataVencimento;
        this.pago = pago;

    }

    public Alunos() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
}