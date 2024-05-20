package br.com.mapped.CareMI.model;

import br.com.mapped.CareMI.dto.EstadoDto.AtualizacaoEstadoDto;
import br.com.mapped.CareMI.dto.EstadoDto.CadastroEstadoDto;
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
@Table(name="t_estado")
@EntityListeners(AuditingEntityListener.class)
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado")
    @SequenceGenerator(name = "estado", sequenceName = "seq_mi_estado", allocationSize = 1)
    @Column(name="cdEstado", length = 6)
    private Long idEstado;

    @Column(name="nmEstado", length = 30, nullable = false)
    private String nome;

    @Column(name="sgEstado", length = 3, nullable = false)
    private String sigla;

    //relacionamentos

    //estado cidade - um pra muitos
    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades;


    public Estado(CadastroEstadoDto estadolDto) {
        nome = estadolDto.nome();
        sigla = estadolDto.sigla();
    }

    public void atualizarInformacoesEstado(AtualizacaoEstadoDto dto) {
        if (dto.nome() != null)
            nome = dto.nome();
        if (dto.sigla() != null)
            sigla = dto.sigla();
    }
}

