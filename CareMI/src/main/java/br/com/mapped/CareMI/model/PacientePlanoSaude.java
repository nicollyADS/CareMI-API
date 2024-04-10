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
@Table(name="t_paciente_plano_saude")
@EntityListeners(AuditingEntityListener.class)
public class PacientePlanoSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pacientePlanoSaude")
    @SequenceGenerator(name = "pacientePlanoSaude", sequenceName = "seq_mi_pac_pl_saude", allocationSize = 1)
    @Column(name="cdPlanoPaciente")
    private Long id;
    private int carteira;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    //fk
    private Long idPaciente;
    private Long idPlanoSaude;

}
