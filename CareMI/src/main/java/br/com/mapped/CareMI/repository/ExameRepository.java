package br.com.mapped.CareMI.repository;

import br.com.mapped.CareMI.model.Exame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ExameRepository  extends JpaRepository<Exame, Long> {

    //BUSCAR EXAME POR DATA
    @Query("SELECT e FROM Exame e WHERE e.data = :data")
    Page<Exame> findByData(@Param("data") LocalDate data, Pageable pageable);
}
