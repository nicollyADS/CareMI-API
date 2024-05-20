package br.com.mapped.CareMI.model;

import br.com.mapped.CareMI.dto.BairroDto.AtualizacaoBairroDto;
import br.com.mapped.CareMI.dto.BairroDto.CadastroBairroDto;
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
@Table(name="t_bairro")
@EntityListeners(AuditingEntityListener.class)
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bairro")
    @SequenceGenerator(name = "bairro", sequenceName = "seq_mi_bairro", allocationSize = 1)
    @Column(name="cdBairro", length = 7)
    private Long idBairro;

    @Column(name="nmBairro", length = 100, nullable = false)
    private String nome;

    @Column(name="nmZonaBairro", length = 100, nullable = false)
    private String zona;

    @Column(name="nrCep", length = 10, nullable = false)
    private String cep;


    //relacionamentos
    //bairro logradouro - um pra muitos
    @OneToMany(mappedBy = "bairro")
    private List<Logradouro> logradouros;

    //bairro cidade - muitos pra um
    @ManyToOne
    @JoinColumn(name="cdCidade", nullable = false)
    private Cidade cidade;

    public Bairro(CadastroBairroDto bairroDto, Cidade cidade) {
        nome = bairroDto.nome();
        zona = bairroDto.zona();
        cep= bairroDto.cep();
        this.cidade = cidade;
    }

    public void atualizarInformacoesBairro(AtualizacaoBairroDto dto) {
        if (dto.nome() != null)
            nome = dto.nome();
        if (dto.zona() != null)
            zona = dto.zona();
        if (dto.cep() != null)
            cep = dto.cep();

    }

}
