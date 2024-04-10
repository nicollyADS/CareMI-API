package br.com.mapped.CareMI.dto.ResultadoExameDto;

public record CadastroResultadoExameDto(String descricao, String observacao, int resultado, int globulosVermelhos, int globulosBrancos, int plaquetas, int homoglobinaGlicada, int creatina, int colesterolTotal, int colesterolHDL, int colesterolLDL, int teglicerides, int hormonioTrioestimulanteTSH) {
}
