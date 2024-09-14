package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.BairroDto.AtualizacaoBairroDto;
import br.com.mapped.CareMI.dto.BairroDto.CadastroBairroDto;
import br.com.mapped.CareMI.dto.BairroDto.DetalhesBairroDto;
import br.com.mapped.CareMI.dto.LogradouroDto.CadastroLogradouroDto;
import br.com.mapped.CareMI.dto.LogradouroDto.DetalhesLogradouroDto;
import br.com.mapped.CareMI.model.Bairro;
import br.com.mapped.CareMI.model.Logradouro;
import br.com.mapped.CareMI.repository.BairroRepository;
import br.com.mapped.CareMI.repository.LogradouroRepository;
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
@RequestMapping("bairros")
@Tag(name = "Bairros", description = "Operações para gerenciar bairros")
public class BairroController {

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private LogradouroRepository logradouroRepository;

    //GET
    @GetMapping
    @Operation(summary = "Lista todos os bairros", description = "Retorna uma lista paginada de todos os bairros")
    public ResponseEntity<List<DetalhesBairroDto>> get(Pageable pageable){
        var bairro = bairroRepository.findAll(pageable)
                .stream().map(DetalhesBairroDto::new).toList();
        return ResponseEntity.ok(bairro);
    }

    //GET BY ID
    @GetMapping("{id}")
    @Operation(summary = "Obtém detalhes de um bairro pelo ID", description = "Retorna os detalhes de um bairro específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalhes do bairro encontrados"),
            @ApiResponse(responseCode = "404", description = "Bairro não encontrado")
    })
    public ResponseEntity<DetalhesBairroDto> get(@PathVariable("id") Long id){
        var bairro = bairroRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesBairroDto(bairro));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    @Operation(summary = "Remove um bairro pelo ID", description = "Remove um bairro específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Bairro removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Bairro não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        bairroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    @Operation(summary = "Atualiza um bairro", description = "Atualiza as informações de um bairro específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bairro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Bairro não encontrado")
    })
    public ResponseEntity<DetalhesBairroDto> put(@PathVariable("id") Long id,
                                                 @RequestBody @Valid AtualizacaoBairroDto dto){
        var bairro = bairroRepository.getReferenceById(id);
        bairro.atualizarInformacoesBairro(dto);
        return ResponseEntity.ok(new DetalhesBairroDto(bairro));
    }

    // POST LOGRADOUROS
    @PostMapping("{id}/logradouros")
    @Transactional
    @Operation(summary = "Adiciona um logradouro a um bairro", description = "Cria um novo logradouro associado a um bairro específico")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Logradouro criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Bairro não encontrado")
    })
    public ResponseEntity<DetalhesLogradouroDto> post(@PathVariable("id") Long id,
                                                      @RequestBody @Valid CadastroLogradouroDto dto,
                                                      UriComponentsBuilder uriBuilder){
        var bairro = bairroRepository.getReferenceById(id);
        var logradouro = new Logradouro(dto, bairro);
        logradouroRepository.save(logradouro);
        var uri = uriBuilder.path("logradouros/{id}").buildAndExpand(logradouro.getIdLogradouro()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesLogradouroDto(logradouro));
    }
}
