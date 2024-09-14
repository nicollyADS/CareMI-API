package br.com.mapped.CareMI.repository;

import br.com.mapped.CareMI.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //USER DETAILS
    UserDetails findByCpf(String cpf);

    //BUSCAR USUARIO POR RG
    @Query("FROM Usuario u WHERE u.rg = :rg")
    Page<Usuario> findByRG(@Param("rg") String rg, Pageable pageable);

}
