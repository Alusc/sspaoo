package com.sspaoo.Validadores;

import java.util.List;

import com.sspaoo.Aluno.Aluno;
import com.sspaoo.Disciplina.Disciplina;

public class ValidadorLogicoAND implements ValidadorPreRequisito {
    
    private List<ValidadorPreRequisito> preRequisitos;

    public ValidadorLogicoAND(List<ValidadorPreRequisito> preRequisitos){
        this.preRequisitos = preRequisitos;
    }

    public boolean validar(Aluno aluno, Disciplina disciplina){
        if (preRequisitos == null || preRequisitos.isEmpty())
            return true;
        
        for (ValidadorPreRequisito preRequisito: preRequisitos)
            if (!preRequisito.validar(aluno, disciplina))
                return false;

        return true;
    }
}
