package br.com.mapped.CareMI.dto.EstadoDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados para cadastro de um estado")
public record CadastroEstadoDto(

        @Schema(description = "Nome do estado", maxLength = 30, example = "São Paulo")
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 30, message = "O nome deve ter no máximo 30 caracteres")
        String nome,

        @Schema(description = "Sigla do estado", maxLength = 3, example = "SP")
        @NotBlank(message = "A sigla não pode estar em branco")
        @Size(max = 3, message = "A sigla deve ter no máximo 3 caracteres")
        String sigla

) {
}