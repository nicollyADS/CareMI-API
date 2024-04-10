package br.com.mapped.CareMI.dto.ExameDto;

import java.time.LocalDate;

public record CadastroExameDto(LocalDate data, String descricao) {
}
