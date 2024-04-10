package br.com.mapped.CareMI.dto.PacienteDto;
import br.com.mapped.CareMI.model.SexoBiologico;

public record AtualizacaoPacienteDto(String nome, Integer peso, Integer altura, String grupoSanguineo, SexoBiologico sexoBiologico) {
}
