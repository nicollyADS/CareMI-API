package br.com.mapped.CareMI.dto.ResultadoExameDto;

import br.com.mapped.CareMI.model.ResultadoExame;

public record DetalhesResultadoExameDto(Long id, String descricao, String observacao, int resultado, int globulosVermelhos, int globulosBrancos, int plaquetas, int homoglobinaGlicada, int creatina, int colesterolTotal, int colesterolHDL, int colesterolLDL, int teglicerides, int hormonioTrioestimulanteTSH) {

    public DetalhesResultadoExameDto(ResultadoExame resultadoExame) {
        this(resultadoExame.getId(), resultadoExame.getDescricao(), resultadoExame.getObservacao(), resultadoExame.getResultado(), resultadoExame.getGlobulosVermelhos(), resultadoExame.getGlobulosBrancos(), resultadoExame.getPlaquetas(), resultadoExame.getHomoglobinaGlicada(), resultadoExame.getCreatina(), resultadoExame.getColesterolTotal(), resultadoExame.getColesterolHDL(), resultadoExame.getColesterolLDL(), resultadoExame.getTeglicerides(), resultadoExame.getHormonioTrioestimulanteTSH());
    }
}