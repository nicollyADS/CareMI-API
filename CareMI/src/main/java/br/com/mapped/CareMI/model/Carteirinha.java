package br.com.mapped.CareMI.model;
import br.com.mapped.CareMI.dto.CarteirinhaDto.AtualizacaoCarteirinhaDto;
import br.com.mapped.CareMI.dto.CarteirinhaDto.CadastroCarteirinhaDto;
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
@Table(name="t_carteirinha")
@EntityListeners(AuditingEntityListener.class)
public class Carteirinha {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carteirinha")
    @SequenceGenerator(name = "carteirinha", sequenceName = "seq_mi_carteirinha", allocationSize = 1)
    @Column(name="cdCarteirinha", length = 9)
    private Long id;

    @Column(name="nmPaciente", length = 100, nullable = false)
    private String nome;

    @Column(name="nmPlanoSaude", length = 100, nullable = false)
    private String planoSaude;

    @Column(name="nrCns", length = 15, nullable = false)
    private Long cns;

    @Column(name="nmEmpresa", length = 100, nullable = false)
    private String empresa;

    @Column(name="dsCarencia", length = 200, nullable = false)
    private String carencia;

    @Column(name="dsAcomodacao", length = 200, nullable = false)
    private String acomodacao;

    @Column(name="dtNascimento", nullable = false)
    private LocalDate dataNascimento;

    public Carteirinha(CadastroCarteirinhaDto carteirinhaDto) {
        nome = carteirinhaDto.nome();
        planoSaude = carteirinhaDto.planoSaude();
        cns = carteirinhaDto.cns();
        empresa = carteirinhaDto.empresa();
        carencia = carteirinhaDto.carencia();
        acomodacao = carteirinhaDto.acomodacao() ;
        dataNascimento = carteirinhaDto.dataNascimento();
    }

    public void atualizarInformacoesCarteirinha(AtualizacaoCarteirinhaDto dto) {
        if (dto.nome() != null)
            nome = dto.nome();
        if (dto.planoSaude() != null)
            planoSaude = dto.planoSaude();
        if (dto.cns() != null)
            cns = dto.cns();
        if (dto.empresa() != null)
            empresa = dto.empresa();
        if (dto.carencia() != null)
            carencia = dto.carencia();
        if (dto.acomodacao() != null)
            acomodacao = dto.acomodacao();
        if (dto.dataNascimento() != null)
            dataNascimento = dto.dataNascimento();
    }
}
