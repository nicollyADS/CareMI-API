package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.CidadeDto.CadastroCidadeDto;
import br.com.mapped.CareMI.dto.CidadeDto.DetalhesCidadeDto;
import br.com.mapped.CareMI.dto.EstadoDto.AtualizacaoEstadoDto;
import br.com.mapped.CareMI.dto.EstadoDto.CadastroEstadoDto;
import br.com.mapped.CareMI.dto.EstadoDto.DetalhesEstadoDto;
import br.com.mapped.CareMI.model.Cidade;
import br.com.mapped.CareMI.model.Estado;
import br.com.mapped.CareMI.repository.CidadeRepository;
import br.com.mapped.CareMI.repository.EstadoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
@RequestMapping("estados")
@Tag(name = "Estados", description = "Operações para gerenciar estados")
public class EstadoController {
    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    //GET
    @GetMapping
    @Operation(summary = "Lista todos os estados", description = "Obtém uma lista paginada de todos os estados")
    public ResponseEntity<List<DetalhesEstadoDto>> get(Pageable pageable){
        var estado = estadoRepository.findAll(pageable)
                .stream().map(DetalhesEstadoDto::new).toList();
        return ResponseEntity.ok(estado);
    }

    //GET BY ID
    @GetMapping("{id}")
    @Operation(summary = "Obtém um estado por ID", description = "Obtém detalhes de um estado específico usando o ID")
    @Parameters({
            @Parameter(name = "id", description = "ID do estado", required = true)
    })
    public ResponseEntity<DetalhesEstadoDto> get(@PathVariable("id")Long id){
        var estado = estadoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesEstadoDto(estado));
    }

    //POST
    @PostMapping
    @Transactional
    @Operation(summary = "Cadastra um novo estado", description = "Cria um novo estado com base nas informações fornecidas")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Estado cadastrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesEstadoDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<DetalhesEstadoDto> post(@RequestBody @Valid CadastroEstadoDto estadoDto,
                                                  UriComponentsBuilder uriBuilder){
        var estado = new Estado(estadoDto);
        estadoRepository.save(estado);
        var uri = uriBuilder.path("estados/{id}").buildAndExpand(estado.getIdEstado()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEstadoDto(estado));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    @Operation(summary = "Deleta um estado", description = "Remove um estado existente com base no ID fornecido")
    @Parameters({
            @Parameter(name = "id", description = "ID do estado a ser removido", required = true)
    })
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        estadoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    @Operation(summary = "Atualiza um estado", description = "Atualiza as informações de um estado existente com base no ID fornecido")
    @Parameters({
            @Parameter(name = "id", description = "ID do estado a ser atualizado", required = true)
    })
    public ResponseEntity<DetalhesEstadoDto> put(@PathVariable("id")Long id,
                                                 @RequestBody @Valid AtualizacaoEstadoDto dto){
        var estado = estadoRepository.getReferenceById(id);
        estado.atualizarInformacoesEstado(dto);
        return ResponseEntity.ok(new DetalhesEstadoDto(estado));
    }

    //relacionamentos

    //POST CIDADE
    @PostMapping("{id}/cidades")
    @Transactional
    @Operation(summary = "Adiciona uma cidade ao estado", description = "Adiciona uma nova cidade ao estado especificado pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cidade cadastrada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesCidadeDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do estado ao qual a cidade será adicionada", required = true)
    })
    public ResponseEntity<DetalhesCidadeDto> post(@PathVariable("id") Long id,
                                                  @RequestBody @Valid CadastroCidadeDto dto,
                                                  UriComponentsBuilder uriBuilder){
        var estado = estadoRepository.getReferenceById(id);
        var cidade = new Cidade(dto, estado);
        cidadeRepository.save(cidade);
        var uri = uriBuilder.path("cidades/{id}").buildAndExpand(cidade.getIdCidade()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesCidadeDto(cidade));
    }
}
