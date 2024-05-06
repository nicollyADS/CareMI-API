package br.com.mapped.CareMI.model;
import br.com.mapped.CareMI.dto.UsuarioDto.AtualizacaoUsuarioDto;
import br.com.mapped.CareMI.dto.UsuarioDto.CadastroUsuarioDto;
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
@Table(name="t_usuario")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario")
    @SequenceGenerator(name = "usuario", sequenceName = "seq_mi_usuario", allocationSize = 1)
    @Column(name="cdUsuario", length = 9)
    private Long id;

    @Column(name="nmUsuario", length = 20, nullable = false)
    private String nome;

    @Column(name="dtNascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name="nrCpf", length = 15, nullable = false)
    private String cpf;

    @Column(name="nrRg", length = 15, nullable = false)
    private String rg;

    @Column(name="dsNacionalidade", length = 50, nullable = false)
    private String nacionalidade;

    @Column(name="dtCadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(name="dsEstadoCivil", length = 100)
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @Column(name="dsProfissao", length = 100)
    private String profissao;

    @Column(name="fgAtivo", length = 1, nullable = false)
    private Integer ativo;

    //relacionamentos
    //usuario enderecoPaciente - um pra UM

    //usuario login - UM pra um

    //usuario paciente - UM pra um

    public Usuario(CadastroUsuarioDto usuarioDto) {
        nome = usuarioDto.nome();
        dataNascimento = usuarioDto.dataNascimento();
        cpf = usuarioDto.cpf();
        rg = usuarioDto.rg();
        nacionalidade = usuarioDto.nacionalidade();
        dataCadastro = usuarioDto.dataCadastro();
        estadoCivil = usuarioDto.estadoCivil();
        profissao = usuarioDto.profissao();
        ativo = usuarioDto.ativo();
    }

    public void atualizarInformacoesUsuario(AtualizacaoUsuarioDto dto) {
        if (dto.nome() != null)
            nome = dto.nome();
        if (dto.dataNascimento() != null)
            dataNascimento = dto.dataNascimento();
        if (dto.cpf() != null)
            cpf = dto.cpf();
        if (dto.rg() != null)
            rg = dto.rg();
        if (dto.nacionalidade() != null)
            nacionalidade = dto.nacionalidade();
        if (dto.dataCadastro() != null)
            dataCadastro = dto.dataCadastro();
        if (dto.estadoCivil() != null)
            estadoCivil = dto.estadoCivil();
        if (dto.profissao() != null)
            profissao = dto.profissao();
        if (dto.ativo() != null)
            ativo = dto.ativo();
    }
}
