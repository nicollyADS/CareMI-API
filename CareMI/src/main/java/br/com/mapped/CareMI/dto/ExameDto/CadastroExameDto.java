package br.com.mapped.CareMI.dto.ExameDto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CadastroExameDto(
        @NotNull(message = "A data não pode estar em branco")
        @FutureOrPresent(message = "A data deve ser atual ou estar no futuro")
        LocalDate data,

        @NotBlank(message = "A descrição não pode estar em branco")
        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String descricao,

        //resultadoExame
        @NotBlank(message = "A descrição não pode estar em branco")
        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String descricaoExame,

        @Size(max = 100, message = "A observação deve ter no máximo 100 caracteres")
        String observacao,

        @NotNull(message = "O resultado não pode ser nulo")
        Integer resultado,

        @NotNull(message = "Os glóbulos vermelhos não podem ser nulos")
        Integer globulosVermelhos,

        @NotNull(message = "Os glóbulos brancos não podem ser nulos")
        Integer globulosBrancos,

        @NotNull(message = "As plaquetas não podem ser nulas")
        Integer plaquetas,

        @NotNull(message = "A hemoglobina glicada não pode ser nula")
        Integer homoglobinaGlicada,

        @NotNull(message = "A creatina não pode ser nula")
        Integer creatina,

        @NotNull(message = "O colesterol total não pode ser nulo")
        Integer colesterolTotal,

        @NotNull(message = "O colesterol HDL não pode ser nulo")
        Integer colesterolHDL,

        @NotNull(message = "O colesterol LDL não pode ser nulo")
        Integer colesterolLDL,

        @NotNull(message = "Os triglicerídeos não podem ser nulos")
        Integer teglicerides,

        @NotNull(message = "O hormônio tireoestimulante (TSH) não pode ser nulo")
        Integer hormonioTrioestimulanteTSH
) {
}
