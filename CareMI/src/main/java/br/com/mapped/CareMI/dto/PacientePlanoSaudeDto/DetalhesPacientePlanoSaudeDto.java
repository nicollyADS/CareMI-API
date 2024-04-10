package br.com.mapped.CareMI.dto.PacientePlanoSaudeDto;

import br.com.mapped.CareMI.model.PacientePlanoSaude;

import java.time.LocalDate;

public record DetalhesPacientePlanoSaudeDto(Long id, int carteira, LocalDate dataInicio, LocalDate dataFim) {

    public DetalhesPacientePlanoSaudeDto(PacientePlanoSaude pacientePlanoSaude) {
        this(pacientePlanoSaude.getId(), pacientePlanoSaude.getCarteira(), pacientePlanoSaude.getDataInicio(), pacientePlanoSaude.getDataFim());
    }
}