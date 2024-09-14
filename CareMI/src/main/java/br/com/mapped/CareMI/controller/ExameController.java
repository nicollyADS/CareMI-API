package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.ExameDto.AtualizacaoExameDto;
import br.com.mapped.CareMI.dto.ExameDto.CadastroExameDto;
import br.com.mapped.CareMI.dto.ExameDto.DetalhesExameDto;
import br.com.mapped.CareMI.model.Exame;
import br.com.mapped.CareMI.repository.ExameRepository;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("exames")
@Tag(name = "Exames", description = "Operações para gerenciar exames")
public class ExameController {
    @Autowired
    private ExameRepository exameRepository;

    //GET
    @GetMapping
    @Operation(summary = "Lista todos os exames", description = "Retorna uma lista paginada de todos os exames")
    public ResponseEntity<List<DetalhesExameDto>> get(Pageable pageable){
        var exame = exameRepository.findAll(pageable)
                .stream().map(DetalhesExameDto::new).toList();
        return ResponseEntity.ok(exame);
    }

    //GET BY ID
    @GetMapping("{id}")
    @Operation(summary = "Obtém detalhes de um exame pelo ID", description = "Retorna os detalhes de um exame específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalhes do exame encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesExameDto.class))),
            @ApiResponse(responseCode = "404", description = "Exame não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do exame", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<DetalhesExameDto> get(@PathVariable("id") Long id){
        var exame = exameRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesExameDto(exame));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    @Operation(summary = "Remove um exame pelo ID", description = "Remove um exame específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Exame removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Exame não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do exame", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        exameRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    @Operation(summary = "Atualiza as informações de um exame", description = "Atualiza as informações de um exame específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exame atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesExameDto.class))),
            @ApiResponse(responseCode = "404", description = "Exame não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do exame", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<DetalhesExameDto> put(@PathVariable("id") Long id,
                                                @RequestBody @Valid AtualizacaoExameDto dto){
        var exame = exameRepository.getReferenceById(id);
        exame.atualizarInformacoesExame(dto);
        return ResponseEntity.ok(new DetalhesExameDto(exame));
    }

    //BUSCAR EXAMES POR DATA
    @GetMapping("por-data")
    @Operation(summary = "Busca exames por data", description = "Busca exames realizados em uma data específica")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exames encontrados para a data especificada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesExameDto.class))),
            @ApiResponse(responseCode = "400", description = "Data inválida fornecida")
    })
    @Parameters({
            @Parameter(name = "data", description = "Data dos exames a serem buscados", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string", format = "date"))
    })
    public ResponseEntity<Page<DetalhesExameDto>> getExamesPorData(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data, Pageable pageable) {
        var page = exameRepository.findByData(data, pageable).map(DetalhesExameDto::new);
        return ResponseEntity.ok(page);
    }
}
