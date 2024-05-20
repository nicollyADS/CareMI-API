package br.com.mapped.CareMI.dto.EnderecoHospitalDto;

import br.com.mapped.CareMI.model.EnderecoHospital;

public record DetalhesEnderecoHospitalDto(Long idEnderecoHospital, Integer numLogradouro, String pontoReferencia, String complemento) {

    public DetalhesEnderecoHospitalDto(EnderecoHospital enderecoHospital) {
        this(enderecoHospital.getIdEnderecoHospital(), enderecoHospital.getNumLogradouro(), enderecoHospital.getPontoReferencia(), enderecoHospital.getComplemento());
    }
}
