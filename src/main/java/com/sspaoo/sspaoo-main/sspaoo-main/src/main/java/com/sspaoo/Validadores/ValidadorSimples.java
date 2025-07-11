package com.sspaoo.Validadores;

// import java.util.Map;

import com.sspaoo.Aluno.Aluno;
import com.sspaoo.Disciplina.Disciplina;

public class ValidadorSimples implements ValidadorPreRequisito {
    public boolean validar(Aluno aluno, Disciplina disciplina){
        return aluno.getHistorico().getOrDefault(disciplina, -1f) >= 60f;
    }
}
