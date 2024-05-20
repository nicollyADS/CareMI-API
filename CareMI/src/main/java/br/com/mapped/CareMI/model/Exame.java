package br.com.mapped.CareMI.model;

import br.com.mapped.CareMI.dto.ExameDto.AtualizacaoExameDto;
import br.com.mapped.CareMI.dto.ExameDto.CadastroExameDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="t_exame")
@EntityListeners(AuditingEntityListener.class)
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exame")
    @SequenceGenerator(name = "exame", sequenceName = "seq_mi_exame", allocationSize = 1)
    @Column(name="cdExame", length = 9)
    private Long idExame;

    @Column(name="dtExame", nullable = false)
    private LocalDate data;

    @Column(name="dsExame", length = 500, nullable = false)
    private String descricao;

    //relacionamentos
    //exame atendimento - muitos pra um
    @ManyToOne
    @JoinColumn(name="cdAtendimento", nullable = false)
    private Atendimento atendimento;

    //exame resultadoExame - UM pra um
    @OneToOne(mappedBy = "exame", cascade = CascadeType.ALL)
    private ResultadoExame resultadoExame;

    public Exame(CadastroExameDto exameDto, Atendimento atendimento) {
        data = exameDto.data();
        descricao = exameDto.descricao();

        //resultadoExame
        resultadoExame = new ResultadoExame(exameDto);
        resultadoExame.setExame(this);

        //atendimento
        this.atendimento = atendimento;
    }

    public void atualizarInformacoesExame(AtualizacaoExameDto dto) {
        if (dto.data() != null)
            data = dto.data();
        if (dto.descricao() != null)
            descricao = dto.descricao();

        //resultadoExame
        if (dto.descricaoExame() != null)
            this.resultadoExame.setDescricaoExame(dto.descricaoExame());
        if (dto.observacao() != null)
            this.resultadoExame.setObservacao(dto.observacao());
        if (dto.resultado() != null)
            this.resultadoExame.setResultado(dto.resultado());
        if (dto.globulosVermelhos() != null)
            this.resultadoExame.setGlobulosVermelhos(dto.globulosVermelhos());
        if (dto.globulosBrancos() != null)
            this.resultadoExame.setGlobulosBrancos(dto.globulosBrancos());
        if (dto.plaquetas() != null)
            this.resultadoExame.setPlaquetas(dto.plaquetas());
        if (dto.homoglobinaGlicada() != null)
            this.resultadoExame.setHomoglobinaGlicada(dto.homoglobinaGlicada());
        if (dto.creatina() != null)
            this.resultadoExame.setCreatina(dto.creatina());
        if (dto.colesterolTotal() != null)
            this.resultadoExame.setColesterolTotal(dto.colesterolTotal());
        if (dto.colesterolHDL() != null)
            this.resultadoExame.setColesterolHDL(dto.colesterolHDL());
        if (dto.colesterolLDL() != null)
            this.resultadoExame.setColesterolLDL(dto.colesterolLDL());
        if (dto.teglicerides() != null)
            this.resultadoExame.setTeglicerides(dto.teglicerides());
        if (dto.hormonioTrioestimulanteTSH() != null)
            this.resultadoExame.setHormonioTrioestimulanteTSH(dto.hormonioTrioestimulanteTSH());

    }
}
