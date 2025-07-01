package com.sspaoo.Aluno;

import java.util.HashMap;

public class Aluno {
    private String nome;
    private String matricula;
    private HashMap<String, Float> historico = new HashMap<>();


    public Aluno(String nome, String matricula){
        setNome(nome);
        setMatricula(matricula);
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        this.nome = nome;
    }
    public void setMatricula(String matricula) {
        if (matricula == null || matricula.isEmpty()) {
            throw new IllegalArgumentException("Matrícula não pode ser nula ou vazia");
        }
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }
}
