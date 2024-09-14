package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.MedicoDto.AtualizacaoMedicoDto;
import br.com.mapped.CareMI.dto.MedicoDto.CadastroMedicoDto;
import br.com.mapped.CareMI.dto.MedicoDto.DetalhesMedicoDto;
import br.com.mapped.CareMI.model.Medico;
import br.com.mapped.CareMI.repository.MedicoRepository;
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
@RequestMapping("medicos")
@Tag(name = "Médicos", description = "Operações para gerenciar médicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    //GET
    @GetMapping
    @Operation(summary = "Lista todos os médicos", description = "Retorna uma lista paginada de todos os médicos")
    public ResponseEntity<List<DetalhesMedicoDto>> get(Pageable pageable){
        var medico = medicoRepository.findAll(pageable)
                .stream().map(DetalhesMedicoDto::new).toList();
        return ResponseEntity.ok(medico);
    }

    //GET BY ID
    @GetMapping("{id}")
    @Operation(summary = "Obtém detalhes de um médico pelo ID", description = "Retorna os detalhes de um médico específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalhes do médico encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesMedicoDto.class))),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do médico", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<DetalhesMedicoDto> get(@PathVariable("id") Long id){
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesMedicoDto(medico));
    }

    //POST
    @PostMapping
    @Transactional
    @Operation(summary = "Cadastra um novo médico", description = "Registra um novo médico")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Médico cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesMedicoDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<DetalhesMedicoDto> post(@RequestBody @Valid CadastroMedicoDto medicoDto,
                                                  UriComponentsBuilder uriBuilder){
        var medico = new Medico(medicoDto);
        medicoRepository.save(medico);
        var uri = uriBuilder.path("medicos/{id}").buildAndExpand(medico.getIdMedico()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesMedicoDto(medico));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    @Operation(summary = "Remove um médico pelo ID", description = "Remove um médico específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Médico removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do médico", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        medicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    @Operation(summary = "Atualiza as informações de um médico", description = "Atualiza as informações de um médico específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Médico atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesMedicoDto.class))),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do médico", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<DetalhesMedicoDto> put(@PathVariable("id") Long id,
                                                 @RequestBody @Valid AtualizacaoMedicoDto dto){
        var medico = medicoRepository.getReferenceById(id);
        medico.atualizarInformacoesMedico(dto);
        return ResponseEntity.ok(new DetalhesMedicoDto(medico));
    }
}
