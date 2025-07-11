package com.sspaoo.Disciplina;

import java.util.ArrayList;
import java.util.List;

public abstract class Disciplina {
    protected String nome;
    protected String codigo;
    protected int cargaHoraria;
    protected int creditos;
    protected int precedencia;
    protected List<Disciplina> preRequisitos = new ArrayList<>();
    protected Disciplina coRequisito;
    
    public Disciplina(String nome, String codigo, int cargaHoraria){
        setNome(nome);
        setCodigo(codigo);
        setCargaHoraria(cargaHoraria);
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty())
            throw new IllegalArgumentException("Nome da disciplina não pode ser nulo ou vazio");
        
        this.nome = nome;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isEmpty())
            throw new IllegalArgumentException("Código da disciplina não pode ser nulo ou vazio");
        
        this.codigo = codigo;
    }

    public void setCargaHoraria(int cargaHoraria) {
        if (cargaHoraria <= 0)
            throw new IllegalArgumentException("A carga horária da disciplina não pode ser menor ou igual a zero");

        this.cargaHoraria = cargaHoraria;
    }

    public void setPreRequisitos(List<Disciplina> preRequisitos) {
        this.preRequisitos = preRequisitos;
    }

    public void setCoRequisito(Disciplina coRequisito) {
        if (coRequisito == null)
            throw new IllegalArgumentException("Co-requisito não pode ser nulo");
            
        this.coRequisito = coRequisito;
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

    public Disciplina getCoRequisito() {
        return coRequisito;
    }
    
    public int getPrecedencia() {
        return precedencia;
    }

}
