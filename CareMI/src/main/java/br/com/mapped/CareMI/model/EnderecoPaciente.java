package br.com.mapped.CareMI.model;

import br.com.mapped.CareMI.dto.EnderecoPacienteDto.AtualizacaoEnderecoPacienteDto;
import br.com.mapped.CareMI.dto.EnderecoPacienteDto.CadastroEnderecoPacienteDto;
import br.com.mapped.CareMI.dto.UsuarioDto.CadastroUsuarioDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="t_endereco_paciente")
@EntityListeners(AuditingEntityListener.class)
public class EnderecoPaciente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enderecoPaciente")
    @SequenceGenerator(name = "enderecoPaciente", sequenceName = "seq_mi_endereco_paciente", allocationSize = 1)
    @Column(name="cdEndereco", length = 9)
    private Long idEnderecoPaciente;

    @Column(name="nrLogradouro", length = 7, nullable = false)
    private Integer numLogradouro;

    @Column(name="dsPontoReferencia", length = 100, nullable = false)
    private String pontoReferencia;

    @Column(name="dsComplemento", length = 100, nullable = false)
    private String complemento;

    //relacionamentos
    //enderecoPaciente usuario - um pra UM
    @OneToOne
    @JoinColumn(name = "cdUsuario", nullable = false)
    private Usuario usuario;

    //enderecoPaciente logradouro - muitos pra um
    @ManyToOne
    @JoinColumn(name="cdLogradouro", nullable = false)
    private Logradouro logradouro;


    public EnderecoPaciente(CadastroEnderecoPacienteDto enderecoPacienteDto) {
        numLogradouro = enderecoPacienteDto.numLogradouro();
        pontoReferencia = enderecoPacienteDto.pontoReferencia();
        complemento = enderecoPacienteDto.complemento();
    }

    public void atualizarInformacoesEnderecoPaciente(AtualizacaoEnderecoPacienteDto dto){
        if (dto.numLogradouro() != null)
            numLogradouro = dto.numLogradouro();
        if (dto.pontoReferencia() != null)
            pontoReferencia = dto.pontoReferencia();
        if (dto.complemento() != null)
            complemento = dto.complemento();
    }

}

