package com.sspaoo.Validadores;

import java.util.List;

import com.sspaoo.Aluno.Aluno;
import com.sspaoo.Disciplina.Disciplina;

public class ValidadorLogicoOR implements ValidadorPreRequisito {
    private ValidadorSimples validadorSimples = new ValidadorSimples();

    public boolean validar(Aluno aluno, Disciplina disciplina){
        List<Disciplina> preRequisitos = disciplina.getPreRequisitos();
        
        if (preRequisitos == null || preRequisitos.isEmpty())
            return true;
        
        for (Disciplina preRequisito: preRequisitos)
            if (validadorSimples.validar(aluno, preRequisito))
                return true;

        return false;
    }

}
