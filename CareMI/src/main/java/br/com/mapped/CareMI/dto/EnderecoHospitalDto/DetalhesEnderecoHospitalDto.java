package br.com.mapped.CareMI.dto.EnderecoHospitalDto;

import br.com.mapped.CareMI.model.EnderecoHospital;

public record DetalhesEnderecoHospitalDto(Long id, Integer logradouro, String pontoReferencia, String complemento) {

    public DetalhesEnderecoHospitalDto(EnderecoHospital enderecoHospital) {
        this(enderecoHospital.getId(), enderecoHospital.getLogradouro(), enderecoHospital.getPontoReferencia(), enderecoHospital.getComplemento());
    }
}
