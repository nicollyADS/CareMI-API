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
@Table(name="t_exame")
@EntityListeners(AuditingEntityListener.class)
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exame")
    @SequenceGenerator(name = "exame", sequenceName = "seq_mi_exame", allocationSize = 1)
    @Column(name="cdExame")
    private Long id;
    private LocalDate data;
    private String descricao;

    //fk
    private Long idAtendimento;
    private Long idPaciente;
}
