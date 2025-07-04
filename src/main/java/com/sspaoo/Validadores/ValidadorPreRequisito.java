package com.sspaoo.Validadores;

import com.sspaoo.Aluno.Aluno;
import com.sspaoo.Disciplina.Disciplina;

public interface ValidadorPreRequisito {

    boolean validar(Aluno aluno, Disciplina disciplina);
    
}