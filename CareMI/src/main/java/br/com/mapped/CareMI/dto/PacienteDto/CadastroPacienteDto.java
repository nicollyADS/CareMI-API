package br.com.mapped.CareMI.dto.PacienteDto;
import br.com.mapped.CareMI.model.SexoBiologico;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CadastroPacienteDto(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotNull(message = "O peso não pode ser nulo")
        @Positive(message = "O peso deve ser um número positivo")
        Integer peso,

        @NotNull(message = "A altura não pode ser nula")
        @Positive(message = "A altura deve ser um número positivo")
        Integer altura,

        @NotBlank(message = "O grupo sanguíneo não pode estar em branco")
        @Size(max = 6, message = "O grupo sanguíneo deve ter no máximo 6 caracteres")
        String grupoSanguineo,

        @NotNull(message = "O sexo biológico não pode estar em branco")
        SexoBiologico sexoBiologico,

        @NotNull(message = "O id do usuario não pode estar em branco")
        Long idUsuario,

        //carteirinha
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nomePaciente,

        @NotBlank(message = "O plano de saúde não pode estar em branco")
        @Size(max = 100, message = "O plano de saúde deve ter no máximo 100 caracteres")
        String planoSaude,

        @NotNull(message = "O CNS não pode estar em branco")
        @Positive(message = "O número do CNS deve ser um número positivo")
        Long cns,

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
        LocalDate dataNascimento
) {
}
