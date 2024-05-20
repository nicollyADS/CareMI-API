package br.com.mapped.CareMI.dto.PacienteDto;
import br.com.mapped.CareMI.model.Paciente;
import br.com.mapped.CareMI.model.SexoBiologico;

import java.time.LocalDate;

public record DetalhesPacienteDto(Long idPaciente, String nome, Integer peso, Integer altura, String grupoSanguineo, SexoBiologico sexoBiologico, Long idUsuario,
                                  String nomePaciente, String planoSaude, Long cns, String empresa, String carencia, String acomodacao, LocalDate dataNascimento) {

    public DetalhesPacienteDto(Paciente paciente) {
        this(
                //paciente
                paciente.getIdPaciente(), paciente.getNome(), paciente.getPeso(), paciente.getAltura(), paciente.getGrupoSanguineo(), paciente.getSexoBiologico(), paciente.getUsuario().getIdUsuario(),
                //carteirinha
                paciente.getCarteirinha().getNomePaciente(), paciente.getCarteirinha().getPlanoSaude(), paciente.getCarteirinha().getCns(), paciente.getCarteirinha().getEmpresa(),
                paciente.getCarteirinha().getCarencia(), paciente.getCarteirinha().getAcomodacao(), paciente.getCarteirinha().getDataNascimento()
        );
    }
}
