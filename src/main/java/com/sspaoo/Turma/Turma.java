package com.sspaoo.Turma;

import com.sspaoo.Disciplina.Disciplina;

public class Turma {
    private Disciplina disciplina;
    private char id;
    private int vagas;
    private int alunosMatriculados;
    private Horario horario;

    public Turma(Disciplina disciplina, char id, int vagas, Horario horario){
        alunosMatriculados = 0;
        setDisciplina(disciplina);
        setId(id);
        setVagas(vagas);
        // setHorario(horario);
    }

    public void setDisciplina(Disciplina disciplina) {
        if (disciplina == null)
            throw new IllegalArgumentException("A disciplina da turma não pode ter um valor nulo");
        this.disciplina = disciplina;
    }

    public void setId(char id) {
        if (id < 'A' || id > 'Z')
            throw new IllegalArgumentException("O id da tuma deve ser um caractere entre A e Z");
        this.id = id;
    }

    public void setVagas(int vagas) {
        if (vagas <= 0)
            throw new IllegalArgumentException("A quantidade de vagas de uma turma tem que ser maior que 0");
        this.vagas = vagas;
    }

    public void setHorario(Horario horario) {
        if (horario == null)
            throw new IllegalArgumentException("O horário da turma não pode ter um valor nulo");
        this.horario = horario;
    }

    public void setAlunosMatriculados(int alunosMatriculados) {
        if (alunosMatriculados < 0)
            throw new IllegalArgumentException("A quantidade de alunos matriculados tem que ser maior ou igual a zero");
        this.alunosMatriculados = alunosMatriculados;
    }


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

    public Horario getHorario() {
        return horario;
    }
    
}
