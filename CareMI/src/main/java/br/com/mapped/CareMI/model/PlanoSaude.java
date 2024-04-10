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
@Table(name="t_plano_saude")
@EntityListeners(AuditingEntityListener.class)
public class PlanoSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planoSaude")
    @SequenceGenerator(name = "planoSaude", sequenceName = "seq_mi_pl_saude", allocationSize = 1)
    @Column(name="cdPlanoSaude")
    private Long id;
    private String razaoSocial;
    private String fantasia;
    private int cnpj;
    private String contato;
    private int telefone;
    private LocalDate dataCadastro;
    private int ativo;
}
