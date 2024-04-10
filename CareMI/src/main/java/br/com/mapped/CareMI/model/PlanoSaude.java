package br.com.mapped.CareMI.model;

import br.com.mapped.CareMI.dto.PacientePlanoSaudeDto.AtualizacaoPacientePlanoSaudeDto;
import br.com.mapped.CareMI.dto.PacientePlanoSaudeDto.CadastroPacientePlanoSaudeDto;
import br.com.mapped.CareMI.dto.PlanoSaudeDto.AtualizacaoPlanoSaudeDto;
import br.com.mapped.CareMI.dto.PlanoSaudeDto.CadastroPlanoSaudeDto;
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
@Table(name="t_plano_saude")
@EntityListeners(AuditingEntityListener.class)
public class PlanoSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planoSaude")
    @SequenceGenerator(name = "planoSaude", sequenceName = "seq_mi_pl_saude", allocationSize = 1)
    @Column(name="cdPlanoSaude")
    private Long id;
    private String razaoSocial;
    private String fantasia;
    private Integer cnpj;
    private String contato;
    private String telefone;
    private LocalDate dataCadastro;
    private Integer ativo;

    public PlanoSaude(CadastroPlanoSaudeDto planoSaudeDto) {
        razaoSocial = planoSaudeDto.razaoSocial();
        fantasia = planoSaudeDto.fantasia();
        cnpj = planoSaudeDto.cnpj();
        contato = planoSaudeDto.contato();
        telefone = planoSaudeDto.telefone();
        dataCadastro = planoSaudeDto.dataCadastro();
        ativo = planoSaudeDto.ativo();
    }

    public void atualizarInformacoesPlanoSaude(AtualizacaoPlanoSaudeDto dto) {
        if (dto.razaoSocial() != null)
            razaoSocial = dto.razaoSocial();
        if (dto.fantasia() != null)
            fantasia = dto.fantasia();
        if (dto.cnpj() != null)
            cnpj = dto.cnpj();
        if (dto.contato() != null)
            contato = dto.contato();
        if (dto.telefone() != null)
            telefone = dto.telefone();
        if (dto.dataCadastro() != null)
            dataCadastro = dto.dataCadastro();
        if (dto.ativo() != null)
            ativo = dto.ativo();
    }
}
