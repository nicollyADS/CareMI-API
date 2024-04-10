package br.com.mapped.CareMI.dto.CarteirinhaDto;
import br.com.mapped.CareMI.model.Carteirinha;

import java.time.LocalDate;

public record DetalhesCarteirinhaDto(Long id, String nome, String planoSaude, Integer cns, String empresa, String carencia, String acomodacao, LocalDate dataNascimento) {

    public DetalhesCarteirinhaDto(Carteirinha carteirinha) {
        this(carteirinha.getId(), carteirinha.getNome(), carteirinha.getPlanoSaude(), carteirinha.getCns(), carteirinha.getEmpresa(), carteirinha.getCarencia(), carteirinha.getAcomodacao(), carteirinha.getDataNascimento());
    }
}