package br.com.mapped.CareMI.model;

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
    private int resultado;
    private int globulosVermelhos;
    private int globulosBrancos;
    private int plaquetas;
    private int homoglobinaGlicada;
    private int creatina;
    private int colesterolTotal;
    private int colesterolHDL;
    private int colesterolLDL;
    private int teglicerides;
    private int hormonioTrioestimulanteTSH;

    //fk
    private Long idExame;

}
