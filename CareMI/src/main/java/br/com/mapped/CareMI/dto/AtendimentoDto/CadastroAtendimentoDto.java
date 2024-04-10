package br.com.mapped.CareMI.dto.AtendimentoDto;

import java.time.LocalDate;

public record CadastroAtendimentoDto(String descricao, int dias, String habito, String tempoSono, String hereditario, LocalDate dataEnvio, int ativo) {
}
