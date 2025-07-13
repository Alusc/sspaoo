package com.sspaoo.SistemaAcademico;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.sspaoo.Aluno.Aluno;
import com.sspaoo.Disciplina.*;
import com.sspaoo.TratamentoDeExcecoes.*;
import com.sspaoo.Validadores.*;
import com.sspaoo.Turma.Horario;
import com.sspaoo.Turma.Turma;

public class SistemaAcademico {
    private static final int CARGA_HORARIA_MAXIMA = 32;
    private static Map<Turma, String> mensagemPorTurma = new HashMap<>();
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
        algoritmos.setTurma(algoritmosTurmaB);
        Turma algoritmosPraticaTurmaD = new Turma(algoritmosPratica, 'D', 100, null);
        algoritmosPratica.setTurma(algoritmosPraticaTurmaD);
        Turma calculo1TurmaA = new Turma(calculo1, 'A', 100, null);
        calculo1.setTurma(calculo1TurmaA);
        Turma geometriaAnaliticaTurmaC = new Turma(geometriaAnalitica, 'C', 100, null); 
        geometriaAnalitica.setTurma(geometriaAnaliticaTurmaC);
        Turma calculo2TurmaA = new Turma(calculo2, 'A', 100, null);
        calculo2.setTurma(calculo2TurmaA);
        algoritmosPraticaTurmaD.setAlunosMatriculados(100);
        
        Aluno aluno1 = new Aluno("Nome", "202500000");
        aluno1.adicionarDisciplinaAoHistorico(calculo1);
        aluno1.adicionarDisciplinaAoHistorico(geometriaAnalitica);
        aluno1.atualizarNotaNoHistorico(calculo1, 60);
        aluno1.atualizarNotaNoHistorico(geometriaAnalitica, 100);
        aluno1.setPlanejamentoFuturo(Arrays.asList(
            algoritmosTurmaB,
            algoritmosPraticaTurmaD,
            calculo2TurmaA
            ));
        mensagemPorTurma.clear();
        matricularAluno(aluno1);
        
        for(Turma turma: aluno1.getPlanejamentoFuturo())
        {
            System.out.println(mensagemPorTurma.get(turma));
        }
    }

    public static void matricularAluno(Aluno aluno) {
        aluno.setCargaHorariaSemanal(0);
        List<Turma> planejamentoFuturo = aluno.getPlanejamentoFuturo();
        if (planejamentoFuturo.isEmpty()){
            System.out.println("O aluno não escolheu nenhuma turma");
            return;
        }
        for (Turma turma: planejamentoFuturo)
        {
            validarMatricula(aluno, turma);
            if(!turma.getDisciplina().isMatriculado())
                validarMatricula(aluno, turma.getDisciplina().getCoRequisito().getTurma());
        }
    }

    public static void realizarMatricula(Aluno aluno, Turma turma) throws MatriculaException {
        //Esse é o código que vai disparar as exceções
        Disciplina disciplina = turma.getDisciplina();
        ValidadorLogico validadorLogico = disciplina.getValidadorLogico();

        if (validadorLogico != null && !validadorLogico.validar(aluno, disciplina))
        {
            turma.getDisciplina().setStatus(false);
            throw new PreRequisitoNaoCumpridoException("Pré requisito não cumprido");
        }
            
        ValidadorCoRequisito validadorCoRequisito = new ValidadorCoRequisito();
        if (!validadorCoRequisito.validar(aluno, disciplina))
        {
            turma.getDisciplina().setStatus(false);
            throw new CoRequisitoNaoAtendidoException("Co-requisito não cumprido");
        }
        
        if (aluno.getCargaHorariaSemanal() + disciplina.getCargaHoraria() >= CARGA_HORARIA_MAXIMA)
        {
            turma.getDisciplina().setStatus(false);
            throw new CargaHorariaExcedidaException("Carga horária máxima excedida");
        }

        if (turma.isCheia())
        {
            turma.getDisciplina().setStatus(false);
            throw new TurmaCheiaException("A turma selecionada não possui vagas disponíveis");
        }
        
        //Se passar por todas as exceções
        turma.getDisciplina().setStatus(true);
        turma.setAlunosMatriculados(turma.getAlunosMatriculados() + 1);
        aluno.adicionarDisciplinaAoHistorico(disciplina);
        mensagemPorTurma.put(turma, "Aluno " + aluno.getMatricula() + " foi matriculado na disciplina " + disciplina.getCodigo() + " turma " + turma.getId() + " com sucesso");
    }

    public static void validarMatricula(Aluno aluno, Turma turma){
        Disciplina disciplina = turma.getDisciplina();
        String alerta = "Aluno " + aluno.getMatricula() + " não foi matriculado na disciplina " + disciplina.getCodigo() + " turma " + turma.getId() + " | Motivo: ";
        try {
            realizarMatricula(aluno, turma);
        } catch (PreRequisitoNaoCumpridoException e) {
            mensagemPorTurma.put(turma, alerta + "Falta de pré requisito | ");
        } catch (CoRequisitoNaoAtendidoException e) {
            //se falhou no verificador de corquisito
            mensagemPorTurma.put(turma, alerta + "Falta de co-requisito | ");
        } catch (CargaHorariaExcedidaException e) {
            //se carga horária total (tem que declarar essa variável) > créditos máximos (constante, perguntei pro gleiph quanto é)
            mensagemPorTurma.put(turma, alerta + "Carga horária máxima excedida | ");
        } catch (ConflictoDeHorarioException e) {
            //se dois dias que tem aula tem horário de início de uma matéria entre horário de início e fim de outra (lembrar de usar as procedências)
            mensagemPorTurma.put(turma, alerta + "Conflito de horário | ");
        } catch (TurmaCheiaException e) {
            mensagemPorTurma.put(turma, alerta + "Turma cheia | ");
        } catch (MatriculaException e) {
            
        }

    }


}
