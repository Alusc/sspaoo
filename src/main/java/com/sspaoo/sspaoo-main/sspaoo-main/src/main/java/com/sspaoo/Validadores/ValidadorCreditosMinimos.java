package com.sspaoo.Validadores;

import com.sspaoo.Aluno.Aluno;
import com.sspaoo.Disciplina.Disciplina;

public class ValidadorCreditosMinimos implements ValidadorPreRequisito {
    private int creditosMinimos;

    public ValidadorCreditosMinimos(int creditosMinimos){
        this.creditosMinimos = creditosMinimos;
    }

    public boolean validar(Aluno aluno, Disciplina disciplina){
        int creditos = 0;
    
        for (Disciplina disciplinaCursada: aluno.getHistorico().keySet())
            creditos += disciplinaCursada.getCreditos();
        
        return creditos >= creditosMinimos;
        
    }
}
