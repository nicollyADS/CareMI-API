package br.com.mapped.CareMI.dto.PacienteDto;
import br.com.mapped.CareMI.model.SexoBiologico;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "Dados para a atualização do paciente")
public record AtualizacaoPacienteDto(

        @Schema(description = "Nome completo do paciente", maxLength = 100, example = "Maria Oliveira")
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @Schema(description = "Peso do paciente em kg", example = "70")
        @NotNull(message = "O peso não pode ser nulo")
        @Positive(message = "O peso deve ser um número positivo")
        Integer peso,

        @Schema(description = "Altura do paciente em cm", example = "170")
        @NotNull(message = "A altura não pode ser nula")
        @Positive(message = "A altura deve ser um número positivo")
        Integer altura,

        @Schema(description = "Grupo sanguíneo do paciente", maxLength = 6, example = "O+")
        @NotBlank(message = "O grupo sanguíneo não pode estar em branco")
        @Size(max = 6, message = "O grupo sanguíneo deve ter no máximo 6 caracteres")
        String grupoSanguineo,

        @Schema(description = "Sexo biológico do paciente", example = "Feminino")
        @NotNull(message = "O sexo biológico não pode estar em branco")
        SexoBiologico sexoBiologico,

        @Schema(description = "ID do usuário associado ao paciente", example = "12345")
        @NotNull(message = "O id do usuário não pode estar em branco")
        Long idUsuario,

        //carteirinha
        @Schema(description = "Nome do paciente para a carteirinha", maxLength = 100, example = "Maria Oliveira")
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nomePaciente,

        @Schema(description = "Nome do plano de saúde", maxLength = 100, example = "Unimed")
        @NotBlank(message = "O plano de saúde não pode estar em branco")
        @Size(max = 100, message = "O plano de saúde deve ter no máximo 100 caracteres")
        String planoSaude,

        @Schema(description = "Número do CNS do paciente", example = "123456789012345")
        @NotNull(message = "O CNS não pode estar em branco")
        @Positive(message = "O número do CNS deve ser um número positivo")
        Long cns,

        @Schema(description = "Nome da empresa de saúde", maxLength = 100, example = "Saúde & Cia")
        @NotBlank(message = "O nome da empresa não pode estar em branco")
        @Size(max = 100, message = "O nome da empresa deve ter no máximo 100 caracteres")
        String empresa,

        @Schema(description = "Descrição da carência do plano de saúde", maxLength = 200, example = "30 dias de carência")
        @NotBlank(message = "A carência não pode estar em branco")
        @Size(max = 200, message = "A carência deve ter no máximo 200 caracteres")
        String carencia,

        @Schema(description = "Descrição da acomodação no plano de saúde", maxLength = 200, example = "Acomodação em enfermaria")
        @NotBlank(message = "A acomodação não pode estar em branco")
        @Size(max = 200, message = "A acomodação deve ter no máximo 200 caracteres")
        String acomodacao,

        @Schema(description = "Data de nascimento do paciente", example = "1980-05-15")
        @NotNull(message = "A data de nascimento não pode estar em branco")
        @Past(message = "A data de nascimento deve estar no passado")
        LocalDate dataNascimento

) {
}
