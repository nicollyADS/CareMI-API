package br.com.mapped.CareMI.repository;

import br.com.mapped.CareMI.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BairroRepository  extends JpaRepository<Bairro, Long> {
}
