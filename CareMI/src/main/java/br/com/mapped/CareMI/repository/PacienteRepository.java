package br.com.mapped.CareMI.repository;

import br.com.mapped.CareMI.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository  extends JpaRepository<Paciente, Long> {
}
