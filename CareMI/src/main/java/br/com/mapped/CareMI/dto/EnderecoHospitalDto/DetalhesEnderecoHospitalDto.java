package br.com.mapped.CareMI.dto.EnderecoHospitalDto;

import br.com.mapped.CareMI.model.EnderecoHospital;

public record DetalhesEnderecoHospitalDto(Long id, Integer numLogradouro, String pontoReferencia, String complemento) {

    public DetalhesEnderecoHospitalDto(EnderecoHospital enderecoHospital) {
        this(enderecoHospital.getId(), enderecoHospital.getNumLogradouro(), enderecoHospital.getPontoReferencia(), enderecoHospital.getComplemento());
    }
}
