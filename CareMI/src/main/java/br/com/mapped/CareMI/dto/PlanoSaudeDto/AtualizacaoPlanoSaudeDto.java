package br.com.mapped.CareMI.dto.PlanoSaudeDto;

import java.time.LocalDate;

public record AtualizacaoPlanoSaudeDto(String razaoSocial, String fantasia, int cnpj, String contato, String telefone, LocalDate dataCadastro, int ativo) {
}
