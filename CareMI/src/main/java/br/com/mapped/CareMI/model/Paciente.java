package br.com.mapped.CareMI.model;

import br.com.mapped.CareMI.dto.MedicoDto.AtualizacaoMedicoDto;
import br.com.mapped.CareMI.dto.MedicoDto.CadastroMedicoDto;
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
    @Column(name="cdPaciente")
    private Long id;
    private String nome;
    private Integer peso;
    private Integer altura;
    private String grupoSanguineo;
    private SexoBiologico sexoBiologico;

    //fk
    private Long idUsuario;
    private Long idContato;
    private Long idTipoContato;
    private Long idCarteirinha;

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
