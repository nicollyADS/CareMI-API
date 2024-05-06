package br.com.mapped.CareMI.model;

import br.com.mapped.CareMI.dto.PacienteDto.AtualizacaoPacienteDto;
import br.com.mapped.CareMI.dto.PacienteDto.CadastroPacienteDto;
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
    @Column(name="cdPaciente", length = 9)
    private Long id;

    @Column(name="nmPaciente", length = 100, nullable = false)
    private String nome;

    @Column(name="nrPeso", length = 6, nullable = false)
    private Integer peso;

    @Column(name="nrAltura", length = 4, nullable = false)
    private Integer altura;

    @Column(name="nmGrupoSanguineo", length = 6, nullable = false)
    private String grupoSanguineo;

    @Column(name="flSexoBiologico", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private SexoBiologico sexoBiologico;

    //relacionamentos
    //paciente usuario - um pra UM

    //paciente atendimento - um pra muitos

    //paciente carteirinha - UM pra um

    //paciente pacientePlanoSaude - um pra muitos


    public Paciente(CadastroPacienteDto pacienteDto) {
        nome = pacienteDto.nome();
        peso = pacienteDto.peso();
        altura = pacienteDto.altura();
        grupoSanguineo = pacienteDto.grupoSanguineo();
        sexoBiologico = pacienteDto.sexoBiologico();
    }

    public void atualizarInformacoesPaciente(AtualizacaoPacienteDto dto) {
        if (dto.nome() != null)
            nome = dto.nome();
        if (dto.peso() != null)
            peso = dto.peso();
        if (dto.altura() != null)
            altura = dto.altura();
        if (dto.grupoSanguineo() != null)
            grupoSanguineo = dto.grupoSanguineo();
        if (dto.sexoBiologico() != null)
            sexoBiologico = dto.sexoBiologico();
    }

}
