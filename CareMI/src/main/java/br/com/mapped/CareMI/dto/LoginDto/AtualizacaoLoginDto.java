package br.com.mapped.CareMI.dto.LoginDto;

import jakarta.validation.constraints.*;

public record AtualizacaoLoginDto(
        @NotBlank(message = "O CPF não pode estar em branco")
        @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres")
        String cpf,

        @NotBlank(message = "A senha não pode estar em branco")
        @Size(max = 100, message = "A senha deve ter no máximo 100 caracteres")
        String senha,

        @NotNull(message = "O campo 'ativo' não pode ser nulo")
        @PositiveOrZero(message = "O campo 'ativo' deve ser um número positivo ou zero")
        @Max(value = 1, message = "o campo ativo deve conter apenas 1 caractere")
        Integer ativo
) {
}
