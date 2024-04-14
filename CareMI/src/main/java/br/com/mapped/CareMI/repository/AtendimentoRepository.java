package br.com.mapped.CareMI.repository;

import br.com.mapped.CareMI.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendimentoRepository  extends JpaRepository<Atendimento, Long> {
}
