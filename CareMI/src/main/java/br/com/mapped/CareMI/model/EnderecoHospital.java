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
@Table(name="t_endereco_hospital")
@EntityListeners(AuditingEntityListener.class)
public class EnderecoHospital {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atendimento")
    @SequenceGenerator(name = "atendimento", sequenceName = "seq_mi_atendimento", allocationSize = 1)
    @Column(name="cdEndereco")
    private Long id;
    private int logradouro;
    private String pontoReferencia;
    private String complemento;
    private Long idLogradouro;

    //fk
    private Long idBairro;
    private Long idCidade;
}
