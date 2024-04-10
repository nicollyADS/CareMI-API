package br.com.mapped.CareMI.dto.PlanoSaudeDto;

import br.com.mapped.CareMI.model.PlanoSaude;

import java.time.LocalDate;

import java.time.LocalDate;

public record DetalhesPlanoSaudeDto(Long id, String razaoSocial, String fantasia, int cnpj, String contato, String telefone, LocalDate dataCadastro, int ativo) {

    public DetalhesPlanoSaudeDto(PlanoSaude planoSaude) {
        this(planoSaude.getId(), planoSaude.getRazaoSocial(), planoSaude.getFantasia(), planoSaude.getCnpj(), planoSaude.getContato(), planoSaude.getTelefone(), planoSaude.getDataCadastro(), planoSaude.getAtivo());
    }
}