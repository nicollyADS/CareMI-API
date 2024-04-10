package br.com.mapped.CareMI.dto.PacienteDto;

import br.com.mapped.CareMI.model.SexoBiologico;

public record CadastroPacienteDto(String nome, int peso, int altura, String grupoSanguineo, SexoBiologico sexoBiologico) {
}
