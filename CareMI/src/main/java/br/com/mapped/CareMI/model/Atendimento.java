package br.com.mapped.CareMI.model;

import br.com.mapped.CareMI.dto.AtendimentoDto.AtualizacaoAtendimentoDto;
import br.com.mapped.CareMI.dto.AtendimentoDto.CadastroAtendimentoDto;
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
    private Integer dias;
    private String habito;
    private String tempoSono;
    private String hereditario;
    private LocalDate dataEnvio;
    private Integer ativo;

    //fk
    private Long idPaciente;
    private Long idMedico;

    public Atendimento(CadastroAtendimentoDto atendimentoDto) {
        descricao = atendimentoDto.descricao();
        dias = atendimentoDto.dias();
        habito = atendimentoDto.habito();
        tempoSono = atendimentoDto.tempoSono();
        hereditario = atendimentoDto.hereditario();
        dataEnvio = atendimentoDto.dataEnvio() ;
        ativo= atendimentoDto.ativo();
    }

    public void atualizarInformacoesAtendimento(AtualizacaoAtendimentoDto dto) {
        if (dto.descricao() != null)
            descricao = dto.descricao();
        if (dto.dias() != null)
            dias = dto.dias();
        if (dto.habito() != null)
            habito = dto.habito();
        if (dto.tempoSono() != null)
            tempoSono = dto.tempoSono();
        if (dto.hereditario() != null)
            hereditario = dto.hereditario();
        if (dto.dataEnvio() != null)
            dataEnvio = dto.dataEnvio();
        if (dto.ativo() != null)
            ativo = dto.ativo();
    }
}
