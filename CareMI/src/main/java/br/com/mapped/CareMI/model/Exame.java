package br.com.mapped.CareMI.model;

import br.com.mapped.CareMI.dto.ExameDto.AtualizacaoExameDto;
import br.com.mapped.CareMI.dto.ExameDto.CadastroExameDto;
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
@Table(name="t_exame")
@EntityListeners(AuditingEntityListener.class)
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exame")
    @SequenceGenerator(name = "exame", sequenceName = "seq_mi_exame", allocationSize = 1)
    @Column(name="cdExame", length = 9)
    private Long id;

    @Column(name="dtExame", nullable = false)
    private LocalDate data;

    @Column(name="dsExame", length = 500, nullable = false)
    private String descricao;

    //relacionamentos
    //exame atendimento - muitos pra um

    //exame resultadoExame - UM pra um

    public Exame(CadastroExameDto exameDto) {
        data = exameDto.data();
        descricao = exameDto.descricao();
    }

    public void atualizarInformacoesExame(AtualizacaoExameDto dto) {
        if (dto.data() != null)
            data = dto.data();
        if (dto.descricao() != null)
            descricao = dto.descricao();
    }
}
