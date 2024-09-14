package br.com.mapped.CareMI.dto.BairroDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Dados para cadastrar um bairro")
public record CadastroBairroDto(

        @Schema(description = "Nome do bairro", example = "Ipanema", maxLength = 100)
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @Schema(description = "Zona onde o bairro está localizado", example = "Zona Sul", maxLength = 100)
        @NotBlank(message = "A zona não pode estar em branco")
        @Size(max = 100, message = "A zona deve ter no máximo 100 caracteres")
        String zona,

        @Schema(description = "CEP do bairro", example = "22221-000", minLength = 8, maxLength = 10)
        @NotBlank(message = "O CEP não pode estar em branco")
        @Size(min = 8, max = 10, message = "O CEP deve ter de 8 a 10 caracteres")
        String cep
) {
}