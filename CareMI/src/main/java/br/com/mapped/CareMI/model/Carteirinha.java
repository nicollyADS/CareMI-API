package br.com.mapped.CareMI.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="t_carteirinha")
@EntityListeners(AuditingEntityListener.class)
public class Carteirinha {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carteirinha")
    @SequenceGenerator(name = "carteirinha", sequenceName = "seq_mi_carteirinha", allocationSize = 1)
    @Column(name="cdCarteirinha")
    private Long id;
    private String nome;
    private String planoSaude;
    private int cns;
    private String empresa;
    private String carencia;
    private String acomodacao;
    private LocalDate dataNascimento;
}
