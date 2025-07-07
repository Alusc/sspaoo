package com.sspaoo.Validadores;

import java.util.List;

import com.sspaoo.Aluno.Aluno;
import com.sspaoo.Disciplina.Disciplina;

public class ValidadorLogicoOR implements ValidadorPreRequisito {
    private List<Disciplina> preRequisitos;
    private ValidadorSimples validadorSimples = new ValidadorSimples();

    public ValidadorLogicoOR(List<Disciplina> preRequisitos){
        this.preRequisitos = preRequisitos;
    }

    public boolean validar(Aluno aluno, Disciplina disciplina){
        if (preRequisitos == null || preRequisitos.isEmpty())
            return true;
        
        for (Disciplina preRequisito: preRequisitos)
            if (validadorSimples.validar(aluno, preRequisito))
                return true;

        return false;
    }

}
