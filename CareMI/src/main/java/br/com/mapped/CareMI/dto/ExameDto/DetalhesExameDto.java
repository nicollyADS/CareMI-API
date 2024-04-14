package br.com.mapped.CareMI.dto.ExameDto;

import br.com.mapped.CareMI.model.Exame;

import java.time.LocalDate;

public record DetalhesExameDto(Long id, LocalDate data, String descricao) {

    public DetalhesExameDto(Exame exame) {
        this(exame.getId(), exame.getData(), exame.getDescricao());
    }
}
