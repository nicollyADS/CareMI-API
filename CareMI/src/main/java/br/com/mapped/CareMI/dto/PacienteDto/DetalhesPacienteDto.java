package br.com.mapped.CareMI.dto.PacienteDto;
import br.com.mapped.CareMI.model.Paciente;
import br.com.mapped.CareMI.model.SexoBiologico;

import java.time.LocalDate;

public record DetalhesPacienteDto(Long id, String nome, Integer peso, Integer altura, String grupoSanguineo, SexoBiologico sexoBiologico,
                                  String nomePaciente, String planoSaude, Long cns, String empresa, String carencia, String acomodacao, LocalDate dataNascimento) {

    public DetalhesPacienteDto(Paciente paciente) {
        this(
                //paciente
                paciente.getId(), paciente.getNome(), paciente.getPeso(), paciente.getAltura(), paciente.getGrupoSanguineo(), paciente.getSexoBiologico(),
                //carteirinha
                paciente.getCarteirinha().getNome(), paciente.getCarteirinha().getPlanoSaude(), paciente.getCarteirinha().getCns(), paciente.getCarteirinha().getEmpresa(),
                paciente.getCarteirinha().getCarencia(), paciente.getCarteirinha().getAcomodacao(), paciente.getCarteirinha().getDataNascimento()
        );
    }
}
