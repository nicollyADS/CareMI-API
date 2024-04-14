package br.com.mapped.CareMI.dto.EnderecoPacienteDto;


import br.com.mapped.CareMI.model.EnderecoPaciente;

public record DetalhesEnderecoPacienteDto(Long id, Integer logradouro, String pontoReferencia, String complemento) {

    public DetalhesEnderecoPacienteDto(EnderecoPaciente enderecoPaciente) {
        this(enderecoPaciente.getId(), enderecoPaciente.getLogradouro(), enderecoPaciente.getPontoReferencia(), enderecoPaciente.getComplemento());
    }
}
