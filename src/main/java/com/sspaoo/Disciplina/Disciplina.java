package com.sspaoo.Disciplina;

import java.util.List;

public abstract class Disciplina {
    protected String nome;
    protected String codigo;
    protected int cargaHoraria;
    protected int creditos;
    protected int precedencia;
    protected List<Disciplina> preRequisitos;
    protected List<Disciplina> coRequisitos;
    
    public Disciplina(String nome, String codigo, int cargaHoraria){
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
    }

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

    public List<Disciplina> getPreRequisitos() {
        return preRequisitos;
    }

    public List<Disciplina> getCoRequisitos() {
        return coRequisitos;
    }

}
