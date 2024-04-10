package br.com.mapped.CareMI.dto.CarteirinhaDto;

import java.time.LocalDate;

public record CadastroCarteirinhaDto(String nome, String planoSaude, int cns, String empresa, String carencia, String acomodacao, LocalDate dataNascimento) {
}
