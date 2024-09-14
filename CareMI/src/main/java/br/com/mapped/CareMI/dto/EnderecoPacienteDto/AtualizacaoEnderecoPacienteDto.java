package br.com.mapped.CareMI.dto.EnderecoPacienteDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados para atualização do endereço do paciente")
public record AtualizacaoEnderecoPacienteDto(

        @Schema(description = "Número do logradouro", example = "123")
        @NotNull(message = "O logradouro não pode estar em branco")
        @Positive(message = "O logradouro deve ser um número positivo")
        Integer numLogradouro,

        @Schema(description = "Ponto de referência do endereço", maxLength = 100, example = "Próximo ao parque central")
        @NotBlank(message = "O ponto de referência não pode estar em branco")
        @Size(max = 100, message = "O ponto de referência deve ter no máximo 100 caracteres")
        String pontoReferencia,

        @Schema(description = "Complemento do endereço", maxLength = 100, example = "Apto 45")
        @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres")
        String complemento,

        @Schema(description = "ID do logradouro", example = "1")
        @NotNull(message = "O id do logradouro não pode ser nulo, informe um id existente")
        Long idLogradouro,

        @Schema(description = "ID do usuário", example = "2")
        @NotNull(message = "O id do usuario não pode ser nulo, informe um id existente")
        Long idUsuario

) {
}
