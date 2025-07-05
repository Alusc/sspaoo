package com.sspaoo.Turma;

import com.sspaoo.Disciplina.Disciplina;

public class Turma {
    private char id;
    private Disciplina disciplina;
    private int vagas;
    private int alunosMatriculados;
    private String horario;

    public char getId() {
        return id;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public int getVagas() {
        return vagas;
    }

    public int getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public String getHorario() {
        return horario;
    }
    
}