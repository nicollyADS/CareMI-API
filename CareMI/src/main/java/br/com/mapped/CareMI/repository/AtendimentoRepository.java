package br.com.mapped.CareMI.repository;

import br.com.mapped.CareMI.model.Atendimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AtendimentoRepository  extends JpaRepository<Atendimento, Long> {

    //BUSCAR TODOS OS ATENDIMENTOS DE UM MÉDICO PELO ID
    @Query("from Atendimento p where p.medico.id = :id")
    Page<Atendimento> findByMedico(Long id, Pageable pageable);

    //BUSCAR TODOS OS ATENDIMENTOS DE UM PACIENTE PELO ID
    @Query("from Atendimento p where p.paciente.id = :id")
    Page<Atendimento> findByNome(Long id, Pageable pageable);

}
