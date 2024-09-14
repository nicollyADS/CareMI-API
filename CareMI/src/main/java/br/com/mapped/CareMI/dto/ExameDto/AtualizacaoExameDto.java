package br.com.mapped.CareMI.dto.ExameDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "Dados para atualização de um exame")
public record AtualizacaoExameDto(

        @Schema(description = "Data do exame", example = "2024-09-15")
        @NotNull(message = "A data não pode estar em branco")
        @FutureOrPresent(message = "A data deve ser atual ou estar no futuro")
        LocalDate data,

        @Schema(description = "Descrição do exame", maxLength = 500, example = "Exame de sangue completo")
        @NotBlank(message = "A descrição não pode estar em branco")
        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String descricao,

        // resultadoExame
        @Schema(description = "Descrição do resultado do exame", maxLength = 500, example = "Resultado normal")
        @NotBlank(message = "A descrição não pode estar em branco")
        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String descricaoExame,

        @Schema(description = "Observação adicional sobre o exame", maxLength = 100, example = "Nenhuma observação")
        @Size(max = 100, message = "A observação deve ter no máximo 100 caracteres")
        String observacao,

        @Schema(description = "Resultado do exame", example = "85")
        @NotNull(message = "O resultado não pode ser nulo")
        Integer resultado,

        @Schema(description = "Quantidade de glóbulos vermelhos", example = "5")
        @NotNull(message = "Os glóbulos vermelhos não podem ser nulos")
        Integer globulosVermelhos,

        @Schema(description = "Quantidade de glóbulos brancos", example = "7")
        @NotNull(message = "Os glóbulos brancos não podem ser nulos")
        Integer globulosBrancos,

        @Schema(description = "Quantidade de plaquetas", example = "150")
        @NotNull(message = "As plaquetas não podem ser nulas")
        Integer plaquetas,

        @Schema(description = "Nível de hemoglobina glicada", example = "6.5")
        @NotNull(message = "A hemoglobina glicada não pode ser nula")
        Integer homoglobinaGlicada,

        @Schema(description = "Nível de creatina", example = "1.2")
        @NotNull(message = "A creatina não pode ser nula")
        Integer creatina,

        @Schema(description = "Nível de colesterol total", example = "200")
        @NotNull(message = "O colesterol total não pode ser nulo")
        Integer colesterolTotal,

        @Schema(description = "Nível de colesterol HDL", example = "60")
        @NotNull(message = "O colesterol HDL não pode ser nulo")
        Integer colesterolHDL,

        @Schema(description = "Nível de colesterol LDL", example = "130")
        @NotNull(message = "O colesterol LDL não pode ser nulo")
        Integer colesterolLDL,

        @Schema(description = "Nível de triglicerídeos", example = "150")
        @NotNull(message = "Os triglicerídeos não podem ser nulos")
        Integer teglicerides,

        @Schema(description = "Nível de hormônio tireoestimulante (TSH)", example = "2.5")
        @NotNull(message = "O hormônio tireoestimulante (TSH) não pode ser nulo")
        Integer hormonioTrioestimulanteTSH

) {
}