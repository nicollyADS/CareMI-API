package br.com.mapped.CareMI.dto.AutenticacaoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DetalhesAutenticacaoDto(
        @NotBlank(message = "O CPF não pode estar em branco")
        @Size(min = 11, max = 15, message = "O CPF deve ter no máximo 15 caracteres")
        String cpf,

        @NotBlank(message = "A senha não pode estar em branco")
        @Size(max = 50, message = "A senha deve ter no máximo 50 caracteres")
        String senha) {
}
