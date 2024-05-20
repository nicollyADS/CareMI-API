package br.com.mapped.CareMI.repository;

import br.com.mapped.CareMI.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository  extends JpaRepository<Paciente, Long> {

    //BUSCAR PACIENTE POR NOME
    @Query("from Paciente p where p.nome like %:nome%")
    Page<Paciente> findByNome(String nome, Pageable pageable);

}
