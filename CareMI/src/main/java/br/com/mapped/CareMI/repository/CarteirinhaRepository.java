package br.com.mapped.CareMI.repository;

import br.com.mapped.CareMI.model.Carteirinha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteirinhaRepository  extends JpaRepository<Carteirinha, Long> {
}
