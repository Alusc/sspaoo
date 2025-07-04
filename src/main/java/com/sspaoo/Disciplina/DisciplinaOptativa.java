package com.sspaoo.Disciplina;

public class DisciplinaOptativa extends Disciplina {
    public DisciplinaOptativa(String nome, String codigo, int cargaHoraria){
        super(nome, codigo, cargaHoraria);
        precedencia = 0;
    }
}
