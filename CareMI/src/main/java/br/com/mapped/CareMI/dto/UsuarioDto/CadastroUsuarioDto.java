package br.com.mapped.CareMI.dto.UsuarioDto;

import br.com.mapped.CareMI.model.EstadoCivil;

import java.time.LocalDate;

public record CadastroUsuarioDto(String nome, LocalDate dataNascimento, String cpf, String rg, String nacionalidade, LocalDate dataCadastro, EstadoCivil estadoCivil, String profissao, Integer ativo) {
}
