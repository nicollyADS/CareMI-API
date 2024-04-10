package br.com.mapped.CareMI.dto.PlanoSaudeDto;

import java.time.LocalDate;

public record CadastroPlanoSaudeDto(String razaoSocial, String fantasia, int cnpj, String contato, String telefone, LocalDate dataCadastro, int ativo) {
}
