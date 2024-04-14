package br.com.mapped.CareMI.dto.BairroDto;

import br.com.mapped.CareMI.model.Bairro;

public record DetalhesBairroDto(Long id, String nome, String zona, String cep) {

    public DetalhesBairroDto(Bairro bairro) {
        this(bairro.getId(), bairro.getNome(), bairro.getZona(), bairro.getCep());
    }
}