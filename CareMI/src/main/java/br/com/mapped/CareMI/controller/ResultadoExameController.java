package br.com.mapped.CareMI.controller;
import br.com.mapped.CareMI.dto.ResultadoExameDto.AtualizacaoResultadoExameDto;
import br.com.mapped.CareMI.dto.ResultadoExameDto.CadastroResultadoExameDto;
import br.com.mapped.CareMI.dto.ResultadoExameDto.DetalhesResultadoExameDto;
import br.com.mapped.CareMI.model.ResultadoExame;
import br.com.mapped.CareMI.repository.ResultadoExameRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("resultados")
public class ResultadoExameController {
    @Autowired
    private ResultadoExameRepository resultadoExameRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesResultadoExameDto>> get(Pageable pageable){
        var resultadoExame = resultadoExameRepository.findAll(pageable)
                .stream().map(DetalhesResultadoExameDto::new).toList();
        return ResponseEntity.ok(resultadoExame);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesResultadoExameDto> get(@PathVariable("id")Long id){
        var resultadoExame = resultadoExameRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesResultadoExameDto(resultadoExame));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesResultadoExameDto> post(@RequestBody @Valid CadastroResultadoExameDto resultadoExameDto,
                                                  UriComponentsBuilder uriBuilder){
        var resultadoExame = new ResultadoExame(resultadoExameDto);
        resultadoExameRepository.save(resultadoExame);
        var uri = uriBuilder.path("resultados/{id}").buildAndExpand(resultadoExame.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesResultadoExameDto(resultadoExame));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        resultadoExameRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesResultadoExameDto> put(@PathVariable("id")Long id,
                                                 @RequestBody @Valid AtualizacaoResultadoExameDto dto){
        var resultadoExame = resultadoExameRepository.getReferenceById(id);
        resultadoExame.atualizarInformacoesResultadoExame(dto);
        return ResponseEntity.ok(new DetalhesResultadoExameDto(resultadoExame));
    }
}
