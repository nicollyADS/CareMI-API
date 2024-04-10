package br.com.mapped.CareMI.dto.UsuarioDto;

import br.com.mapped.CareMI.model.EstadoCivil;
import br.com.mapped.CareMI.model.Usuario;

import java.time.LocalDate;

public record DetalhesUsuarioDto(Long id, String nome, LocalDate dataNascimento, int cpf, int rg, String nacionalidade, LocalDate dataCadastro, EstadoCivil estadoCivil, String profissao, int ativo) {

    public DetalhesUsuarioDto(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getDataNascimento(), usuario.getCpf(), usuario.getRg(), usuario.getNacionalidade(), usuario.getDataCadastro(), usuario.getEstadoCivil(), usuario.getProfissao(), usuario.getAtivo());
    }
}