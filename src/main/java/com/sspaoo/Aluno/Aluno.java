package com.sspaoo.Aluno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sspaoo.Disciplina.Disciplina;
import com.sspaoo.Turma.Turma;

public class Aluno {
    private String nome;
    private String matricula;
    private Map<Disciplina, Float> historico = new HashMap<>();
    private List<Turma> planejamentoFuturo = new ArrayList<>();

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
        if(matricula.length() != 9)
            throw new IllegalArgumentException("Matrícula deve conter 9 elementos");
        if(Integer.parseInt(matricula.substring(0, 3)) > 2025)
            throw new IllegalArgumentException("Os 4 primeiros elementos da matrícula devem ser um ano válido");
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

    public List<Turma> getPlanejamentoFuturo() {
        return planejamentoFuturo;
    }

    public void adicionarDisciplinaAoHistorico(Disciplina disciplina) {
        historico.put(disciplina, 0f);
    }

}
