package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.PacientePlanoSaudeDto.CadastroPacientePlanoSaudeDto;
import br.com.mapped.CareMI.dto.PacientePlanoSaudeDto.DetalhesPacientePlanoSaudeDto;
import br.com.mapped.CareMI.dto.PlanoSaudeDto.AtualizacaoPlanoSaudeDto;
import br.com.mapped.CareMI.dto.PlanoSaudeDto.CadastroPlanoSaudeDto;
import br.com.mapped.CareMI.dto.PlanoSaudeDto.DetalhesPlanoSaudeDto;
import br.com.mapped.CareMI.model.PacientePlanoSaude;
import br.com.mapped.CareMI.model.PlanoSaude;
import br.com.mapped.CareMI.repository.PacientePlanoSaudeRepository;
import br.com.mapped.CareMI.repository.PlanoSaudeRepository;
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
@RequestMapping("planos-saude")
@Tag(name = "Planos de Saúde", description = "Operações para gerenciar os planos de saúde")
public class PlanoSaudeController {
    @Autowired
    private PlanoSaudeRepository planoSaudeRepository;

    //GET
    @GetMapping
    @Operation(summary = "Lista todos os planos de saúde", description = "Retorna uma lista paginada de todos os planos de saúde")
    public ResponseEntity<List<DetalhesPlanoSaudeDto>> get(Pageable pageable){
        var planoSaude = planoSaudeRepository.findAll(pageable)
                .stream().map(DetalhesPlanoSaudeDto::new).toList();
        return ResponseEntity.ok(planoSaude);
    }

    //GET BY ID
    @GetMapping("{id}")
    @Operation(summary = "Obtém detalhes do plano de saúde pelo ID", description = "Retorna os detalhes de um plano de saúde específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalhes do plano de saúde encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesPlanoSaudeDto.class))),
            @ApiResponse(responseCode = "404", description = "Plano de saúde não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do plano de saúde", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<DetalhesPlanoSaudeDto> get(@PathVariable("id") Long id){
        var planoSaude = planoSaudeRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesPlanoSaudeDto(planoSaude));
    }

    //POST
    @PostMapping
    @Transactional
    @Operation(summary = "Cadastra um novo plano de saúde", description = "Registra um novo plano de saúde no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Plano de saúde cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesPlanoSaudeDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<DetalhesPlanoSaudeDto> post(@RequestBody @Valid CadastroPlanoSaudeDto planoSaudeDto,
                                                      UriComponentsBuilder uriBuilder){
        var planoSaude = new PlanoSaude(planoSaudeDto);
        planoSaudeRepository.save(planoSaude);
        var uri = uriBuilder.path("planos-saude/{id}").buildAndExpand(planoSaude.getIdPlanoSaude()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPlanoSaudeDto(planoSaude));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    @Operation(summary = "Remove um plano de saúde pelo ID", description = "Remove um plano de saúde específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Plano de saúde removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Plano de saúde não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do plano de saúde", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        planoSaudeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    @Operation(summary = "Atualiza as informações do plano de saúde", description = "Atualiza as informações de um plano de saúde específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Plano de saúde atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesPlanoSaudeDto.class))),
            @ApiResponse(responseCode = "404", description = "Plano de saúde não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do plano de saúde", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<DetalhesPlanoSaudeDto> put(@PathVariable("id") Long id,
                                                     @RequestBody @Valid AtualizacaoPlanoSaudeDto dto){
        var planoSaude = planoSaudeRepository.getReferenceById(id);
        planoSaude.atualizarInformacoesPlanoSaude(dto);
        return ResponseEntity.ok(new DetalhesPlanoSaudeDto(planoSaude));
    }
}
