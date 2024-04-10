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
@Table(name="t_paciente")
@EntityListeners(AuditingEntityListener.class)
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente")
    @SequenceGenerator(name = "paciente", sequenceName = "seq_mi_paciente", allocationSize = 1)
    @Column(name="cdPaciente")
    private Long id;
    private String nome;
    private int peso;
    private int altura;
    private String grupoSanguineo;
    private SexoBiologico sexoBiologico;

    //fk
    private Long idUsuario;
    private Long idContato;
    private Long idTipoContato;
    private Long idCarteirinha;

}
