package com.sspaoo.SistemaAcademico;

import java.util.Arrays;

import com.sspaoo.Disciplina.*;
import com.sspaoo.TratamentoDeExcecoes.*;

public class SistemaAcademico {
    public static void main(String[] args) {
        System.out.println("Sistema");
        //Exemplo de criação de discplinas e pre requisitos
        Disciplina calculo1 = new DisciplinaObrigatoria("Cálculo I", "MAT154", 4);
        Disciplina geometriaAnalitica = new DisciplinaObrigatoria("Geometria analítica", "MAT155", 4);
        Disciplina calculo2 = new DisciplinaObrigatoria("Cálculo II", "MAT156", 4);
        calculo2.setPreRequisitos(Arrays.asList(calculo1, geometriaAnalitica));

        //Printando os pre requisitos de calculo 2 pra mostrar que funciona
        for (Disciplina preRequisito : calculo2.getPreRequisitos()) {
            System.out.println(preRequisito.getCodigo());
        }
    }
    public void validaMatricula(){
/*
                try {
                    //realizar matricula
                    
 
        } catch (PreRequisitoNaoCumpridoException e) {
            //se falhou no and e no or
        } catch (CoRequisitoNaoAtendidoException e) {
            //se falhou no verificador de corquisito
        } catch (CargaHorariaExcedidaException e) {
            //se carga horária total (tem que declarar essa variável) > créditos máximos (constante, perguntei pro gleiph quanto é)
        } catch (ConflictoDeHorarioException e) {
            //se dois dias que tem aula tem horário de início de uma matéria entre horário de início e fim de outra (lembrar de usar as procedências)
        } catch (TurmaCheiaException e) {
            //se turmas.alunosmatriculados>=turma.vagas (lembrar de usar o ira pra desempatar)
        } catch (MatriculaException e) {
            
        }
*/
    }

}
