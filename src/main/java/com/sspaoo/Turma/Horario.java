package com.sspaoo.Turma;

import java.time.*;

public class Horario {
    private boolean[] temAulaNesseDia = new boolean[5];    
    private LocalTime[] inicioDaAula = new LocalTime[5];
    private LocalTime[] fimDaAula = new LocalTime[5];
    public Horario(boolean[] temAulaNesseDia, LocalTime[] inicioDaAula, LocalTime[] fimDaAula){
        if(inicioDaAula.length != fimDaAula.length){
            throw new blablablaexception ("vetores de tamanhos diferentes");
        }
        
    }
}


