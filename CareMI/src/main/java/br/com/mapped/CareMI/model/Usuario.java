package br.com.mapped.CareMI.model;

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
    @Column(name="cdUsuario")
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private int cpf;
    private int rg;
    private String nacionalidade;
    private LocalDate dataCadastro;
    private EstadoCivil estadoCivil;
    private String profissao;
    private int ativo;
    private Long idEndereco;
    private Long idLogradouro;
    private Long idBairro;
    private Long idCidade;

}
