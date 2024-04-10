package br.com.mapped.CareMI.dto.PacientePlanoSaudeDto;

import java.time.LocalDate;

public record CadastroPacientePlanoSaudeDto(Integer carteira, LocalDate dataInicio, LocalDate dataFim) {
}
