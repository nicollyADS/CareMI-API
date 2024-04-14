package br.com.mapped.CareMI.dto.PacientePlanoSaudeDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CadastroPacientePlanoSaudeDto(
        @NotNull(message = "O número da carteira não pode ser nulo")
        @Positive(message = "O número da carteira deve ser um número positivo")
        Long carteira,

        @NotNull(message = "A data de início não pode ser nula")
        LocalDate dataInicio,

        @NotNull(message = "A data final não pode ser nula")
        LocalDate dataFim
) {
}
