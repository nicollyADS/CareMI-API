package br.com.mapped.CareMI.dto.PacienteDto;
import br.com.mapped.CareMI.model.SexoBiologico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CadastroPacienteDto(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotNull(message = "O peso não pode ser nulo")
        @Positive(message = "O peso deve ser um número positivo")
        Integer peso,

        @NotNull(message = "A altura não pode ser nula")
        @Positive(message = "A altura deve ser um número positivo")
        Integer altura,

        @NotBlank(message = "O grupo sanguíneo não pode estar em branco")
        @Size(max = 6, message = "O grupo sanguíneo deve ter no máximo 6 caracteres")
        String grupoSanguineo,

        @NotBlank(message = "O sexo biológico não pode estar em branco")
        @Size(max = 15, message = "O sexo biológico deve ter no máximo 15 caracteres")
        SexoBiologico sexoBiologico
) {
}
