package br.com.mapped.CareMI.dto.EstadoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroEstadoDto(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 30, message = "O nome deve ter no máximo 30 caracteres")
        String nome,

        @NotBlank(message = "A sigla não pode estar em branco")
        @Size(max = 3, message = "A sigla deve ter no máximo 3 caracteres")
        String sigla
) {
}
