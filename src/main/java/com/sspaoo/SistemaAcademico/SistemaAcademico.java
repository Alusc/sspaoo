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
    private static Map<Turma, String> turmasAprovadas = new HashMap<>();
    private static Map<Turma, String> turmasRejeitadas = new HashMap<>();
    public static void main(String[] args) {
        System.out.println("Sistema");
        //Exemplo de criação de discplinas e pre requisitos
        Disciplina algoritmos = new DisciplinaObrigatoria("Algoritmos I", "DCC119", 4);
        Disciplina algoritmosPratica = new DisciplinaObrigatoria("Algoritmos Prática", "DCC120", 2);
        algoritmos.setCoRequisito(algoritmosPratica);
        
        Disciplina calculo1 = new DisciplinaEletiva("Cálculo I", "MAT154", 4);
        Disciplina geometriaAnalitica = new DisciplinaObrigatoria("Geometria analítica", "MAT155", 4);
        Disciplina calculo2 = new DisciplinaObrigatoria("Cálculo II", "MAT156", 4);
        calculo2.setPreRequisitos(Arrays.asList(calculo1, geometriaAnalitica), Disciplina.TipoPreRequisito.AND);

       // Exemplos de horários diferentes com início e fim de aula
        java.time.LocalTime[] inicioAula1 = {
            java.time.LocalTime.of(8, 0), // segunda
            java.time.LocalTime.of(0, 0),                         
            java.time.LocalTime.of(0, 0),                        
            java.time.LocalTime.of(0, 0),                         
            java.time.LocalTime.of(10, 0) // sexta
        };
        java.time.LocalTime[] fimAula1 = {
            java.time.LocalTime.of(10, 0), // segunda
            java.time.LocalTime.of(0, 0),                          
            java.time.LocalTime.of(0, 0),                          
            java.time.LocalTime.of(0, 0),                         
            java.time.LocalTime.of(12, 0)  // sexta
        };
        Horario horario1 = new Horario(
            new boolean[]{true, false, false, false, true},
            inicioAula1,
            fimAula1
        );

        java.time.LocalTime[] inicioAula2 = {
            java.time.LocalTime.of(0, 0),
            java.time.LocalTime.of(14, 0), // terça
            java.time.LocalTime.of(16, 0), // quarta
            java.time.LocalTime.of(0, 0),
            java.time.LocalTime.of(0, 0),
        };
        java.time.LocalTime[] fimAula2 = {
            java.time.LocalTime.of(0, 0),
            java.time.LocalTime.of(16, 0), // terça
            java.time.LocalTime.of(18, 0), // quarta
            java.time.LocalTime.of(0, 0),
            java.time.LocalTime.of(0, 0),
        };
        Horario horario2 = new Horario(
            new boolean[]{false, true, true, false, false},
            inicioAula2,
            fimAula2
        );

        java.time.LocalTime[] inicioAula3 = {
            java.time.LocalTime.of(8, 0), // segunda
            java.time.LocalTime.of(10, 0), // terça
            java.time.LocalTime.of(0, 0),
            java.time.LocalTime.of(0, 0),
            java.time.LocalTime.of(0, 0)
        };
        java.time.LocalTime[] fimAula3 = {
            java.time.LocalTime.of(10, 0), // segunda
            java.time.LocalTime.of(12, 0), // terça
            java.time.LocalTime.of(0, 0),
            java.time.LocalTime.of(0, 0),
            java.time.LocalTime.of(0, 0)
        };
        Horario horario3 = new Horario(
            new boolean[]{true, true, false, false, false},
            inicioAula3,
            fimAula3
        );

        java.time.LocalTime[] inicioAula4 = {
            java.time.LocalTime.of(0, 0),
            java.time.LocalTime.of(0, 0),
            java.time.LocalTime.of(0, 0),
            java.time.LocalTime.of(13, 0), // quinta
            java.time.LocalTime.of(15, 0)  // sexta
        };
        java.time.LocalTime[] fimAula4 = {
            java.time.LocalTime.of(0, 0),
            java.time.LocalTime.of(0, 0),
            java.time.LocalTime.of(0, 0),
            java.time.LocalTime.of(15, 0), // quinta
            java.time.LocalTime.of(17, 0)  // sexta
        };
        Horario horario4 = new Horario(
            new boolean[]{false, false, false, true, true},
            inicioAula4,
            fimAula4
        );
        Turma algoritmosTurmaB = new Turma(algoritmos, 'B', 100, horario1);
        Turma algoritmosPraticaTurmaD = new Turma(algoritmosPratica, 'D', 100, horario2);
        Turma calculo1TurmaA = new Turma(calculo1, 'A', 100, horario1);
        Turma geometriaAnaliticaTurmaC = new Turma(geometriaAnalitica, 'C', 100, horario3); 
        Turma calculo2TurmaA = new Turma(calculo2, 'A', 100, horario4);
        
        Aluno aluno1 = new Aluno("Nome", "202500000");

        aluno1.setPlanejamentoFuturo(Arrays.asList( 
            algoritmosTurmaB,
            geometriaAnaliticaTurmaC,
            calculo1TurmaA,
            algoritmosPraticaTurmaD,
            calculo2TurmaA
        ));
        
        matricularAluno(aluno1);
    
    }

    public static void matricularAluno(Aluno aluno) {
        turmasAprovadas.clear();
        turmasRejeitadas.clear();
        aluno.setCargaHorariaSemanal(0);
        List<Turma> planejamentoFuturo = aluno.getPlanejamentoFuturo();
        if (planejamentoFuturo.isEmpty()){
            System.out.println("O aluno não escolheu nenhuma turma");
            return;
        }
        System.out.println("Relatorio de matrícula");
        System.out.println("Nome: " + aluno.getNome());
        System.out.println("Número de matrícula: " + aluno.getMatricula());
        for (Turma turma: planejamentoFuturo)
            validarMatricula(aluno, turma);
        
        System.out.println("Turmas aprovadas: ");
        for (String alerta: turmasAprovadas.values())
            System.out.println(alerta);

        System.out.println();
        System.out.println("Turmas rejeitadas: ");
        for (String alerta: turmasRejeitadas.values())
            System.out.println(alerta);
        System.out.println();

        for (Turma turmaMatriculada: turmasAprovadas.keySet()) {
            turmaMatriculada.setAlunosMatriculados(turmaMatriculada.getAlunosMatriculados() + 1);
            aluno.adicionarDisciplinaAoHistorico(turmaMatriculada.getDisciplina());
        }
    }
    private static Integer horarioConflita(Aluno aluno, Turma turma){
        List<Turma> planejamentoFuturo = aluno.getPlanejamentoFuturo();
        Horario horario = turma.getHorario();
        String alertaNaoMatriculado = "Aluno " + aluno.getMatricula() + " não foi matriculado na disciplina ";
        String alertaMatriculado = "Aluno " + aluno.getMatricula() + " não foi matriculado na disciplina ";

        for(Turma turmaJaMatriculada: turmasAprovadas.keySet()){
            Horario horarioJaMatriculado = turmaJaMatriculada.getHorario();
            for(int i = 0; i < 5; i++){
                if(horario.temAulaNesseDia(i) && horarioJaMatriculado.temAulaNesseDia(i)){
                    if(horario.fimDaAulaNoDia(i).isAfter(horarioJaMatriculado.inicioDaAulaNoDia(i)) && horario.inicioDaAulaNoDia(i).isBefore(horarioJaMatriculado.fimDaAulaNoDia(i)) ){
                        Disciplina disciplina = turma.getDisciplina();
                        Disciplina disciplinaJaMatriculada = turmaJaMatriculada.getDisciplina();
                        if(disciplina == disciplinaJaMatriculada)
                        {
                            continue;
                        }
                        if(disciplina.getPrecedencia() == disciplinaJaMatriculada.getPrecedencia()){
                            return 1;
                        }
                        if(disciplina.getPrecedencia() > disciplinaJaMatriculada.getPrecedencia()){
                            turmasAprovadas.put(turma, alertaMatriculado + turma.getDisciplina().getCodigo() + " turma " + turma.getId());
                            turmasAprovadas.remove(turmaJaMatriculada);
                            turmasRejeitadas.put(turmaJaMatriculada, alertaNaoMatriculado + turmaJaMatriculada.getDisciplina().getCodigo() +
                                    " turma " + turmaJaMatriculada.getId() + " | Motivo: Conflito de horário | ");
                            return -1;
                        }
                        if(disciplina.getPrecedencia() < disciplinaJaMatriculada.getPrecedencia()){
                            turmasRejeitadas.put(turma, alertaNaoMatriculado + turma.getDisciplina().getCodigo() +
                                    " turma " + turma.getId() +  " | Motivo: Conflito de horário | ");
                            return 2;
                        }
                
                    }
                }
            }
        }
        return 0;
    }
    public static void realizarMatricula(Aluno aluno, Turma turma) throws MatriculaException {
        if (turmasRejeitadas.containsKey(turma))
            return;

        Disciplina disciplina = turma.getDisciplina();
        ValidadorLogico validadorLogico = disciplina.getValidadorLogico();

        if (validadorLogico != null && !validadorLogico.validar(aluno, disciplina))
            throw new PreRequisitoNaoCumpridoException("Pré requisito não cumprido");
        
        ValidadorCoRequisito validadorCoRequisito = new ValidadorCoRequisito();
        if (!validadorCoRequisito.validar(aluno, disciplina))
            throw new CoRequisitoNaoAtendidoException("Co-requisito não cumprido");
        
        if (aluno.getCargaHorariaSemanal() + disciplina.getCargaHoraria() > CARGA_HORARIA_MAXIMA)
            throw new CargaHorariaExcedidaException("Carga horária máxima excedida");
    
        if (turma.isCheia())
            throw new TurmaCheiaException("A turma selecionada não possui vagas disponíveis");

        if(horarioConflita(aluno, turma) == 1)
            throw new ConflictoDeHorarioException("A turma selecionada tem horários conflitando com outra turma de disciplinas de mesma precedência");
        
        //Se passar por todas as exceções
        // turma.setAlunosMatriculados(turma.getAlunosMatriculados() + 1);
        // aluno.adicionarDisciplinaAoHistorico(disciplina);
        if(horarioConflita(aluno, turma) == 0)
            turmasAprovadas.put(turma, "Aluno " + aluno.getMatricula() + " foi matriculado na disciplina " + disciplina.getCodigo() + " turma " + turma.getId() + " com sucesso");
    }

    public static void validarMatricula(Aluno aluno, Turma turma){
        boolean erro = false;
        Disciplina disciplina = turma.getDisciplina();
        String alerta = "Aluno " + aluno.getMatricula() + " não foi matriculado na disciplina " + disciplina.getCodigo() + " turma " + turma.getId() + " | Motivo: ";
        try {
            realizarMatricula(aluno, turma);
        } catch (PreRequisitoNaoCumpridoException e) {
            erro = true;
            turmasRejeitadas.put(turma, alerta + "Falta de pré requisito | ");
        } catch (CoRequisitoNaoAtendidoException e) {
            erro = true;
            turmasRejeitadas.put(turma, alerta + "Falta de co-requisito | ");
        } catch (CargaHorariaExcedidaException e) {
            erro = true;
            turmasRejeitadas.put(turma, alerta + "Carga horária máxima excedida | ");
        } catch (ConflictoDeHorarioException e) {
            erro = true;
            turmasRejeitadas.put(turma, alerta + "Conflito de horário | ");
        } catch (TurmaCheiaException e) {
            erro = true;
            turmasRejeitadas.put(turma, alerta + "Turma cheia | ");
        } catch (MatriculaException e) {
            
        }
        if (erro && disciplina.getCoRequisito() != null) {
            Turma turmaCoRequisito = null;
            for (Turma turmaMatriculada : aluno.getPlanejamentoFuturo()) {
                if (turmaMatriculada.getDisciplina() == disciplina.getCoRequisito())
                    turmaCoRequisito = turmaMatriculada;
            }
            if (turmaCoRequisito != null) {
                String novoAlerta = "Aluno " + aluno.getMatricula() + " não foi matriculado na disciplina " + turmaCoRequisito.getDisciplina().getCodigo() + " turma " + turmaCoRequisito.getId() + " | Motivo: ";
                turmasRejeitadas.put(turmaCoRequisito, novoAlerta + "Falta de co-requisito | ");
                turmasAprovadas.remove(turmaCoRequisito);
            }
        }
    }


}
