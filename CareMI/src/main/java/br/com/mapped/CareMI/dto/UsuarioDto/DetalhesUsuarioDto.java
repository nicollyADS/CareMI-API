package br.com.mapped.CareMI.dto.UsuarioDto;
import br.com.mapped.CareMI.model.EstadoCivil;
import br.com.mapped.CareMI.model.Usuario;
import java.time.LocalDate;

public record DetalhesUsuarioDto(Long id, String nome, LocalDate dataNascimento, String cpf, String rg, String nacionalidade, LocalDate dataCadastro, EstadoCivil estadoCivil, String profissao, Integer ativo,
                                 String numCpf, String senha, Integer flagAtivo,
                                 Integer numLogradouro, String pontoReferencia, String complemento) {

    public DetalhesUsuarioDto(Usuario usuario) {
        this(
                //usuario
                usuario.getId(), usuario.getNome(), usuario.getDataNascimento(), usuario.getCpf(), usuario.getRg(), usuario.getNacionalidade(), usuario.getDataCadastro(), usuario.getEstadoCivil(), usuario.getProfissao(), usuario.getAtivo(),
                //login
                usuario.getLogin().getNumCpf(), usuario.getLogin().getSenha(), usuario.getLogin().getFlagAtivo(),
                //enderecoPaciente
                usuario.getEnderecoPaciente().getNumLogradouro(), usuario.getEnderecoPaciente().getPontoReferencia(), usuario.getEnderecoPaciente().getComplemento()
        );
    }
}