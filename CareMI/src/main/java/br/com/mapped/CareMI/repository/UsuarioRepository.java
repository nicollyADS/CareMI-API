package br.com.mapped.CareMI.repository;

import br.com.mapped.CareMI.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //BUSCAR USUARIO POR CPF
    @Query("FROM Usuario u WHERE u.cpf = :cpf")
    Page<Usuario> findByCpf(@Param("cpf") String cpf, Pageable pageable);

    //BUSCAR USUARIO POR RG
    @Query("FROM Usuario u WHERE u.rg = :rg")
    Page<Usuario> findByRG(@Param("rg") String rg, Pageable pageable);

}
