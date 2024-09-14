package br.com.mapped.CareMI.model;
import br.com.mapped.CareMI.dto.UsuarioDto.AtualizacaoUsuarioDto;
import br.com.mapped.CareMI.dto.UsuarioDto.CadastroUsuarioDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="t_usuario")
@EntityListeners(AuditingEntityListener.class)
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario")
    @SequenceGenerator(name = "usuario", sequenceName = "seq_mi_usuario", allocationSize = 1)
    @Column(name="cdUsuario", length = 9)
    private Long idUsuario;

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

    @Column(name="dsSenha", length = 100, nullable = false)
    private String senha;

    @Column(name="fgAtivo", length = 1, nullable = false)
    private Integer ativo;

    //relacionamentos
    //usuario enderecoPaciente - UM pra um
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private EnderecoPaciente enderecoPaciente;

    //usuario paciente - UM pra um
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Paciente paciente;

    public Usuario(CadastroUsuarioDto usuarioDto) {
        //usuario
        nome = usuarioDto.nome();
        dataNascimento = usuarioDto.dataNascimento();
        cpf = usuarioDto.cpf();
        rg = usuarioDto.rg();
        nacionalidade = usuarioDto.nacionalidade();
        dataCadastro = usuarioDto.dataCadastro();
        estadoCivil = usuarioDto.estadoCivil();
        profissao = usuarioDto.profissao();
        senha = usuarioDto.senha();
        ativo = usuarioDto.ativo();

    }

    public void atualizarInformacoesUsuario(AtualizacaoUsuarioDto dto) {
        //usuario
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
        if(dto.senha() != null)
            senha = dto.senha();
        if (dto.ativo() != null)
            ativo = dto.ativo();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
