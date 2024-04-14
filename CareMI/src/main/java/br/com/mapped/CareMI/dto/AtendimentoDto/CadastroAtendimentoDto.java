package br.com.mapped.CareMI.dto.AtendimentoDto;
import jakarta.validation.constraints.*;
import lombok.Value;

import java.time.LocalDate;

public record CadastroAtendimentoDto(
        @NotBlank(message = "A descrição não pode estar em branco")
        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String descricao,

        @NotNull(message = "Os dias não podem ser nulos")
        @Positive(message = "O número de dias deve ser positivo")
        Integer dias,

        @NotBlank(message = "O hábito não pode estar em branco")
        @Size(max = 100, message = "O hábito deve ter no máximo 100 caracteres")
        String habito,

        @NotBlank(message = "O tempo de sono não pode estar em branco")
        @Size(max = 10, message = "O tempo de sono deve ter no máximo 10 caracteres")
        String tempoSono,

        @NotBlank(message = "O histórico hereditário não pode estar em branco")
        @Size(max = 50, message = "O histórico hereditário deve ter no máximo 50 caracteres")
        String hereditario,

        @NotNull(message = "A data de envio não pode ser nula")
        @PastOrPresent(message = "A data de envio precisa ser atual ou estar no passado")
        LocalDate dataEnvio,

        @NotNull(message = "O campo 'ativo' não pode ser nulo")
        @PositiveOrZero(message = "O campo 'ativo' deve ser um número positivo ou zero")
        @Max(value = 1, message = "o campo ativo deve conter apenas 1 caractere")
        Integer ativo) {
}
