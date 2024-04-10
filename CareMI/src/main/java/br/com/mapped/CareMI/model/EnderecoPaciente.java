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
@Table(name="t_endereco_paciente")
@EntityListeners(AuditingEntityListener.class)
public class EnderecoPaciente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enderecoPaciente")
    @SequenceGenerator(name = "enderecoPaciente", sequenceName = "seq_mi_endereco_paciente", allocationSize = 1)
    @Column(name="cdEndereco")
    private Long id;
    private int logradouro;
    private String pontoReferencia;
    private String complemento;

    //fk
    private Long idLogradouro;
    private Long idBairro;
    private Long idCidade;
}
