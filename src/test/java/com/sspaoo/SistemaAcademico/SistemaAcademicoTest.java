package com.sspaoo.SistemaAcademico;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Arrays;

import com.sspaoo.Aluno.Aluno;
import com.sspaoo.Disciplina.DisciplinaObrigatoria;
import com.sspaoo.Turma.Horario;
import com.sspaoo.Turma.Turma;
import com.sspaoo.TratamentoDeExcecoes.*;

class SistemaAcademicoTest {

    private Aluno aluno;
    private DisciplinaObrigatoria disciplina;
    private Turma turma;
    private DisciplinaObrigatoria disciplinaCoRequisito;
    private Turma turmaCoRequisito;

    @BeforeEach
    void setUp() {
        aluno = new Aluno("João Silva", "202500001");
        disciplina = new DisciplinaObrigatoria("Geometria Analitica", "MAT155", 4);
        
        boolean[] diasSemAula = new boolean[5];
        LocalTime[] iniciosVazios = new LocalTime[5];
        LocalTime[] finsVazios = new LocalTime[5];
        Horario horarioVazio = new Horario(diasSemAula, iniciosVazios, finsVazios);

        LocalTime[] inicioAulaGA = new LocalTime[5];
        LocalTime[] fimAulaGA = new LocalTime[5];
        LocalTime[] inicioAulaCalculo1 = new LocalTime[5];
        LocalTime[] fimAulaCalculo1 = new LocalTime[5];
        

        inicioAulaGA[0] = LocalTime.of(8, 0);
        inicioAulaGA[4] = LocalTime.of(8, 0);
        fimAulaGA[0] = LocalTime.of(10, 0);
        fimAulaGA[4] = LocalTime.of(10, 0);
        inicioAulaCalculo1[2] = LocalTime.of(14, 0);
        fimAulaCalculo1[4] = LocalTime.of(16, 0);
        Horario horarioDeGA = new Horario(
            new boolean[]{true, false, false, false, true},
            inicioAulaGA,
            fimAulaGA
        );
        
        Horario horarioDeCalculo = new Horario(
                new boolean[] {false, false, true, false, true},
                inicioAulaCalculo1,
                fimAulaCalculo1
        );
        
        
        turma = new Turma(disciplina, 'A', 2, horarioDeGA);
        
        disciplinaCoRequisito = new DisciplinaObrigatoria("Algoritmos Prática", "DCC120", 2);
        turmaCoRequisito = new Turma(disciplinaCoRequisito, 'B', 2, horarioVazio);
        disciplina.setCoRequisito(disciplinaCoRequisito);
    }

    @Test
    void testMatriculaBemSucedida() {
        aluno.setCargaHorariaSemanal(10);
        turma.setAlunosMatriculados(1); // 1 vaga disponível
        
        assertDoesNotThrow(() -> SistemaAcademico.realizarMatricula(aluno, turma));
        assertTrue(aluno.getHistorico().containsKey(turma.getDisciplina()));
    }

    @Test
    void testPreRequisitoNaoCumpridoException() {
        DisciplinaObrigatoria preRequisito = new DisciplinaObrigatoria("Pré-requisito", "PRE001", 4);
        disciplina.setPreRequisitos(Arrays.asList(preRequisito), DisciplinaObrigatoria.TipoPreRequisito.AND);
        
        Exception exception = assertThrows(PreRequisitoNaoCumpridoException.class,
            () -> SistemaAcademico.realizarMatricula(aluno, turma));
        
        assertFalse(aluno.getHistorico().containsKey(turma.getDisciplina()));
        assertTrue(exception.getMessage().contains("Pré requisito não cumprido"));
    }

    @Test
    void testCoRequisitoNaoAtendidoException() {
        aluno.setPlanejamentoFuturo(Arrays.asList(turma)); // Apenas a disciplina principal
        
        Exception exception = assertThrows(CoRequisitoNaoAtendidoException.class,
            () -> SistemaAcademico.realizarMatricula(aluno, turma));
        
        assertFalse(aluno.getHistorico().containsKey(turma.getDisciplina()));
        assertTrue(exception.getMessage().contains("Co-requisito não cumprido"));
    }

    @Test
    void testTurmaCheiaException() {
        turma.setAlunosMatriculados(2); // Todas as vagas ocupadas
        
        Exception exception = assertThrows(TurmaCheiaException.class,
            () -> SistemaAcademico.realizarMatricula(aluno, turma));
        
        assertFalse(aluno.getHistorico().containsKey(turma.getDisciplina()));
        assertTrue(exception.getMessage().contains("não possui vagas disponíveis"));
    }

    @Test
    void testCargaHorariaExcedidaException() {
        aluno.setCargaHorariaSemanal(30); // Limite é 32
        disciplina.setCargaHoraria(4); // 30 + 4 = 34 > 32
        
        Exception exception = assertThrows(CargaHorariaExcedidaException.class,
            () -> SistemaAcademico.realizarMatricula(aluno, turma));
        
        assertFalse(aluno.getHistorico().containsKey(turma.getDisciplina()));
        assertTrue(exception.getMessage().contains("Carga horária máxima excedida"));
    }

    @Test
    void testConflitoHorario() {
        boolean[] diasAula = new boolean[5];
        LocalTime[] inicios = new LocalTime[5];
        LocalTime[] fins = new LocalTime[5];
        
        diasAula[0] = true;
        inicios[0] = LocalTime.of(8, 0);
        fins[0] = LocalTime.of(10, 0);
        
        Horario horario1 = new Horario(diasAula.clone(), inicios.clone(), fins.clone());
        
        inicios[0] = LocalTime.of(9, 0);
        fins[0] = LocalTime.of(11, 0);
        Horario horario2 = new Horario(diasAula.clone(), inicios.clone(), fins.clone());
        
        Turma turma1 = new Turma(disciplina, 'A', 2, horario1);
        DisciplinaObrigatoria disciplina2 = new DisciplinaObrigatoria("Cálculo I", "MAT101", 4);
        Turma turma2 = new Turma(disciplina2, 'B', 2, horario2);
        
        aluno.setPlanejamentoFuturo(Arrays.asList(turma1, turma2));
        
        Exception exception = assertThrows(ConflictoDeHorarioException.class,
            () -> SistemaAcademico.realizarMatricula(aluno, turma1));
        
        assertFalse(aluno.getHistorico().containsKey(turma1.getDisciplina()));
        assertTrue(exception.getMessage().contains("horários conflitando"));
    }
}
