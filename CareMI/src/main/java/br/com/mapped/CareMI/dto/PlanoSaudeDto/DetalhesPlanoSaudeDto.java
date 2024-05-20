package br.com.mapped.CareMI.dto.PlanoSaudeDto;
import br.com.mapped.CareMI.model.PlanoSaude;

import java.time.LocalDate;

import java.time.LocalDate;

public record DetalhesPlanoSaudeDto(Long idPlanoSaude, String razaoSocial, String fantasia, Long cnpj, String contato, String telefone, LocalDate dataCadastro, Integer ativo) {

    public DetalhesPlanoSaudeDto(PlanoSaude planoSaude) {
        this(planoSaude.getIdPlanoSaude(), planoSaude.getRazaoSocial(), planoSaude.getFantasia(), planoSaude.getCnpj(), planoSaude.getContato(), planoSaude.getTelefone(), planoSaude.getDataCadastro(), planoSaude.getAtivo());
    }
}