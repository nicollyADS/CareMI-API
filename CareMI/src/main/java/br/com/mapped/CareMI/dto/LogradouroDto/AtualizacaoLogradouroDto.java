package br.com.mapped.CareMI.dto.LogradouroDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Schema(description = "Dados para atualização de um logradouro")
public record AtualizacaoLogradouroDto(

        @Schema(description = "Nome do logradouro", maxLength = 100, example = "Rua das Flores")
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 100, message = "O nome do logradouro deve ter no máximo 100 caracteres")
        String nome

) {
}
