package com.sspaoo.Turma;

import java.time.*;

public class Horario {
    private boolean[] temAulaNesseDia = new boolean[5];    
    private LocalTime[] inicioDaAula = new LocalTime[5];
    private LocalTime[] fimDaAula = new LocalTime[5];
    public Horario(boolean[] temAulaNesseDia, LocalTime[] inicioDaAula, LocalTime[] fimDaAula){
        for(int i = 0; i < 5; i++){
            if (fimDaAula[i].isBefore(inicioDaAula[i]))
                throw new IllegalArgumentException("A aula precisa começar antes de acabar");
        }
    }
    //gets não devem ter parâmetro, mudar o nome/passar pra classe turma
    public void validarPosicao(int dia){
        if(dia < 0 || dia > 4)
            throw new IllegalArgumentException("Dia inválido");
    }
    public boolean getTemAulaNoDia(int dia){
        validarPosicao(dia);
        return temAulaNesseDia[dia];
    }
    public LocalTime getInicioDaAulaNoDia(int dia){
        validarPosicao(dia);
        return inicioDaAula[dia];
    }
    public LocalTime getFimDaAulaNoDia(int dia){
        validarPosicao(dia);
        return fimDaAula[dia];
    }
}



