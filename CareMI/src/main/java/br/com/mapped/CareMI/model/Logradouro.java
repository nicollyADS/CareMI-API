package br.com.mapped.CareMI.model;

import br.com.mapped.CareMI.dto.LogradouroDto.AtualizacaoLogradouroDto;
import br.com.mapped.CareMI.dto.LogradouroDto.CadastroLogradouroDto;
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
@Table(name="t_logradouro")
@EntityListeners(AuditingEntityListener.class)
public class Logradouro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logradouro")
    @SequenceGenerator(name = "logradouro", sequenceName = "seq_mi_logradouro", allocationSize = 1)
    @Column(name="cdLogradouro", length = 9)
    private Long idLogradouro;

    @Column(name="nmRua", length = 100, nullable = false)
    private String nome;

    //relacionamentos
    //logradouro bairro - muitos pra um
    @ManyToOne
    @JoinColumn(name="cdBairro", nullable = false)
    private Bairro bairro;

    //logradouro enderecoPaciente - um pra muitos
    @OneToMany(mappedBy = "logradouro")
    private List<EnderecoPaciente> enderecoPacientes;

    //logradouro enderecoHospital - um pra muitos
    @OneToMany(mappedBy = "logradouro")
    private List<EnderecoHospital> enderecoHospitals;

    public Logradouro(CadastroLogradouroDto logradouroDto, Bairro bairro) {
        nome = logradouroDto.nome();
        this.bairro = bairro;
    }

    public void atualizarInformacoesLogradouro(AtualizacaoLogradouroDto dto) {
        if (dto.nome() != null)
            nome = dto.nome();
    }
}
