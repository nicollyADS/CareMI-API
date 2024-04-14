package br.com.mapped.CareMI.dto.EstadoDto;

import br.com.mapped.CareMI.model.Estado;

public record DetalhesEstadoDto(Long id, String nome, String sigla) {

    public DetalhesEstadoDto(Estado estado) {
        this(estado.getId(), estado.getNome(), estado.getSigla());
    }
}