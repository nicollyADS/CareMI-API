package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.EnderecoHospitalDto.CadastroEnderecoHospitalDto;
import br.com.mapped.CareMI.dto.EnderecoHospitalDto.DetalhesEnderecoHospitalDto;
import br.com.mapped.CareMI.dto.LogradouroDto.AtualizacaoLogradouroDto;
import br.com.mapped.CareMI.dto.LogradouroDto.CadastroLogradouroDto;
import br.com.mapped.CareMI.dto.LogradouroDto.DetalhesLogradouroDto;
import br.com.mapped.CareMI.model.EnderecoHospital;
import br.com.mapped.CareMI.model.Logradouro;
import br.com.mapped.CareMI.repository.EnderecoHospitalRepository;
import br.com.mapped.CareMI.repository.LogradouroRepository;
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
@RequestMapping("logradouros")
@Tag(name = "Logradouros", description = "Operações para gerenciar logradouros")
public class LogradouroController {
    @Autowired
    private LogradouroRepository logradouroRepository;

    @Autowired
    private EnderecoHospitalRepository enderecoHospitalRepository;

    //GET
    @GetMapping
    @Operation(summary = "Lista todos os logradouros", description = "Retorna uma lista paginada de todos os logradouros")
    public ResponseEntity<List<DetalhesLogradouroDto>> get(Pageable pageable){
        var logradouro = logradouroRepository.findAll(pageable)
                .stream().map(DetalhesLogradouroDto::new).toList();
        return ResponseEntity.ok(logradouro);
    }

    //GET BY ID
    @GetMapping("{id}")
    @Operation(summary = "Obtém detalhes de um logradouro pelo ID", description = "Retorna os detalhes de um logradouro específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalhes do logradouro encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesLogradouroDto.class))),
            @ApiResponse(responseCode = "404", description = "Logradouro não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do logradouro", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<DetalhesLogradouroDto> get(@PathVariable("id") Long id){
        var logradouro = logradouroRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesLogradouroDto(logradouro));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    @Operation(summary = "Remove um logradouro pelo ID", description = "Remove um logradouro específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Logradouro removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Logradouro não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do logradouro", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        logradouroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    @Operation(summary = "Atualiza as informações de um logradouro", description = "Atualiza as informações de um logradouro específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Logradouro atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesLogradouroDto.class))),
            @ApiResponse(responseCode = "404", description = "Logradouro não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do logradouro", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<DetalhesLogradouroDto> put(@PathVariable("id") Long id,
                                                     @RequestBody @Valid AtualizacaoLogradouroDto dto){
        var logradouro = logradouroRepository.getReferenceById(id);
        logradouro.atualizarInformacoesLogradouro(dto);
        return ResponseEntity.ok(new DetalhesLogradouroDto(logradouro));
    }

    //POST ENDERECO HOSPITAL
    @PostMapping("{id}/enderecos-hospitais")
    @Transactional
    @Operation(summary = "Cadastra um endereço de hospital associado ao logradouro", description = "Registra um novo endereço de hospital associado ao logradouro")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Endereço de hospital cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesEnderecoHospitalDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Logradouro não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do logradouro", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<DetalhesEnderecoHospitalDto> post(@PathVariable("id") Long id,
                                                            @RequestBody @Valid CadastroEnderecoHospitalDto dto,
                                                            UriComponentsBuilder uriBuilder){
        var logradouro = logradouroRepository.getReferenceById(id);
        var endHospital = new EnderecoHospital(dto, logradouro);
        enderecoHospitalRepository.save(endHospital);
        var uri = uriBuilder.path("enderecos-hospitais/{id}").buildAndExpand(endHospital.getIdEnderecoHospital()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEnderecoHospitalDto(endHospital));
    }
}
