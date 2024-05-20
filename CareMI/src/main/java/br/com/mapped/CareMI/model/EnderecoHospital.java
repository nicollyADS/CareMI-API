package br.com.mapped.CareMI.model;

import br.com.mapped.CareMI.dto.EnderecoHospitalDto.AtualizacaoEnderecoHospitalDto;
import br.com.mapped.CareMI.dto.EnderecoHospitalDto.CadastroEnderecoHospitalDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="t_endereco_hospital")
@EntityListeners(AuditingEntityListener.class)
public class EnderecoHospital {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enderecoHospital")
    @SequenceGenerator(name = "enderecoHospital", sequenceName = "seq_mi_endereco_hospital", allocationSize = 1)
    @Column(name="cdEndereco", length = 9)
    private Long idEnderecoHospital;

    @Column(name="nrLogradouro", length = 7, nullable = false)
    private Integer numLogradouro;

    @Column(name="dsPontoReferencia", length = 100, nullable = false)
    private String pontoReferencia;

    @Column(name="dsComplemento", length = 100, nullable = false)
    private String complemento;

    //relacionamentos
    //enderecoHospital logradouro - muitos pra um
    @ManyToOne
    @JoinColumn(name="cdLogradouro", nullable = false)
    private Logradouro logradouro;



    public EnderecoHospital(CadastroEnderecoHospitalDto enderecoHospitalDto, Logradouro logradouro) {
        numLogradouro = enderecoHospitalDto.numLogradouro();
        pontoReferencia = enderecoHospitalDto.pontoReferencia();
        complemento = enderecoHospitalDto.complemento();
        this.logradouro = logradouro;
    }

    public void atualizarInformacoesEnderecoHospital(AtualizacaoEnderecoHospitalDto dto) {
        if (dto.numLogradouro() != null)
            numLogradouro = dto.numLogradouro();
        if (dto.pontoReferencia() != null)
            pontoReferencia = dto.pontoReferencia();
        if (dto.complemento() != null)
            complemento = dto.complemento();
    }
}
