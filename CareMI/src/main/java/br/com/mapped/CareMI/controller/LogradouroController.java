package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.LogradouroDto.AtualizacaoLogradouroDto;
import br.com.mapped.CareMI.dto.LogradouroDto.CadastroLogradouroDto;
import br.com.mapped.CareMI.dto.LogradouroDto.DetalhesLogradouroDto;
import br.com.mapped.CareMI.model.Logradouro;
import br.com.mapped.CareMI.repository.LogradouroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("logradouros")
public class LogradouroController {
    @Autowired
    private LogradouroRepository logradouroRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesLogradouroDto>> get(Pageable pageable){
        var logradouro = logradouroRepository.findAll(pageable)
                .stream().map(DetalhesLogradouroDto::new).toList();
        return ResponseEntity.ok(logradouro);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesLogradouroDto> get(@PathVariable("id")Long id){
        var logradouro = logradouroRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesLogradouroDto(logradouro));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesLogradouroDto> post(@RequestBody @Valid CadastroLogradouroDto logradouroDto,
                                                  UriComponentsBuilder uriBuilder){
        var logradouro = new Logradouro(logradouroDto);
        logradouroRepository.save(logradouro);
        var uri = uriBuilder.path("logradouros/{id}").buildAndExpand(logradouro.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesLogradouroDto(logradouro));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        logradouroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesLogradouroDto> put(@PathVariable("id")Long id,
                                                 @RequestBody @Valid AtualizacaoLogradouroDto dto){
        var logradouro = logradouroRepository.getReferenceById(id);
        logradouro.atualizarInformacoesLogradouro(dto);
        return ResponseEntity.ok(new DetalhesLogradouroDto(logradouro));
    }
}
