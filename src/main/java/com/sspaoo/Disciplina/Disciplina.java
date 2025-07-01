package com.sspaoo.Disciplina;

public abstract class Disciplina {
    private String nome;
    private String codigo;
    private int cargaHoraria;
    private int creditos;

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public int getCreditos() {
        return creditos;
    }

}
