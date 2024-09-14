package br.com.mapped.CareMI.dto.UsuarioDto;
import br.com.mapped.CareMI.model.EstadoCivil;
import br.com.mapped.CareMI.model.Usuario;
import java.time.LocalDate;

public record DetalhesUsuarioDto(Long id, String nome) {

    public DetalhesUsuarioDto(Usuario usuario) {
        this(
                usuario.getIdUsuario(), usuario.getNome()
        );
    }
}