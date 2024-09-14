package br.com.mapped.CareMI.dto.EnderecoPacienteDto;

import br.com.mapped.CareMI.model.EnderecoPaciente;

public record DetalhesEnderecoPacienteDto(Long id, Integer numLogradouro, String pontoReferencia, String complemento, Long idLogradouro, Long idUsuario) {

    public DetalhesEnderecoPacienteDto(EnderecoPaciente enderecoPaciente){
        this(
                enderecoPaciente.getIdEnderecoPaciente(), enderecoPaciente.getNumLogradouro(), enderecoPaciente.getPontoReferencia(), enderecoPaciente.getComplemento(), enderecoPaciente.getLogradouro().getIdLogradouro(), enderecoPaciente.getUsuario().getIdUsuario()
        );
    }

}
