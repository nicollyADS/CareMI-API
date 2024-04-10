package br.com.mapped.CareMI.model;

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
    @Column(name="cdLogin")
    private int id;
    private String cpf;
    private String senha;
    private int ativo;

    //fk
    private Long idUsuario;
    private Long idContato;
    private Long idTipoContato;
}
