package com.sspaoo.SistemaAcademico;

import java.util.Arrays;

import com.sspaoo.Aluno.Aluno;
import com.sspaoo.Disciplina.*;
import com.sspaoo.TratamentoDeExcecoes.*;
import com.sspaoo.Validadores.*;
import com.sspaoo.Turma.Horario;
import com.sspaoo.Turma.Turma;

public class SistemaAcademico {
    public static void main(String[] args) {
        System.out.println("Sistema");
        //Exemplo de criação de discplinas e pre requisitos
        Disciplina calculo1 = new DisciplinaObrigatoria("Cálculo I", "MAT154", 4);
        Disciplina geometriaAnalitica = new DisciplinaObrigatoria("Geometria analítica", "MAT155", 4);
        Disciplina calculo2 = new DisciplinaObrigatoria("Cálculo II", "MAT156", 4);
        calculo2.setPreRequisitos(Arrays.asList(calculo1, geometriaAnalitica));


        Turma calculo1TurmaA = new Turma(calculo1, 'A', 4, null);
        Turma calculo2TurmaA = new Turma(calculo2, 'A', 4, null);
        Aluno aluno1 = new Aluno("Lucas", "202500000");

        validarMatricula(aluno1, calculo1TurmaA);
        
    }

    public static void realizarMatricula(Aluno aluno, Turma turma) throws MatriculaException {
        //Esse é o código que vai disparar as exceções
        Disciplina disciplina = turma.getDisciplina();

        ValidadorLogicoAND validadorAnd = new ValidadorLogicoAND();

        //Exemplo de como as exceções vão ser lançadas
        if (!validadorAnd.validar(aluno, disciplina))
            throw new PreRequisitoNaoCumpridoException("Pré requisito não cumprido");

        if (turma.getVagas() == turma.getAlunosMatriculados())
            throw new TurmaCheiaException("A turma selecionada não possui vagas disponíveis");


        //Se passar por todas as exceções
        turma.setAlunosMatriculados(turma.getAlunosMatriculados() + 1);
        aluno.adicionarDisciplinaAoHistorico(disciplina);
        System.out.println("Aluno " + aluno.getMatricula() + " foi matriculado na disciplina " + disciplina.getCodigo() + " turma " + turma.getId() + " com sucesso");
    }

    public static void validarMatricula(Aluno aluno, Turma turma){
        Disciplina disciplina = turma.getDisciplina();

        try {
            realizarMatricula(aluno, turma);
 
        } catch (PreRequisitoNaoCumpridoException e) {
             System.out.println("Aluno " + aluno.getMatricula() + " não foi matriculado na disciplina " + disciplina.getCodigo() + " turma " + turma.getId() + " por falta de pré requisito");
        } catch (CoRequisitoNaoAtendidoException e) {
            //se falhou no verificador de corquisito
        } catch (CargaHorariaExcedidaException e) {
            //se carga horária total (tem que declarar essa variável) > créditos máximos (constante, perguntei pro gleiph quanto é)
        } catch (ConflictoDeHorarioException e) {
            //se dois dias que tem aula tem horário de início de uma matéria entre horário de início e fim de outra (lembrar de usar as procedências)
        } catch (TurmaCheiaException e) {
             System.out.println("Aluno " + aluno.getMatricula() + " não foi matriculado na disciplina " + disciplina.getCodigo() + " turma " + turma.getId() + " pela turma estar cheia");
        } catch (MatriculaException e) {
            
        }

    }


}
