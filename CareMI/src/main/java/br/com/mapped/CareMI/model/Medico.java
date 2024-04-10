package br.com.mapped.CareMI.model;

import br.com.mapped.CareMI.dto.MedicoDto.AtualizacaoMedicoDto;
import br.com.mapped.CareMI.dto.MedicoDto.CadastroMedicoDto;
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
