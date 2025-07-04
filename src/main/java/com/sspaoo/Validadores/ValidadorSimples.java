package com.sspaoo.Validadores;

import com.sspaoo.Aluno.Aluno;
import com.sspaoo.Disciplina.Disciplina;

public class ValidadorSimples implements ValidadorPreRequisito {
    
    public boolean validar(Aluno aluno, Disciplina disciplina){
        Disciplina[] preRequisitos = disciplina.getPreRequisitos();
        if (preRequisitos.length == 0)
            return true;
        
        for (Disciplina preRequisito : preRequisitos){
            
        }
        
        return false;
    }
}
