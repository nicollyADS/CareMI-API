package br.com.mapped.CareMI.model;

import br.com.mapped.CareMI.dto.CidadeDto.AtualizacaoCidadeDto;
import br.com.mapped.CareMI.dto.CidadeDto.CadastroCidadeDto;
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
@Table(name="t_cidade")
@EntityListeners(AuditingEntityListener.class)
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cidade")
    @SequenceGenerator(name = "cidade", sequenceName = "seq_mi_cidade", allocationSize = 1)
    @Column(name="cdCidade", length = 6)
    private Long idCidade;

    @Column(name="nmCidade", length = 100, nullable = false)
    private String nome;

    //relacionamentos

    //cidade bairro - um pra muitos
    @OneToMany(mappedBy = "cidade")
    private List<Bairro> bairros;

    //cidade estado - muitos pra um
    @ManyToOne
    @JoinColumn(name="cdEstado", nullable = false)
    private Estado estado;


    public Cidade(CadastroCidadeDto cidadeDto, Estado estado) {
        nome = cidadeDto.nome();
        this.estado = estado;
    }

    public void atualizarInformacoesCidade(AtualizacaoCidadeDto dto) {
        if (dto.nome() != null)
            nome = dto.nome();
    }
}
