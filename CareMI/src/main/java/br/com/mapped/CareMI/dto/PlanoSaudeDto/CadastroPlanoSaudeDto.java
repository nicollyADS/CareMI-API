package br.com.mapped.CareMI.dto.PlanoSaudeDto;
import java.time.LocalDate;

public record CadastroPlanoSaudeDto(String razaoSocial, String fantasia, Integer cnpj, String contato, String telefone, LocalDate dataCadastro, Integer ativo) {
}
