package com.sspaoo.Aluno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sspaoo.Disciplina.Disciplina;

public class Aluno {
    private String nome;
    private String matricula;
    private Map<Disciplina, Float> historico = new HashMap<>();
    private List<Disciplina> planejamentoFuturo = new ArrayList<>();


    public Aluno(String nome, String matricula){
        setNome(nome);
        setMatricula(matricula);
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty())
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        
        this.nome = nome;
    }
    public void setMatricula(String matricula) {
        if (matricula == null || matricula.isEmpty())
            throw new IllegalArgumentException("Matrícula não pode ser nula ou vazia");
        
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public Map<Disciplina, Float> getHistorico() {
        return historico;
    }

    public List<Disciplina> getPlanejamentoFuturo() {
        return planejamentoFuturo;
    }

    // public void printarHistorico() {
    //     return;
    // }
}
