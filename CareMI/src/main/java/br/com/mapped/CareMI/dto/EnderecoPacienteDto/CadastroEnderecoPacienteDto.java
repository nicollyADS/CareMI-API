package br.com.mapped.CareMI.dto.EnderecoPacienteDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CadastroEnderecoPacienteDto(
        @NotNull(message = "O logradouro não pode estar em branco")
        @Positive(message = "O logradouro deve ser um número positivo")
        Integer numLogradouro,

        @NotBlank(message = "O ponto de referência não pode estar em branco")
        @Size(max = 100, message = "O ponto de referência deve ter no máximo 100 caracteres")
        String pontoReferencia,

        @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres")
        String complemento,

        @NotNull(message = "O id do logradouro não pode ser nulo, informe um id existente")
        Long idLogradouro,

        @NotNull(message = "O id do usuario não pode ser nulo, informe um id existente")
        Long idUsuario



) {
}
