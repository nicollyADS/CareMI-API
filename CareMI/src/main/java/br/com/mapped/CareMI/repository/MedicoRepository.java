package br.com.mapped.CareMI.repository;

import br.com.mapped.CareMI.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository  extends JpaRepository<Medico, Long> {
}
