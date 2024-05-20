package br.com.mapped.CareMI.controller;
import br.com.mapped.CareMI.dto.ExameDto.AtualizacaoExameDto;
import br.com.mapped.CareMI.dto.ExameDto.CadastroExameDto;
import br.com.mapped.CareMI.dto.ExameDto.DetalhesExameDto;
import br.com.mapped.CareMI.model.Exame;
import br.com.mapped.CareMI.repository.ExameRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("exames")
public class ExameController {
    @Autowired
    private ExameRepository exameRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesExameDto>> get(Pageable pageable){
        var exame = exameRepository.findAll(pageable)
                .stream().map(DetalhesExameDto::new).toList();
        return ResponseEntity.ok(exame);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesExameDto> get(@PathVariable("id")Long id){
        var exame = exameRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesExameDto(exame));
    }


    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        exameRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesExameDto> put(@PathVariable("id")Long id,
                                                 @RequestBody @Valid AtualizacaoExameDto dto){
        var exame = exameRepository.getReferenceById(id);
        exame.atualizarInformacoesExame(dto);
        return ResponseEntity.ok(new DetalhesExameDto(exame));
    }

    //BUSCAR EXAMES POR DATA
    @GetMapping("por-data")
    public ResponseEntity<Page<DetalhesExameDto>> getExamesPorData(@RequestParam("data") LocalDate data, Pageable pageable) {
        var page = exameRepository.findByData(data, pageable).map(DetalhesExameDto::new);
        return ResponseEntity.ok(page);
    }
}
