package br.com.mapped.CareMI.dto.PlanoSaudeDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "Dados para o cadastro de um plano de saúde")
public record CadastroPlanoSaudeDto(

        @Schema(description = "Razão social da empresa", maxLength = 100, example = "Saúde e Cia LTDA")
        @NotBlank(message = "A razão social não pode estar em branco")
        @Size(max = 100, message = "A razão social deve ter no máximo 100 caracteres")
        String razaoSocial,

        @Schema(description = "Nome fantasia da empresa", maxLength = 100, example = "Saúde Fácil")
        @NotBlank(message = "O nome fantasia não pode estar em branco")
        @Size(max = 100, message = "O nome fantasia deve ter no máximo 100 caracteres")
        String fantasia,

        @Schema(description = "CNPJ da empresa", example = "12345678000195")
        @NotNull(message = "O CNPJ não pode ser nulo")
        @Positive(message = "O número do CNPJ deve ser um número positivo")
        Long cnpj,

        @Schema(description = "Contato da empresa", maxLength = 100, example = "João Silva")
        @NotBlank(message = "O contato não pode estar em branco")
        @Size(max = 100, message = "O contato deve ter no máximo 100 caracteres")
        String contato,

        @Schema(description = "Telefone de contato da empresa", maxLength = 15, example = "+55 11 98765-4321")
        @NotBlank(message = "O telefone não pode estar em branco")
        @Size(max = 15, message = "O telefone deve ter no máximo 15 caracteres")
        String telefone,

        @Schema(description = "Data de cadastro do plano de saúde", example = "2024-01-01")
        @NotNull(message = "A data de cadastro não pode ser nula")
        LocalDate dataCadastro,

        @Schema(description = "Campo que indica se o plano de saúde está ativo (1 para ativo, 0 para inativo)", example = "1")
        @NotNull(message = "O campo 'ativo' não pode ser nulo")
        @PositiveOrZero(message = "O campo 'ativo' deve ser um número positivo ou zero")
        @Max(value = 1, message = "O campo 'ativo' deve conter apenas 1 caractere")
        Integer ativo

) {
}