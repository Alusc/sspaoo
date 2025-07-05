package com.sspaoo.Validadores;

import java.util.List;

import com.sspaoo.Aluno.Aluno;
import com.sspaoo.Disciplina.Disciplina;

public class ValidadorLogicoOR implements ValidadorPreRequisito {
    private List<ValidadorPreRequisito> preRequisitos;

    public ValidadorLogicoOR(List<ValidadorPreRequisito> preRequisitos){
        this.preRequisitos = preRequisitos;
    }

    public boolean validar(Aluno aluno, Disciplina disciplina){
        if (preRequisitos.isEmpty() || preRequisitos == null)
            return true;
        
        for (ValidadorPreRequisito preRequisito: preRequisitos)
            if (preRequisito.validar(aluno, disciplina))
                return true;

        return false;
    }

}
