package br.com.mapped.CareMI.dto.CidadeDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Dados para atualização da cidade")
public record AtualizacaoCidadeDto(

        @Schema(description = "Nome da cidade", maxLength = 100, example = "São Paulo")
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome

) {
}