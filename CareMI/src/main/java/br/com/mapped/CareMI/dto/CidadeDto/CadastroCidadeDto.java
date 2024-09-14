package br.com.mapped.CareMI.dto.CidadeDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados para cadastro de uma cidade")
public record CadastroCidadeDto(

        @Schema(description = "Nome da cidade", maxLength = 100, example = "Rio de Janeiro")
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome

) {
}