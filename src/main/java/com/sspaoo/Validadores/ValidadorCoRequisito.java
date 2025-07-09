package com.sspaoo.Validadores;

import com.sspaoo.Aluno.Aluno;
import com.sspaoo.Disciplina.Disciplina;

public class ValidadorCoRequisito implements ValidadorPreRequisito {
    public boolean validar(Aluno aluno, Disciplina disciplina){
        Disciplina coRequisito = disciplina.getCoRequisito();
        return coRequisito == null || aluno.getPlanejamentoFuturo().contains(coRequisito);
    }
}
