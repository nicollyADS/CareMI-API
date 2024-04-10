package br.com.mapped.CareMI.repository;

import br.com.mapped.CareMI.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository  extends JpaRepository<Estado, Long> {
}
