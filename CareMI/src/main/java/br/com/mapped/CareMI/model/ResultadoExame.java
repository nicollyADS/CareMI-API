package br.com.mapped.CareMI.model;

import br.com.mapped.CareMI.dto.ResultadoExameDto.AtualizacaoResultadoExameDto;
import br.com.mapped.CareMI.dto.ResultadoExameDto.CadastroResultadoExameDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="t_resultado_exame")
@EntityListeners(AuditingEntityListener.class)
public class ResultadoExame {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resultadoExame")
    @SequenceGenerator(name = "resultadoExame", sequenceName = "seq_mi_rs_exame", allocationSize = 1)
    @Column(name="cdResultado")
    private Long id;
    private String descricao;
    private String observacao;
    private Integer resultado;
    private Integer globulosVermelhos;
    private Integer globulosBrancos;
    private Integer plaquetas;
    private Integer homoglobinaGlicada;
    private Integer creatina;
    private Integer colesterolTotal;
    private Integer colesterolHDL;
    private Integer colesterolLDL;
    private Integer teglicerides;
    private Integer hormonioTrioestimulanteTSH;

    //fk
    private Long idExame;

    public ResultadoExame(CadastroResultadoExameDto resultadoExameDto) {
        descricao = resultadoExameDto.descricao();
        observacao = resultadoExameDto.observacao();
        resultado = resultadoExameDto.resultado();
        globulosVermelhos = resultadoExameDto.globulosVermelhos();
        globulosBrancos = resultadoExameDto.globulosBrancos();
        plaquetas = resultadoExameDto.plaquetas();
        homoglobinaGlicada = resultadoExameDto.homoglobinaGlicada();
        creatina = resultadoExameDto.creatina();
        colesterolTotal = resultadoExameDto.colesterolTotal();
        colesterolHDL = resultadoExameDto.colesterolHDL();
        colesterolLDL = resultadoExameDto.colesterolLDL();
        teglicerides = resultadoExameDto.teglicerides();
        hormonioTrioestimulanteTSH = resultadoExameDto.hormonioTrioestimulanteTSH();
    }

    public void atualizarInformacoesResultadoExame(AtualizacaoResultadoExameDto dto) {
        if (dto.descricao() != null)
            descricao = dto.descricao();
        if (dto.observacao() != null)
            observacao = dto.observacao();
        if (dto.resultado() != null)
            resultado = dto.resultado();
        if (dto.globulosVermelhos() != null)
            globulosVermelhos = dto.globulosVermelhos();
        if (dto.globulosBrancos() != null)
            globulosBrancos = dto.globulosBrancos();
        if (dto.plaquetas() != null)
            plaquetas = dto.plaquetas();
        if (dto.homoglobinaGlicada() != null)
            homoglobinaGlicada = dto.homoglobinaGlicada();
        if (dto.creatina() != null)
            creatina = dto.creatina();
        if (dto.colesterolTotal() != null)
            colesterolTotal = dto.colesterolTotal();
        if (dto.colesterolHDL() != null)
            colesterolHDL = dto.colesterolHDL();
        if (dto.colesterolLDL() != null)
            colesterolLDL = dto.colesterolLDL();
        if (dto.teglicerides() != null)
            teglicerides = dto.teglicerides();
        if (dto.hormonioTrioestimulanteTSH() != null)
            hormonioTrioestimulanteTSH = dto.hormonioTrioestimulanteTSH();
    }


}
