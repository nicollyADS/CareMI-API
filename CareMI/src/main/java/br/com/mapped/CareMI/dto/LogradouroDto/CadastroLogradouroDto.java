package br.com.mapped.CareMI.dto.LogradouroDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroLogradouroDto(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 100, message = "o nome do logradouro deve ter no máximo 100 caracteres")
        String nome
) {
}
