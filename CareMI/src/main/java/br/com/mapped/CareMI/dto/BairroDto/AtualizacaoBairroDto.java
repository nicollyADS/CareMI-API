package br.com.mapped.CareMI.dto.BairroDto;
import jakarta.validation.constraints.*;

public record AtualizacaoBairroDto(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "A zona não pode estar em branco")
        @Size(max = 100, message = "A zona deve ter no máximo 100 caracteres")
        String zona,

        @NotBlank(message = "O CEP não pode estar em branco")
        @Size(min = 8, max = 9, message = "O CEP deve ter de 8 a 9 caracteres")
        String cep){
}
