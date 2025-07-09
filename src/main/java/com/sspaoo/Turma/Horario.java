package com.sspaoo.Turma;

import java.time.*;

public class Horario {
    private boolean[] temAulaNesseDia = new boolean[5];    
    private LocalTime[] inicioDaAula = new LocalTime[5];
    private LocalTime[] fimDaAula = new LocalTime[5];
    public Horario(boolean[] temAulaNesseDia, LocalTime[] inicioDaAula, LocalTime[] fimDaAula){
        for(int i = 0; i < 5; i++){
            if(fimDaAula[i].isBefore(inicioDaAula[i])){
                throw new IllegalArgumentException("A aula precisa começar antes de acabar");
            }
        }
    }
    //gets não devem ter parâmetro, mudar o nome/passar pra classe turma

    public boolean getTemAulaNoDia(int dia){
        if(dia > 5 || dia < 1){
            throw new IllegalArgumentException("Os dias da semana vão de 1 a 5");
        }
        return temAulaNesseDia[dia];
    }
    public LocalTime getInicioDaAulaNoDia(int dia){
        if(dia > 5 || dia < 1){
            throw new IllegalArgumentException("Os dias da semana vão de 1 a 5");
        }
        return inicioDaAula[dia];
    }
    public LocalTime getFimDaAulaNoDia(int dia){
        if(dia > 5 || dia < 1){
            throw new IllegalArgumentException("Os dias da semana vão de 1 a 5");
        }
        return fimDaAula[dia];
    }
}



