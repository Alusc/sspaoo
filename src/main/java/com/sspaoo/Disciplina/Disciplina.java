package com.sspaoo.Disciplina;

import java.util.ArrayList;
import java.util.List;

import com.sspaoo.Validadores.ValidadorLogico;
import com.sspaoo.Validadores.ValidadorLogicoAND;
import com.sspaoo.Validadores.ValidadorLogicoOR;
import com.sspaoo.Turma.Turma;

public abstract class Disciplina {
    protected String nome;
    protected String codigo;
    protected int cargaHoraria;
    protected int creditos;
    protected int precedencia;
    protected ValidadorLogico validadorLogico;
    protected List<Disciplina> preRequisitos = new ArrayList<>();
    protected Disciplina coRequisito;
    protected boolean isMatriculado = true;
    protected Turma turma;
    
    public static enum TipoPreRequisito {AND, OR}  

    public Disciplina(String nome, String codigo, int cargaHoraria){
        setNome(nome);
        setCodigo(codigo);
        setCargaHoraria(cargaHoraria);
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty())
            throw new IllegalArgumentException("Nome da disciplina não pode ser nulo ou vazio");
        
        this.nome = nome;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isEmpty())
            throw new IllegalArgumentException("Código da disciplina não pode ser nulo ou vazio");
        
        this.codigo = codigo;
    }

    public void setCargaHoraria(int cargaHoraria) {
        if (cargaHoraria <= 0)
            throw new IllegalArgumentException("A carga horária da disciplina não pode ser menor ou igual a zero");

        this.cargaHoraria = cargaHoraria;
    }

    private void setValidadorLogico(TipoPreRequisito tipoPreRequisito) {
        switch (tipoPreRequisito) {
            case TipoPreRequisito.AND:
                this.validadorLogico = new ValidadorLogicoAND();
            break;
        
            case TipoPreRequisito.OR:
                this.validadorLogico = new ValidadorLogicoOR();
                
            break;
        }
    }

    public void setPreRequisitos(List<Disciplina> preRequisitos, TipoPreRequisito tipoPreRequisito) {
        this.preRequisitos = preRequisitos;
        setValidadorLogico(tipoPreRequisito);
    }

    public void setCoRequisito(Disciplina coRequisito) {
        if (coRequisito == null)
            throw new IllegalArgumentException("Co-requisito não pode ser nulo");
        
        coRequisito.coRequisito = this;
        this.coRequisito = coRequisito;
    }
    
    public void setTurma(Turma turma)
    {
        this.turma = turma;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public int getCreditos() {
        return creditos;
    }

   public ValidadorLogico getValidadorLogico() {
       return validadorLogico;
   }

    public List<Disciplina> getPreRequisitos() {
        return preRequisitos;
    }

    public Disciplina getCoRequisito() {
        return coRequisito;
    }
    
    public int getPrecedencia() {
        return precedencia;
    }
    
    public void setStatus(boolean matriculado)
    {
        isMatriculado = matriculado;
    }
    
    public boolean isMatriculado()
    {
        return isMatriculado;
    }
    
    public Turma getTurma()
    {
        return turma;
    }
}
