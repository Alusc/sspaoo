package com.sspaoo.Validadores;

import com.sspaoo.Aluno.Aluno;
import com.sspaoo.Disciplina.Disciplina;
import com.sspaoo.Turma.Turma;

public class ValidadorCoRequisito implements ValidadorPreRequisito {
    public boolean validar(Aluno aluno, Disciplina disciplina){
        Disciplina coRequisito = disciplina.getCoRequisito();
        if (coRequisito == null)
            return true;
        
        for (Turma turma: aluno.getPlanejamentoFuturo())
            if (turma.getDisciplina() == coRequisito)
                return true;
        return false;
    }
}
