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
@Table(name="t_atendimento")
@EntityListeners(AuditingEntityListener.class)
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atendimento")
    @SequenceGenerator(name = "atendimento", sequenceName = "seq_mi_atendimento", allocationSize = 1)
    @Column(name="cdAtendimento")
    private Long id;
    private String descricao;
    private int dias;
    private String habito;
    private String tempoSono;
    private String hereditario;
    private LocalDate dataEnvio;
    private int ativo;

    //fk
    private Long idPaciente;
    private Long idMedico;
}
