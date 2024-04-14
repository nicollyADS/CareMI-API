package br.com.mapped.CareMI.dto.PlanoSaudeDto;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record AtualizacaoPlanoSaudeDto(
        @NotBlank(message = "A razão social não pode estar em branco")
        @Size(max = 100, message = "A razão social deve ter no máximo 100 caracteres")
        String razaoSocial,

        @NotBlank(message = "O nome fantasia não pode estar em branco")
        @Size(max = 100, message = "O nome fantasia deve ter no máximo 100 caracteres")
        String fantasia,

        @NotNull(message = "O CNPJ não pode ser nulo")
        @Positive(message = "O número do cnpj deve ser um número positivo")
        Long cnpj,

        @NotBlank(message = "O contato não pode estar em branco")
        @Size(max = 100, message = "O contato deve ter no máximo 100 caracteres")
        String contato,

        @NotBlank(message = "O telefone não pode estar em branco")
        @Size(max = 15, message = "O telefone deve ter no máximo 15 caracteres")
        String telefone,

        @NotNull(message = "A data de cadastro não pode ser nula")
        LocalDate dataCadastro,

        @NotNull(message = "O campo 'ativo' não pode ser nulo")
        @PositiveOrZero(message = "O campo 'ativo' deve ser um número positivo ou zero")
        @Max(value = 1, message = "o campo ativo deve conter apenas 1 caractere")
        Integer ativo
) {
}
