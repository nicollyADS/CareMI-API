package br.com.mapped.CareMI.dto.CarteirinhaDto;
import java.time.LocalDate;

public record AtualizacaoCarteirinhaDto(String nome, String planoSaude, Integer cns, String empresa, String carencia, String acomodacao, LocalDate dataNascimento) {
}
