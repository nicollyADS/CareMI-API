package br.com.mapped.CareMI.repository;

import br.com.mapped.CareMI.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
