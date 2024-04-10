package br.com.mapped.CareMI.repository;

import br.com.mapped.CareMI.model.Exame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExameRepository  extends JpaRepository<Exame, Long> {
}
