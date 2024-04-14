package br.com.mapped.CareMI.dto.CidadeDto;
import jakarta.validation.constraints.*;

public record AtualizacaoCidadeDto(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome) {
}
