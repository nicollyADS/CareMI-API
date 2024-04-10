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
@Table(name="t_logradouro")
@EntityListeners(AuditingEntityListener.class)
public class Logradouro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logradouro")
    @SequenceGenerator(name = "logradouro", sequenceName = "seq_mi_logradouro", allocationSize = 1)
    @Column(name="cdLogradouro")
    private int id;
    private String nome;
    private Long idBairro;
    private Long idCidade;
}
