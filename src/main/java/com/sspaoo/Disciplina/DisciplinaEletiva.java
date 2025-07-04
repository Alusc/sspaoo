package com.sspaoo.Disciplina;

public class DisciplinaEletiva extends Disciplina {
    public DisciplinaEletiva(String nome, String codigo, int cargaHoraria){
        super(nome, codigo, cargaHoraria);
        precedencia = 1;
    }
}
