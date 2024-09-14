package br.com.mapped.CareMI.dto.EnderecoHospitalDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados para atualização do endereço do hospital")
public record AtualizacaoEnderecoHospitalDto(

        @Schema(description = "Número do logradouro", example = "456")
        @NotNull(message = "O logradouro não pode estar em branco")
        @Positive(message = "O logradouro deve ser um número positivo")
        Integer numLogradouro,

        @Schema(description = "Ponto de referência do endereço", maxLength = 100, example = "Ao lado da entrada principal")
        @NotBlank(message = "O ponto de referência não pode estar em branco")
        @Size(max = 100, message = "O ponto de referência deve ter no máximo 100 caracteres")
        String pontoReferencia,

        @Schema(description = "Complemento do endereço", maxLength = 100, example = "Sala 101")
        @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres")
        String complemento

) {
}