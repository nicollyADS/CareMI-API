package br.com.mapped.CareMI.dto.AtendimentoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "Dados para atualizar um atendimento")
public record AtualizacaoAtendimentoDto(

        @Schema(description = "Descrição do atendimento", example = "Atendimento inicial para avaliação de saúde", maxLength = 500)
        @NotBlank(message = "A descrição não pode estar em branco")
        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String descricao,

        @Schema(description = "Número de dias para o atendimento", example = "7", minimum = "1")
        @NotNull(message = "Os dias não podem ser nulos")
        @Positive(message = "O número de dias deve ser positivo")
        Integer dias,

        @Schema(description = "Hábito do paciente", example = "Sedentário", maxLength = 100)
        @NotBlank(message = "O hábito não pode estar em branco")
        @Size(max = 100, message = "O hábito deve ter no máximo 100 caracteres")
        String habito,

        @Schema(description = "Tempo de sono do paciente", example = "8h", maxLength = 10)
        @NotBlank(message = "O tempo de sono não pode estar em branco")
        @Size(max = 10, message = "O tempo de sono deve ter no máximo 10 caracteres")
        String tempoSono,

        @Schema(description = "Histórico hereditário do paciente", example = "Diabetes, hipertensão", maxLength = 50)
        @NotBlank(message = "O histórico hereditário não pode estar em branco")
        @Size(max = 50, message = "O histórico hereditário deve ter no máximo 50 caracteres")
        String hereditario,

        @Schema(description = "Data de envio do atendimento", example = "2024-09-15")
        @NotNull(message = "A data de envio não pode ser nula")
        @PastOrPresent(message = "A data de envio precisa ser atual ou estar no passado")
        LocalDate dataEnvio,

        @Schema(description = "Status do atendimento", example = "1", allowableValues = {"0", "1"})
        @NotNull(message = "O campo 'ativo' não pode ser nulo")
        @PositiveOrZero(message = "O campo 'ativo' deve ser um número positivo ou zero")
        @Max(value = 1, message = "O campo ativo deve conter apenas 1 caractere")
        Integer ativo,

        @Schema(description = "ID do médico responsável", example = "123")
        @NotNull(message = "O id do médico não pode ser nulo")
        Long idMedico,

        @Schema(description = "ID do paciente", example = "456")
        @NotNull(message = "O id do paciente não pode ser nulo")
        Long idPaciente
) {
}
