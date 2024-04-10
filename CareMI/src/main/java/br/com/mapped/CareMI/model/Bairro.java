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
@Table(name="t_bairro")
@EntityListeners(AuditingEntityListener.class)
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bairro")
    @SequenceGenerator(name = "bairro", sequenceName = "seq_mi_bairro", allocationSize = 1)
    @Column(name="cdBairro")
    private Long id;
    private String nome;
    private String zona;
    private String cep;

    //fk
    private Long idCidade;

}
