package br.com.mapped.CareMI.model;

import br.com.mapped.CareMI.dto.ExameDto.CadastroExameDto;
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
    @Column(name="cdResultado", length = 9)
    private Long idResultadoExame;

    @Column(name="dsResultado", length = 500, nullable = false)
    private String descricaoExame;

    @Column(name="dsObservacoes", length = 100, nullable = false)
    private String observacao;

    @Column(name="vrResultado", length = 3, nullable = false)
    private Integer resultado;

    @Column(name="nrGlobulosVermelhos", length = 5, nullable = false)
    private Integer globulosVermelhos;

    @Column(name="nrGlobulosBrancos", length = 5, nullable = false)
    private Integer globulosBrancos;

    @Column(name="nrPlaquetas", length = 5, nullable = false)
    private Integer plaquetas;

    @Column(name="nrHomoglobinaGlicada", length = 5, nullable = false)
    private Integer homoglobinaGlicada;

    @Column(name="nrCreatina", length = 5, nullable = false)
    private Integer creatina;

    @Column(name="nrColesterolTotal", length = 5, nullable = false)
    private Integer colesterolTotal;

    @Column(name="nrColesterolHDL", length = 5, nullable = false)
    private Integer colesterolHDL;

    @Column(name="nrColesterolLDL", length = 5, nullable = false)
    private Integer colesterolLDL;

    @Column(name="nrTeglicerides", length = 5, nullable = false)
    private Integer teglicerides;

    @Column(name="nrHormonioTrioEstimulanteTSH", length = 5, nullable = false)
    private Integer hormonioTrioestimulanteTSH;

    //relacionamentos
    //resultadoExame exame - um pra UM
    @OneToOne
    @JoinColumn(name = "cdExame", nullable = false)
    private Exame exame;

    public ResultadoExame(CadastroExameDto resultadoExameDto) {
        descricaoExame = resultadoExameDto.descricaoExame();
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


}
