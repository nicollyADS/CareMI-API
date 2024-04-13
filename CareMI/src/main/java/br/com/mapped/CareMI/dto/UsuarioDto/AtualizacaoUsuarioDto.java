package br.com.mapped.CareMI.dto.UsuarioDto;
import br.com.mapped.CareMI.model.EstadoCivil;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record AtualizacaoUsuarioDto(
        @NotBlank(message = "O nome de usuario não pode estar em branco")
        @Size(max = 20, message = "O nome de usuario deve ter no máximo 20 caracteres")
        String nome,

        @NotNull(message = "A data de nascimento não pode ser nula")
        LocalDate dataNascimento,

        @NotBlank(message = "O CPF não pode estar em branco")
        @Size(max = 15, message = "O CPF deve ter no máximo 15 caracteres")
        String cpf,

        @NotBlank(message = "O RG não pode estar em branco")
        @Size(max = 15, message = "O RG deve ter no máximo 15 caracteres")
        String rg,

        @NotBlank(message = "A nacionalidade não pode estar em branco")
        @Size(max = 50, message = "A nacionalidade deve ter no máximo 50 caracteres")
        String nacionalidade,

        @NotNull(message = "A data de cadastro não pode ser nula")
        LocalDate dataCadastro,

        @NotNull(message = "O estado civil não pode ser nulo")
        EstadoCivil estadoCivil,

        @NotBlank(message = "A profissão não pode estar em branco")
        @Size(max = 100, message = "A profissão deve ter no máximo 100 caracteres")
        String profissao,

        @NotNull(message = "O campo 'ativo' não pode estar em branco")
        @PositiveOrZero(message = "O campo 'ativo' deve ser um número positivo ou zero")
        @Max(value = 1, message = "o campo ativo deve conter apenas 1 caractere")
        Integer ativo
) {
}
