package com.sspaoo.Validadores;

import com.sspaoo.Aluno.Aluno;
import com.sspaoo.Disciplina.Disciplina;

public abstract class ValidadorLogico implements ValidadorPreRequisito {
    public abstract boolean validar(Aluno aluno, Disciplina disciplina);
}
