package br.com.mapped.CareMI.dto.ExameDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AtualizacaoExameDto(
        @NotNull(message = "A data não pode estar em branco")
        @PastOrPresent(message = "A data deve ser atual ou estar no passado")
        LocalDate data,

        @NotBlank(message = "A descrição não pode estar em branco")
        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String descricao
) {
}
