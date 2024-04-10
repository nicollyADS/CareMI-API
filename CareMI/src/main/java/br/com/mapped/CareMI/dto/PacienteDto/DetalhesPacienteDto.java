package br.com.mapped.CareMI.dto.PacienteDto;
import br.com.mapped.CareMI.model.Paciente;
import br.com.mapped.CareMI.model.SexoBiologico;

public record DetalhesPacienteDto(Long id, String nome, Integer peso, Integer altura, String grupoSanguineo, SexoBiologico sexoBiologico) {

    public DetalhesPacienteDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getPeso(), paciente.getAltura(), paciente.getGrupoSanguineo(), paciente.getSexoBiologico());
    }
}
