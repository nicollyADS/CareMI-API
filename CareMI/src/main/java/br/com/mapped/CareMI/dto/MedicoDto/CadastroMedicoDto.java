package br.com.mapped.CareMI.dto.MedicoDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroMedicoDto(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "A especialização não pode estar em branco")
        @Size(max = 200, message = "A especialização deve ter no máximo 200 caracteres")
        String especializacao,

        @NotBlank(message = "O CRM não pode estar em branco")
        @Size(max = 15, message = "O CRM deve ter no máximo 15 caracteres")
        String crm,

        @NotBlank(message = "O email não pode estar em branco")
        @Email(message = "O email deve ser válido")
        @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
        String email,

        @NotBlank(message = "O celular não pode estar em branco")
        @Size(max = 15, message = "O celular deve ter no máximo 15 caracteres")
        String celular
) {
}
