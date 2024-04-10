package br.com.mapped.CareMI.dto.EnderecoHospitalDto;

import br.com.mapped.CareMI.model.EnderecoHospital;

public record DetalhesEnderecoHospitalDto(Long id, int logradouro, String pontoReferencia, String complemento, Long idLogradouro) {

    public DetalhesEnderecoHospitalDto(EnderecoHospital enderecoHospital) {
        this(enderecoHospital.getId(), enderecoHospital.getLogradouro(), enderecoHospital.getPontoReferencia(), enderecoHospital.getComplemento(), enderecoHospital.getIdLogradouro());
    }
}
