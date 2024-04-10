package br.com.mapped.CareMI.dto.ResultadoExameDto;

public record CadastroResultadoExameDto(String descricao, String observacao, Integer resultado, Integer globulosVermelhos, Integer globulosBrancos, Integer plaquetas, Integer homoglobinaGlicada, Integer creatina, Integer colesterolTotal, Integer colesterolHDL, Integer colesterolLDL, Integer teglicerides, Integer hormonioTrioestimulanteTSH) {
}