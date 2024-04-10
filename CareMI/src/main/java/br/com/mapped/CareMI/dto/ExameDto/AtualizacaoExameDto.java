package br.com.mapped.CareMI.dto.ExameDto;

import java.time.LocalDate;

public record AtualizacaoExameDto(LocalDate data, String descricao) {
}
