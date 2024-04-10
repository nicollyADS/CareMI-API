package br.com.mapped.CareMI.dto.LoginDto;

import br.com.mapped.CareMI.model.Login;

public record DetalhesLoginDto(Long id, String cpf, String senha, int ativo) {

    public DetalhesLoginDto(Login login) {
        this(login.getId(), login.getCpf(), login.getSenha(), login.getAtivo());
    }
}
