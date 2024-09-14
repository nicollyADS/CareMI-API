package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.BairroDto.CadastroBairroDto;
import br.com.mapped.CareMI.dto.BairroDto.DetalhesBairroDto;
import br.com.mapped.CareMI.dto.CidadeDto.AtualizacaoCidadeDto;
import br.com.mapped.CareMI.dto.CidadeDto.CadastroCidadeDto;
import br.com.mapped.CareMI.dto.CidadeDto.DetalhesCidadeDto;
import br.com.mapped.CareMI.model.Bairro;
import br.com.mapped.CareMI.model.Cidade;
import br.com.mapped.CareMI.repository.BairroRepository;
import br.com.mapped.CareMI.repository.CidadeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("cidades")
@Tag(name = "Cidades", description = "Operações para gerenciar cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private BairroRepository bairroRepository;

    //GET
    @GetMapping
    @Operation(summary = "Lista todas as cidades", description = "Retorna uma lista paginada de todas as cidades")
    public ResponseEntity<List<DetalhesCidadeDto>> get(Pageable pageable){
        var cidade = cidadeRepository.findAll(pageable)
                .stream().map(DetalhesCidadeDto::new).toList();
        return ResponseEntity.ok(cidade);
    }

    //GET BY ID
    @GetMapping("{id}")
    @Operation(summary = "Obtém detalhes de uma cidade pelo ID", description = "Retorna os detalhes de uma cidade específica pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalhes da cidade encontrados"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada")
    })
    public ResponseEntity<DetalhesCidadeDto> get(@PathVariable("id") Long id){
        var cidade = cidadeRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesCidadeDto(cidade));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    @Operation(summary = "Remove uma cidade pelo ID", description = "Remove uma cidade específica pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cidade removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada")
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        cidadeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    @Operation(summary = "Atualiza uma cidade", description = "Atualiza as informações de uma cidade específica")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cidade atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada")
    })
    public ResponseEntity<DetalhesCidadeDto> put(@PathVariable("id") Long id,
                                                 @RequestBody @Valid AtualizacaoCidadeDto dto){
        var cidade = cidadeRepository.getReferenceById(id);
        cidade.atualizarInformacoesCidade(dto);
        return ResponseEntity.ok(new DetalhesCidadeDto(cidade));
    }

    //POST BAIRRO
    @PostMapping("{id}/bairros")
    @Transactional
    @Operation(summary = "Adiciona um bairro a uma cidade", description = "Cria um novo bairro associado a uma cidade específica")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Bairro criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada")
    })
    public ResponseEntity<DetalhesBairroDto> post(@PathVariable("id") Long id,
                                                  @RequestBody @Valid CadastroBairroDto dto,
                                                  UriComponentsBuilder uriBuilder){
        var cidade = cidadeRepository.getReferenceById(id);
        var bairro = new Bairro(dto, cidade);
        bairroRepository.save(bairro);
        var uri = uriBuilder.path("bairros/{id}").buildAndExpand(bairro.getIdBairro()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesBairroDto(bairro));
    }
}
