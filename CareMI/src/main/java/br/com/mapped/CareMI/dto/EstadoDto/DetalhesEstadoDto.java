package br.com.mapped.CareMI.dto.EstadoDto;

import br.com.mapped.CareMI.model.Estado;

public record DetalhesEstadoDto(Long idEstado, String nome, String sigla) {

    public DetalhesEstadoDto(Estado estado) {
        this(estado.getIdEstado(), estado.getNome(), estado.getSigla());
    }
}