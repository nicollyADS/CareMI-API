package br.com.mapped.CareMI.dto.ExameDto;

import br.com.mapped.CareMI.model.Exame;

import java.time.LocalDate;

public record DetalhesExameDto(Long idExame, LocalDate data, String descricao,
                               String descricaoExame, String observacao, Integer resultado, Integer globulosVermelhos, Integer globulosBrancos,
                               Integer plaquetas, Integer homoglobinaGlicada, Integer creatina, Integer colesterolTotal, Integer colesterolHDL, Integer colesterolLDL,
                               Integer teglicerides, Integer hormonioTrioestimulanteTSH) {

    public DetalhesExameDto(Exame exame) {
        this(
                //exame
                exame.getIdExame(), exame.getData(), exame.getDescricao(),
                //resultadoExame
                exame.getResultadoExame().getDescricaoExame(), exame.getResultadoExame().getObservacao(), exame.getResultadoExame().getResultado(),
                exame.getResultadoExame().getGlobulosVermelhos(), exame.getResultadoExame().getGlobulosBrancos(), exame.getResultadoExame().getPlaquetas(),
                exame.getResultadoExame().getHomoglobinaGlicada(), exame.getResultadoExame().getCreatina(), exame.getResultadoExame().getColesterolTotal(),
                exame.getResultadoExame().getColesterolHDL(), exame.getResultadoExame().getColesterolLDL(), exame.getResultadoExame().getTeglicerides(),
                exame.getResultadoExame().getHormonioTrioestimulanteTSH()
        );
    }
}
