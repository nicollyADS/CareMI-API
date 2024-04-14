package br.com.mapped.CareMI.dto.CidadeDto;

import br.com.mapped.CareMI.model.Cidade;

public record DetalhesCidadeDto(Long id, String nome) {

    public DetalhesCidadeDto(Cidade cidade) {
        this(cidade.getId(), cidade.getNome());
    }
}
