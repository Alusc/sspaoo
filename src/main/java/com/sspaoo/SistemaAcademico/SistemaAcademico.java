package com.sspaoo.SistemaAcademico;

import java.util.Arrays;
import java.util.List;

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
        Disciplina algoritmos = new DisciplinaObrigatoria("Algoritmos I", "DCC119", 4);
        Disciplina algoritmosPratica = new DisciplinaObrigatoria("Algoritmos Prática", "DCC120", 2);
        algoritmos.setCoRequisito(algoritmosPratica);
        
        Disciplina calculo1 = new DisciplinaObrigatoria("Cálculo I", "MAT154", 4);
        Disciplina geometriaAnalitica = new DisciplinaObrigatoria("Geometria analítica", "MAT155", 4);
        Disciplina calculo2 = new DisciplinaObrigatoria("Cálculo II", "MAT156", 4);
        calculo2.setPreRequisitos(Arrays.asList(calculo1, geometriaAnalitica), Disciplina.TipoPreRequisito.AND);


        Turma algoritmosTurmaB = new Turma(algoritmos, 'B', 100, null);
        Turma algoritmosPraticaTurmaD = new Turma(algoritmosPratica, 'D', 100, null);
        Turma calculo1TurmaA = new Turma(calculo1, 'A', 100, null);
        Turma geometriaAnaliticaTurmaC = new Turma(geometriaAnalitica, 'C', 100, null); 
        Turma calculo2TurmaA = new Turma(calculo2, 'A', 100, null);

        Aluno aluno1 = new Aluno("Nome", "202500000");
        aluno1.setPlanejamentoFuturo(Arrays.asList(
            algoritmosTurmaB, 
            algoritmosPraticaTurmaD, 
            calculo1TurmaA, 
            geometriaAnaliticaTurmaC, 
            calculo2TurmaA
            ));

        matricularAluno(aluno1);
        
    }

    public static void matricularAluno(Aluno aluno) {
        List<Turma> planejamentoFuturo = aluno.getPlanejamentoFuturo();
        if (planejamentoFuturo.isEmpty()){
            System.out.println("O aluno não escolheu nenhuma turma");
            return;
        }
        for (Turma turma: aluno.getPlanejamentoFuturo()) {
            validarMatricula(aluno, turma);
        }
    }

    public static void realizarMatricula(Aluno aluno, Turma turma) throws MatriculaException {
        //Esse é o código que vai disparar as exceções
        
        Disciplina disciplina = turma.getDisciplina();

        ValidadorLogico validadorLogico = disciplina.getValidadorLogico();

        if (validadorLogico != null && !validadorLogico.validar(aluno, disciplina))
            throw new PreRequisitoNaoCumpridoException("Pré requisito não cumprido");
            
        ValidadorCoRequisito validadorCoRequisito = new ValidadorCoRequisito();
        if (!validadorCoRequisito.validar(aluno, disciplina))
            throw new CoRequisitoNaoAtendidoException("Co-requisito não cumprido");

        if (turma.isCheia())
            throw new TurmaCheiaException("A turma selecionada não possui vagas disponíveis");

        //Se passar por todas as exceções
        turma.setAlunosMatriculados(turma.getAlunosMatriculados() + 1);
        aluno.adicionarDisciplinaAoHistorico(disciplina);
        System.out.println("Aluno " + aluno.getMatricula() + " foi matriculado na disciplina " + disciplina.getCodigo() + " turma " + turma.getId() + " com sucesso");
    }

    public static void validarMatricula(Aluno aluno, Turma turma){
        Disciplina disciplina = turma.getDisciplina();
        String alerta = "Aluno " + aluno.getMatricula() + " não foi matriculado na disciplina " + disciplina.getCodigo() + " turma " + turma.getId() + " | Motivo: ";
        try {
            realizarMatricula(aluno, turma);
        } catch (PreRequisitoNaoCumpridoException e) {
            System.out.println(alerta + "Falta de pré requisito | ");
        } catch (CoRequisitoNaoAtendidoException e) {
            //se falhou no verificador de corquisito
            System.out.println(alerta + "Falta de co-requisito | ");
        } catch (CargaHorariaExcedidaException e) {
            //se carga horária total (tem que declarar essa variável) > créditos máximos (constante, perguntei pro gleiph quanto é)
            System.out.println(alerta + "Carga horária excedida | ");
        } catch (ConflictoDeHorarioException e) {
            //se dois dias que tem aula tem horário de início de uma matéria entre horário de início e fim de outra (lembrar de usar as procedências)
            System.out.println(alerta + "Conflito de horário | ");
        } catch (TurmaCheiaException e) {
            System.out.println(alerta + "Turma cheia | ");
        } catch (MatriculaException e) {
            
        }

    }


}
