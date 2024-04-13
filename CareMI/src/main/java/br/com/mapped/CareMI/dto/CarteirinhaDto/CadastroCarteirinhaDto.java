package br.com.mapped.CareMI.dto.CarteirinhaDto;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CadastroCarteirinhaDto(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "O plano de saúde não pode estar em branco")
        @Size(max = 100, message = "O plano de saúde deve ter no máximo 100 caracteres")
        String planoSaude,

        @NotNull(message = "O CNS não pode estar em branco")
        @Positive(message = "O número do CNS deve ser um número positivo")
        Integer cns,

        @NotBlank(message = "O nome da empresa não pode estar em branco")
        @Size(max = 100, message = "O nome da empresa deve ter no máximo 100 caracteres")
        String empresa,

        @NotBlank(message = "A carência não pode estar em branco")
        @Size(max = 200, message = "A carência deve ter no máximo 200 caracteres")
        String carencia,

        @NotBlank(message = "A acomodação não pode estar em branco")
        @Size(max = 200, message = "A acomodação deve ter no máximo 200 caracteres")
        String acomodacao,

        @NotNull(message = "A data de nascimento não pode estar em branco")
        @Past(message = "A data de nascimento deve estar no passado")
        LocalDate dataNascimento) {
}
