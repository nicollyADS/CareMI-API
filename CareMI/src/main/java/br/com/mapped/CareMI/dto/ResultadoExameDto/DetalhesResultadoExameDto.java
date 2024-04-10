package br.com.mapped.CareMI.dto.ResultadoExameDto;

import br.com.mapped.CareMI.model.ResultadoExame;

public record DetalhesResultadoExameDto(Long id, String descricao, String observacao, Integer resultado, Integer globulosVermelhos, Integer globulosBrancos, Integer plaquetas, Integer homoglobinaGlicada, Integer creatina, Integer colesterolTotal, Integer colesterolHDL, Integer colesterolLDL, Integer teglicerides, Integer hormonioTrioestimulanteTSH) {

    public DetalhesResultadoExameDto(ResultadoExame resultadoExame) {
        this(resultadoExame.getId(), resultadoExame.getDescricao(), resultadoExame.getObservacao(), resultadoExame.getResultado(), resultadoExame.getGlobulosVermelhos(), resultadoExame.getGlobulosBrancos(), resultadoExame.getPlaquetas(), resultadoExame.getHomoglobinaGlicada(), resultadoExame.getCreatina(), resultadoExame.getColesterolTotal(), resultadoExame.getColesterolHDL(), resultadoExame.getColesterolLDL(), resultadoExame.getTeglicerides(), resultadoExame.getHormonioTrioestimulanteTSH());
    }
}