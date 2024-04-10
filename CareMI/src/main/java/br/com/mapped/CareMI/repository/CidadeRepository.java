package br.com.mapped.CareMI.repository;

import br.com.mapped.CareMI.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository  extends JpaRepository<Cidade, Long> {
}
