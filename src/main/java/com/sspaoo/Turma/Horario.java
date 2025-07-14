package com.sspaoo.Turma;

import java.time.*;

public class Horario {
    private boolean[] temAulaNesseDia = {false, false, false, false, false};
    private LocalTime[] inicioDaAula = new LocalTime[5];
    private LocalTime[] fimDaAula = new LocalTime[5];
    public Horario(boolean[] temAulaNesseDia, LocalTime[] inicioDaAula, LocalTime[] fimDaAula){
        for(int i = 0; i < 5; i++){
            if(temAulaNesseDia[i]) {
                if(inicioDaAula[i] == null || fimDaAula[i] == null) {
                    throw new IllegalArgumentException("Defina os horários de início e fim para os dias com aula");
                }
                if (fimDaAula[i].isBefore(inicioDaAula[i])){
                    throw new IllegalArgumentException("A aula precisa começar antes de acabar");
                }
            } else {
                inicioDaAula[i] = LocalTime.of(0, 0);
                fimDaAula[i] = LocalTime.of(0, 0);
            }
        }
        setInicioDaAula(inicioDaAula);
        setFimDaAula(fimDaAula);
        setTemAulaNesseDia(temAulaNesseDia);
    }
    public void validarPosicao(int dia){
        if(dia < 0 || dia > 4)
            throw new IllegalArgumentException("Dia inválido");
    }
    public void setTemAulaNesseDia(boolean[] temAulaNesseDia){
        this.temAulaNesseDia = temAulaNesseDia;
    }
    public void setInicioDaAula(LocalTime[] inicioDaAula){
        this.inicioDaAula = inicioDaAula;
    }
    public void setFimDaAula(LocalTime[] fimDaAula){
        this.fimDaAula = fimDaAula;
    }
    public boolean temAulaNesseDia(int dia){
        validarPosicao(dia);
        return temAulaNesseDia[dia];
    }
    public boolean[] getTemAulaNesseDia(){
        return temAulaNesseDia;
    }
    public LocalTime inicioDaAulaNoDia(int dia){
        validarPosicao(dia);
        return inicioDaAula[dia];
    }
    public LocalTime[] getInicioDaAula(){
        return inicioDaAula;
    }
    public LocalTime fimDaAulaNoDia(int dia){
        validarPosicao(dia);
        return fimDaAula[dia];
    }
    public LocalTime[] getFimDaAula(){
        return fimDaAula;
    }
}