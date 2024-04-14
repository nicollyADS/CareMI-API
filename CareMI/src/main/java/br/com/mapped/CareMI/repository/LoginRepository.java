package br.com.mapped.CareMI.repository;

import br.com.mapped.CareMI.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository  extends JpaRepository<Login, Long> {
}
