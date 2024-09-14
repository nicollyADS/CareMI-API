package br.com.mapped.CareMI.dto.UsuarioDto;
import br.com.mapped.CareMI.model.EstadoCivil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "Dados para atualizar um usuário")
public record AtualizacaoUsuarioDto(

        @Schema(description = "Nome do usuário", maxLength = 20, example = "João da Silva")
        @NotBlank(message = "O nome de usuário não pode estar em branco")
        @Size(max = 20, message = "O nome de usuário deve ter no máximo 20 caracteres")
        String nome,

        @Schema(description = "Data de nascimento do usuário", example = "1990-01-01")
        @NotNull(message = "A data de nascimento não pode ser nula")
        LocalDate dataNascimento,

        @Schema(description = "CPF do usuário", maxLength = 15, example = "12345678900")
        @NotBlank(message = "O CPF não pode estar em branco")
        @Size(min = 11, max = 15, message = "O CPF deve ter no máximo 15 caracteres")
        String cpf,

        @Schema(description = "RG do usuário", maxLength = 15, example = "123456789")
        @NotBlank(message = "O RG não pode estar em branco")
        @Size(max = 15, message = "O RG deve ter no máximo 15 caracteres")
        String rg,

        @Schema(description = "Nacionalidade do usuário", maxLength = 50, example = "Brasileiro")
        @NotBlank(message = "A nacionalidade não pode estar em branco")
        @Size(max = 50, message = "A nacionalidade deve ter no máximo 50 caracteres")
        String nacionalidade,

        @Schema(description = "Data de cadastro do usuário", example = "2024-01-01")
        @NotNull(message = "A data de cadastro não pode ser nula")
        LocalDate dataCadastro,

        @Schema(description = "Estado civil do usuário", example = "CASADO")
        EstadoCivil estadoCivil,

        @Schema(description = "Profissão do usuário", maxLength = 100, example = "Desenvolvedor")
        @Size(max = 100, message = "A profissão deve ter no máximo 100 caracteres")
        String profissao,

        @Schema(description = "Senha do usuário", maxLength = 100, example = "senha123")
        @NotBlank(message = "A senha não pode estar em branco")
        @Size(max = 100, message = "A senha deve ter no máximo 100 caracteres")
        String senha,

        @Schema(description = "Campo que indica se o usuário está ativo (1 para ativo, 0 para inativo)", example = "1")
        @NotNull(message = "O campo 'ativo' não pode estar em branco")
        @PositiveOrZero(message = "O campo 'ativo' deve ser um número positivo ou zero")
        @Max(value = 1, message = "O campo 'ativo' deve conter apenas 1 caractere")
        Integer ativo

) {
}
