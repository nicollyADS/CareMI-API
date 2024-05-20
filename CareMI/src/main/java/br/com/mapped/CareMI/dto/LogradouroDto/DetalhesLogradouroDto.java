package br.com.mapped.CareMI.dto.LogradouroDto;

import br.com.mapped.CareMI.model.Logradouro;

public record DetalhesLogradouroDto(Long idLogradouro, String nome) {

    public DetalhesLogradouroDto(Logradouro logradouro) {
        this(logradouro.getIdLogradouro(), logradouro.getNome());
    }
}
