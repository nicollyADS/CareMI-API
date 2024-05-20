package br.com.mapped.CareMI.dto.UsuarioDto;
import br.com.mapped.CareMI.model.EstadoCivil;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CadastroUsuarioDto(
        //usuario
        @NotBlank(message = "O nome de usuario não pode estar em branco")
        @Size(max = 20, message = "O nome de usuario deve ter no máximo 20 caracteres")
        String nome,

        @NotNull(message = "A data de nascimento não pode ser nula")
        LocalDate dataNascimento,

        @NotBlank(message = "O CPF não pode estar em branco")
        @Size(min = 11, max = 15, message = "O CPF deve ter no máximo 15 caracteres")
        String cpf,

        @NotBlank(message = "O RG não pode estar em branco")
        @Size(max = 15, message = "O RG deve ter no máximo 15 caracteres")
        String rg,

        @NotBlank(message = "A nacionalidade não pode estar em branco")
        @Size(max = 50, message = "A nacionalidade deve ter no máximo 50 caracteres")
        String nacionalidade,

        @NotNull(message = "A data de cadastro não pode ser nula")
        LocalDate dataCadastro,

        EstadoCivil estadoCivil,

        @Size(max = 100, message = "A profissão deve ter no máximo 100 caracteres")
        String profissao,

        @NotNull(message = "O campo 'ativo' não pode estar em branco")
        @PositiveOrZero(message = "O campo 'ativo' deve ser um número positivo ou zero")
        @Max(value = 1, message = "o campo ativo deve conter apenas 1 caractere")
        Integer ativo,


        //login
        @NotBlank(message = "O CPF não pode estar em branco")
        @Size(min = 11, max = 15, message = "O CPF deve ter no máximo 15 caracteres")
        String numCpf,

        @NotBlank(message = "A senha não pode estar em branco")
        @Size(max = 100, message = "A senha deve ter no máximo 100 caracteres")
        String senha,

        @NotNull(message = "O campo 'ativo' não pode ser nulo")
        @PositiveOrZero(message = "O campo 'ativo' deve ser um número positivo ou zero")
        @Max(value = 1, message = "o campo ativo deve conter apenas 1 caractere")
        Integer flagAtivo,


        //enderecoPaciente
        @NotNull(message = "O logradouro não pode estar em branco")
        @Positive(message = "O logradouro deve ser um número positivo")
        Integer numLogradouro,

        @NotBlank(message = "O ponto de referência não pode estar em branco")
        @Size(max = 100, message = "O ponto de referência deve ter no máximo 100 caracteres")
        String pontoReferencia,

        @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres")
        String complemento,

        @NotNull(message = "O id do logradouro não pode ser nulo, informe um id existente")
        Long idLogradouro

) {
}
