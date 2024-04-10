package br.com.mapped.CareMI.dto.PacientePlanoSaudeDto;

import java.time.LocalDate;

public record AtualizacaoPacientePlanoSaudeDto(int carteira, LocalDate dataInicio, LocalDate dataFim) {
}
