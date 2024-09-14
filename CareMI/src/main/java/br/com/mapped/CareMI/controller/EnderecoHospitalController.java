package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.EnderecoHospitalDto.AtualizacaoEnderecoHospitalDto;
import br.com.mapped.CareMI.dto.EnderecoHospitalDto.CadastroEnderecoHospitalDto;
import br.com.mapped.CareMI.dto.EnderecoHospitalDto.DetalhesEnderecoHospitalDto;
import br.com.mapped.CareMI.model.EnderecoHospital;
import br.com.mapped.CareMI.repository.EnderecoHospitalRepository;
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
@RequestMapping("enderecos-hospitais")
@Tag(name = "Endereços Hospitais", description = "Operações para gerenciar os endereços dos hospitais")
public class EnderecoHospitalController {

    @Autowired
    private EnderecoHospitalRepository enderecoHospitalRepository;

    //GET
    @GetMapping
    @Operation(summary = "Lista todos os endereços de hospitais", description = "Retorna uma lista paginada de todos os endereços de hospitais")
    public ResponseEntity<List<DetalhesEnderecoHospitalDto>> get(Pageable pageable){
        var enderecoHospital = enderecoHospitalRepository.findAll(pageable)
                .stream().map(DetalhesEnderecoHospitalDto::new).toList();
        return ResponseEntity.ok(enderecoHospital);
    }

    //GET BY ID
    @GetMapping("{id}")
    @Operation(summary = "Obtém detalhes de um endereço de hospital pelo ID", description = "Retorna os detalhes de um endereço de hospital específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalhes do endereço de hospital encontrados"),
            @ApiResponse(responseCode = "404", description = "Endereço de hospital não encontrado")
    })
    public ResponseEntity<DetalhesEnderecoHospitalDto> get(@PathVariable("id") Long id){
        var enderecoHospital = enderecoHospitalRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesEnderecoHospitalDto(enderecoHospital));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    @Operation(summary = "Remove um endereço de hospital pelo ID", description = "Remove um endereço de hospital específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Endereço de hospital removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço de hospital não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        enderecoHospitalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    @Operation(summary = "Atualiza um endereço de hospital", description = "Atualiza as informações de um endereço de hospital específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Endereço de hospital atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço de hospital não encontrado")
    })
    public ResponseEntity<DetalhesEnderecoHospitalDto> put(@PathVariable("id") Long id,
                                                           @RequestBody @Valid AtualizacaoEnderecoHospitalDto dto){
        var enderecoHospital = enderecoHospitalRepository.getReferenceById(id);
        enderecoHospital.atualizarInformacoesEnderecoHospital(dto);
        return ResponseEntity.ok(new DetalhesEnderecoHospitalDto(enderecoHospital));
    }
}
