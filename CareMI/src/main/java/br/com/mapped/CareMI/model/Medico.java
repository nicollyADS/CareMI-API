package br.com.mapped.CareMI.model;

import br.com.mapped.CareMI.dto.MedicoDto.AtualizacaoMedicoDto;
import br.com.mapped.CareMI.dto.MedicoDto.CadastroMedicoDto;
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
@Table(name="t_medico")
@EntityListeners(AuditingEntityListener.class)
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medico")
    @SequenceGenerator(name = "medico", sequenceName = "seq_mi_medico", allocationSize = 1)
    @Column(name="cdMedico", length = 9)
    private Long idMedico;

    @Column(name="nmMedico", length = 100, nullable = false)
    private String nome;

    @Column(name="dsEspecializacao", length = 200, nullable = false)
    private String especializacao;

    @Column(name="dsCrm", length = 15, nullable = false)
    private String crm;

    @Column(name="dsEmail", length = 100, nullable = false)
    private String email;

    @Column(name="nrCelular", length = 15, nullable = false)
    private String celular;

    //relacionamentos

    //medico atendimeno - um pra muitos
    @OneToMany(mappedBy = "medico")
    private List<Atendimento> atendimentos;

    public Medico(CadastroMedicoDto medicoDto) {
        nome = medicoDto.nome();
        especializacao = medicoDto.especializacao();
        crm = medicoDto.crm();
        email = medicoDto.email();
        celular = medicoDto.celular();
    }

    public void atualizarInformacoesMedico(AtualizacaoMedicoDto dto) {
        if (dto.nome() != null)
            nome = dto.nome();
        if (dto.especializacao() != null)
            especializacao = dto.especializacao();
        if (dto.crm() != null)
            crm = dto.crm();
        if (dto.email() != null)
            email = dto.email();
        if (dto.celular() != null)
            celular = dto.celular();
    }

}
