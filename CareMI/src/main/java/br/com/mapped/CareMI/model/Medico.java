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
@Table(name="t_medico")
@EntityListeners(AuditingEntityListener.class)
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medico")
    @SequenceGenerator(name = "medico", sequenceName = "seq_mi_medico", allocationSize = 1)
    @Column(name="cdMedico")
    private Long id;
    private String nome;
    private String especializacao;
    private String crm;
    private String email;
    private String celular;

}
