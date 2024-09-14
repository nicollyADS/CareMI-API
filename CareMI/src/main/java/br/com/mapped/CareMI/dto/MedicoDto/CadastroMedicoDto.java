package br.com.mapped.CareMI.dto.MedicoDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados para cadastro de um médico")
public record CadastroMedicoDto(

        @Schema(description = "Nome completo do médico", maxLength = 100, example = "Dr. João Silva")
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @Schema(description = "Especialização do médico", maxLength = 200, example = "Cardiologista")
        @NotBlank(message = "A especialização não pode estar em branco")
        @Size(max = 200, message = "A especialização deve ter no máximo 200 caracteres")
        String especializacao,

        @Schema(description = "CRM do médico", maxLength = 15, example = "123456-SP")
        @NotBlank(message = "O CRM não pode estar em branco")
        @Size(max = 15, message = "O CRM deve ter no máximo 15 caracteres")
        String crm,

        @Schema(description = "Email do médico", maxLength = 100, example = "joao.silva@exemplo.com")
        @NotBlank(message = "O email não pode estar em branco")
        @Email(message = "O email deve ser válido")
        @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
        String email,

        @Schema(description = "Número de celular do médico", maxLength = 15, example = "+55 11 91234-5678")
        @NotBlank(message = "O celular não pode estar em branco")
        @Size(max = 15, message = "O celular deve ter no máximo 15 caracteres")
        String celular

) {
}
