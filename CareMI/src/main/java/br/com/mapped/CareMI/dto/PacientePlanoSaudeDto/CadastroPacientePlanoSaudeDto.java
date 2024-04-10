package br.com.mapped.CareMI.dto.PacientePlanoSaudeDto;

import java.time.LocalDate;

public record CadastroPacientePlanoSaudeDto(int carteira, LocalDate dataInicio, LocalDate dataFim) {
}
