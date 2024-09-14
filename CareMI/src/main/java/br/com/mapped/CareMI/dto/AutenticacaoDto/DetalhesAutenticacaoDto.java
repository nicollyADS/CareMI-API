package br.com.mapped.CareMI.dto.AutenticacaoDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados para autenticação do usuário")
public record DetalhesAutenticacaoDto(

        @Schema(description = "CPF do usuário", example = "123.456.789-00", minLength = 11, maxLength = 15)
        @NotBlank(message = "O CPF não pode estar em branco")
        @Size(min = 11, max = 15, message = "O CPF deve ter no máximo 15 caracteres")
        String cpf,

        @Schema(description = "Senha do usuário", example = "senha123", maxLength = 50)
        @NotBlank(message = "A senha não pode estar em branco")
        @Size(max = 50, message = "A senha deve ter no máximo 50 caracteres")
        String senha
) {
}