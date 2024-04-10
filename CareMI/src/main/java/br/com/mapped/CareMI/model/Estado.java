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
@Table(name="t_estado")
@EntityListeners(AuditingEntityListener.class)
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado")
    @SequenceGenerator(name = "estado", sequenceName = "seq_mi_estado", allocationSize = 1)
    @Column(name="cdEstado")
    private Long id;
    private String nome;
    private String sigla;
}
