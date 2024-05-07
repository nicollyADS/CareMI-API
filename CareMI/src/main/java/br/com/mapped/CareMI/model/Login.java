package br.com.mapped.CareMI.model;

import br.com.mapped.CareMI.dto.UsuarioDto.AtualizacaoUsuarioDto;
import br.com.mapped.CareMI.dto.UsuarioDto.CadastroUsuarioDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="t_login")
@EntityListeners(AuditingEntityListener.class)
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login")
    @SequenceGenerator(name = "login", sequenceName = "seq_mi_login", allocationSize = 1)
    @Column(name="cdLogin", length = 9)
    private Long id;

    @Column(name="nrCpf", length = 15, nullable = false)
    private String numCpf;

    @Column(name="dsSenha", length = 100, nullable = false)
    private String senha;

    @Column(name="fgAtivo", length = 1, nullable = false)
    private Integer flagAtivo;

    //relacionamentos
    //login usuario - um pra UM
    @OneToOne
    @JoinColumn(name = "cdUsuario", nullable = false)
    private Usuario usuario;

    public Login(CadastroUsuarioDto loginDto) {
        numCpf = loginDto.cpf();
        senha = loginDto.senha();
        flagAtivo = loginDto.flagAtivo();
    }

}

