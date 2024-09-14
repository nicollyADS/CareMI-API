package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.PacienteDto.AtualizacaoPacienteDto;
import br.com.mapped.CareMI.dto.PacienteDto.CadastroPacienteDto;
import br.com.mapped.CareMI.dto.PacienteDto.DetalhesPacienteDto;
import br.com.mapped.CareMI.dto.PacientePlanoSaudeDto.CadastroPacientePlanoSaudeDto;
import br.com.mapped.CareMI.dto.PacientePlanoSaudeDto.DetalhesPacientePlanoSaudeDto;
import br.com.mapped.CareMI.dto.PlanoSaudeDto.CadastroPlanoSaudeDto;
import br.com.mapped.CareMI.dto.UsuarioDto.CadastroUsuarioDto;
import br.com.mapped.CareMI.dto.UsuarioDto.DetalhesUsuarioDto;
import br.com.mapped.CareMI.model.Paciente;
import br.com.mapped.CareMI.model.PacientePlanoSaude;
import br.com.mapped.CareMI.model.PlanoSaude;
import br.com.mapped.CareMI.model.Usuario;
import br.com.mapped.CareMI.repository.PacientePlanoSaudeRepository;
import br.com.mapped.CareMI.repository.PacienteRepository;
import br.com.mapped.CareMI.repository.PlanoSaudeRepository;
import br.com.mapped.CareMI.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pacientes")
@Tag(name = "Pacientes", description = "Operações para gerenciar pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PacientePlanoSaudeRepository pacientePlanoSaudeRepository;

    @Autowired
    private PlanoSaudeRepository planoSaudeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //GET
    @GetMapping
    @Operation(summary = "Lista todos os pacientes", description = "Retorna uma lista paginada de todos os pacientes")
    public ResponseEntity<List<DetalhesPacienteDto>> get(Pageable pageable){
        var paciente = pacienteRepository.findAll(pageable)
                .stream().map(DetalhesPacienteDto::new).toList();
        return ResponseEntity.ok(paciente);
    }

    //GET BY ID
    @GetMapping("{id}")
    @Operation(summary = "Obtém detalhes de um paciente pelo ID", description = "Retorna os detalhes de um paciente específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalhes do paciente encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesPacienteDto.class))),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do paciente", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<DetalhesPacienteDto> get(@PathVariable("id") Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesPacienteDto(paciente));
    }

    //POST
    @PostMapping
    @Transactional
    @Operation(summary = "Cadastra um novo paciente", description = "Registra um novo paciente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Paciente cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesPacienteDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<DetalhesPacienteDto> cadastrar(@RequestBody @Valid CadastroPacienteDto dto, UriComponentsBuilder builder) {
        var paciente = new Paciente(dto);
        var usuario = usuarioRepository.getReferenceById(dto.idUsuario());
        paciente.setUsuario(usuario);
        paciente = pacienteRepository.save(paciente);
        var uri = builder.path("/pacientes/{id}").buildAndExpand(paciente.getIdPaciente()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPacienteDto(paciente));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    @Operation(summary = "Remove um paciente pelo ID", description = "Remove um paciente específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Paciente removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do paciente", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        pacienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    @Operation(summary = "Atualiza as informações de um paciente", description = "Atualiza as informações de um paciente específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesPacienteDto.class))),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do paciente", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<DetalhesPacienteDto> put(@PathVariable("id") Long id,
                                                   @RequestBody @Valid AtualizacaoPacienteDto dto){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.atualizarInformacoesPaciente(dto);
        return ResponseEntity.ok(new DetalhesPacienteDto(paciente));
    }

    //BUSCAR PACIENTE POR NOME
    @GetMapping("por-nome")
    @Operation(summary = "Busca pacientes pelo nome", description = "Retorna uma lista paginada de pacientes que correspondem ao nome fornecido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pacientes encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesPacienteDto.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetro de nome inválido")
    })
    @Parameters({
            @Parameter(name = "nome", description = "Nome do paciente a ser pesquisado", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "page", description = "Número da página", in = ParameterIn.QUERY),
            @Parameter(name = "size", description = "Número de itens por página", in = ParameterIn.QUERY)
    })
    public ResponseEntity<Page<DetalhesPacienteDto>> getNome(@RequestParam("nome") String nome, Pageable pageable){
        var page = pacienteRepository.findByNome(nome, pageable).map(DetalhesPacienteDto::new);
        return ResponseEntity.ok(page);
    }
}
