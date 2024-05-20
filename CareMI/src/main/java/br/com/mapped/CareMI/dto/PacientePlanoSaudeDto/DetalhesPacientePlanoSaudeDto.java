package br.com.mapped.CareMI.dto.PacientePlanoSaudeDto;
import br.com.mapped.CareMI.model.PacientePlanoSaude;

import java.time.LocalDate;

public record DetalhesPacientePlanoSaudeDto(Long idPacientePlanoSaude, Long carteira, LocalDate dataInicio, LocalDate dataFim, Long idPlanoSaude, Long idPaciente) {

    public DetalhesPacientePlanoSaudeDto(PacientePlanoSaude pacientePlanoSaude) {
        this(pacientePlanoSaude.getIdPacientePlanoSaude(), pacientePlanoSaude.getCarteira(), pacientePlanoSaude.getDataInicio(), pacientePlanoSaude.getDataFim(),
            pacientePlanoSaude.getPlanoSaude().getIdPlanoSaude(), pacientePlanoSaude.getPaciente().getIdPaciente()
        );
    }
}