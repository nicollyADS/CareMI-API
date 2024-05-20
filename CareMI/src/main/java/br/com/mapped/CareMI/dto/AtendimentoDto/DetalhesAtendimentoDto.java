package br.com.mapped.CareMI.dto.AtendimentoDto;
import br.com.mapped.CareMI.model.Atendimento;

import java.time.LocalDate;

public record DetalhesAtendimentoDto(Long idAtendimento, String descricao, Integer dias, String habito, String tempoSono, String hereditario, LocalDate dataEnvio, Integer ativo, Long idMedico, Long idPaciente) {

    public DetalhesAtendimentoDto(Atendimento atendimento) {
        this(atendimento.getIdAtendimento(), atendimento.getDescricao(), atendimento.getDias(), atendimento.getHabito(), atendimento.getTempoSono(), atendimento.getHereditario(), atendimento.getDataEnvio(), atendimento.getAtivo(),
            atendimento.getMedico().getIdMedico(), atendimento.getPaciente().getIdPaciente()

        );
    }
}
