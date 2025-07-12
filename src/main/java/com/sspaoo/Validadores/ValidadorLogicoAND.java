package com.sspaoo.Validadores;

import java.util.List;

import com.sspaoo.Aluno.Aluno;
import com.sspaoo.Disciplina.Disciplina;

public class ValidadorLogicoAND extends ValidadorLogico {
    
    public boolean validar(Aluno aluno, Disciplina disciplina){
        ValidadorSimples validadorSimples = new ValidadorSimples();
        List<Disciplina> preRequisitos = disciplina.getPreRequisitos();

        if (preRequisitos == null || preRequisitos.isEmpty())
            return true;
        
        for (Disciplina preRequisito: preRequisitos)
            if (!validadorSimples.validar(aluno, preRequisito))
                return false;

        return true;
    }
}
