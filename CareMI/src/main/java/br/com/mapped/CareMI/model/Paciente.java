package br.com.mapped.CareMI.model;

import br.com.mapped.CareMI.dto.PacienteDto.AtualizacaoPacienteDto;
import br.com.mapped.CareMI.dto.PacienteDto.CadastroPacienteDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

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
    private Long idPaciente;

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
    @OneToOne
    @JoinColumn(name = "cdUsuario", nullable = false)
    private Usuario usuario;

    //paciente atendimento - um pra muitos
    @OneToMany(mappedBy = "paciente")
    private List<Atendimento> atendimentos;

    //paciente carteirinha - UM pra um
    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    private Carteirinha carteirinha;

    //paciente pacientePlanoSaude - um pra muitos
    @OneToMany(mappedBy = "paciente", cascade =  CascadeType.ALL)
    private List<PacientePlanoSaude> pacientePlanoSaudes;


    public Paciente(CadastroPacienteDto pacienteDto) {
        nome = pacienteDto.nome();
        peso = pacienteDto.peso();
        altura = pacienteDto.altura();
        grupoSanguineo = pacienteDto.grupoSanguineo();
        sexoBiologico = pacienteDto.sexoBiologico();

        //carteirnha
        carteirinha = new Carteirinha(pacienteDto);
        carteirinha.setPaciente(this);
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

        //carteirinha
        if (dto.nomePaciente() != null)
            this.carteirinha.setNomePaciente(dto.nomePaciente());
        if (dto.planoSaude() != null)
            this.carteirinha.setPlanoSaude(dto.planoSaude());
        if (dto.cns() != null)
            this.carteirinha.setCns(dto.cns());
        if (dto.empresa() != null)
            this.carteirinha.setEmpresa(dto.empresa());
        if (dto.carencia() != null)
            this.carteirinha.setCarencia(dto.carencia());
        if (dto.acomodacao() != null)
            this.carteirinha.setAcomodacao(dto.acomodacao());
        if (dto.dataNascimento() != null)
            this.carteirinha.setDataNascimento(dto.dataNascimento());
    }

}
