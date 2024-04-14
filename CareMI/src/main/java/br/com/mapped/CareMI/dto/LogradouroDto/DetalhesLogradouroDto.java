package br.com.mapped.CareMI.dto.LogradouroDto;

import br.com.mapped.CareMI.model.Logradouro;

public record DetalhesLogradouroDto(Long id, String nome) {

    public DetalhesLogradouroDto(Logradouro logradouro) {
        this(logradouro.getId(), logradouro.getNome());
    }
}
