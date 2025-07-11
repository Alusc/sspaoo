package com.sspaoo.Disciplina;

public class DisciplinaObrigatoria extends Disciplina {
    public DisciplinaObrigatoria(String nome, String codigo, int cargaHoraria){
        super(nome, codigo, cargaHoraria);
        precedencia = 2;
    }
}
