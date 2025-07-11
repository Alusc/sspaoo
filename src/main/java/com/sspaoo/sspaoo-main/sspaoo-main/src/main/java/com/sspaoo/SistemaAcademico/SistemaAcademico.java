package com.sspaoo.SistemaAcademico;

import java.util.Arrays;

import com.sspaoo.Disciplina.*;

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

}
