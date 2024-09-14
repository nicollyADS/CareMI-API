package br.com.mapped.CareMI.dto.PacientePlanoSaudeDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Schema(description = "Dados para a atualização do vínculo entre paciente e plano de saúde")
public record AtualizacaoPacientePlanoSaudeDto(

        @Schema(description = "Número da carteira do paciente", example = "12345678901")
        @NotNull(message = "O número da carteira não pode ser nulo")
        @Positive(message = "O número da carteira deve ser um número positivo")
        Long carteira,

        @Schema(description = "Data de início do vínculo", example = "2024-01-01")
        @NotNull(message = "A data de início não pode ser nula")
        LocalDate dataInicio,

        @Schema(description = "Data final do vínculo", example = "2024-12-31")
        @NotNull(message = "A data final não pode ser nula")
        LocalDate dataFim,

        @Schema(description = "ID do plano de saúde", example = "1")
        @NotNull(message = "O id do plano de saúde não pode ser nulo")
        Long idPlanoSaude,

        @Schema(description = "ID do paciente", example = "100")
        @NotNull(message = "O id do paciente não pode ser nulo")
        Long idPaciente

) {
}
