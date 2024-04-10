package br.com.mapped.CareMI.dto.AtendimentoDto;

import java.time.LocalDate;

public record AtualizacaoAtendimentoDto(String descricao, Integer dias, String habito, String tempoSono, String hereditario, LocalDate dataEnvio, Integer ativo) {
}
