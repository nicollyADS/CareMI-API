package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.EnderecoPacienteDto.AtualizacaoEnderecoPacienteDto;
import br.com.mapped.CareMI.dto.EnderecoPacienteDto.CadastroEnderecoPacienteDto;
import br.com.mapped.CareMI.dto.EnderecoPacienteDto.DetalhesEnderecoPacienteDto;
import br.com.mapped.CareMI.model.EnderecoPaciente;
import br.com.mapped.CareMI.repository.EnderecoPacienteRepository;
import br.com.mapped.CareMI.repository.LogradouroRepository;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("enderecos-pacientes")
@Tag(name = "Endereços Pacientes", description = "Operações para gerenciar os endereços dos pacientes")
public class EnderecoPacienteController {

    @Autowired
    private EnderecoPacienteRepository enderecoPacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LogradouroRepository logradouroRepository;

    //GET
    @GetMapping
    @Operation(summary = "Lista todos os endereços de pacientes", description = "Retorna uma lista paginada de todos os endereços de pacientes")
    public ResponseEntity<List<DetalhesEnderecoPacienteDto>> get(Pageable pageable){
        var enderecoPac = enderecoPacienteRepository.findAll(pageable)
                .stream().map(DetalhesEnderecoPacienteDto::new).toList();
        return ResponseEntity.ok(enderecoPac);
    }

    //GET BY ID
    @GetMapping("{id}")
    @Operation(summary = "Obtém detalhes de um endereço de paciente pelo ID", description = "Retorna os detalhes de um endereço de paciente específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalhes do endereço de paciente encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesEnderecoPacienteDto.class))),
            @ApiResponse(responseCode = "404", description = "Endereço de paciente não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do endereço de paciente", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<DetalhesEnderecoPacienteDto> get(@PathVariable("id") Long id){
        var enderecoPac = enderecoPacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesEnderecoPacienteDto(enderecoPac));
    }

    //POST
    @PostMapping
    @Transactional
    @Operation(summary = "Cadastra um novo endereço de paciente", description = "Cadastra um novo endereço de paciente e retorna os detalhes do endereço cadastrado")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Endereço de paciente criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesEnderecoPacienteDto.class)))
    })
    public ResponseEntity<DetalhesEnderecoPacienteDto> cadastrar(@RequestBody @Valid CadastroEnderecoPacienteDto dto, UriComponentsBuilder builder) {
        var enderecoPac = new EnderecoPaciente(dto);
        var logradouro = logradouroRepository.getReferenceById(dto.idLogradouro());
        var usuario = usuarioRepository.getReferenceById(dto.idUsuario());

        enderecoPac.setLogradouro(logradouro);
        enderecoPac.setUsuario(usuario);

        enderecoPac = enderecoPacienteRepository.save(enderecoPac);
        var uri = builder.path("enderecos-pacientes/{id}").buildAndExpand(enderecoPac.getIdEnderecoPaciente()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEnderecoPacienteDto(enderecoPac));
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    @Operation(summary = "Atualiza um endereço de paciente", description = "Atualiza as informações de um endereço de paciente específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Endereço de paciente atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesEnderecoPacienteDto.class))),
            @ApiResponse(responseCode = "404", description = "Endereço de paciente não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do endereço de paciente", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<DetalhesEnderecoPacienteDto> put(@PathVariable("id") Long id,
                                                           @RequestBody @Valid AtualizacaoEnderecoPacienteDto dto){
        var enderecoPac = enderecoPacienteRepository.getReferenceById(id);
        enderecoPac.atualizarInformacoesEnderecoPaciente(dto);
        return ResponseEntity.ok(new DetalhesEnderecoPacienteDto(enderecoPac));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    @Operation(summary = "Remove um endereço de paciente pelo ID", description = "Remove um endereço de paciente específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Endereço de paciente removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço de paciente não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do endereço de paciente", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        enderecoPacienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
