package br.com.mapped.CareMI.controller;
import br.com.mapped.CareMI.dto.BairroDto.AtualizacaoBairroDto;
import br.com.mapped.CareMI.dto.BairroDto.CadastroBairroDto;
import br.com.mapped.CareMI.dto.BairroDto.DetalhesBairroDto;
import br.com.mapped.CareMI.model.Bairro;
import br.com.mapped.CareMI.repository.BairroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("bairros")
public class BairroController {
    @Autowired
    private BairroRepository bairroRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesBairroDto>> get(Pageable pageable){
        var bairro = bairroRepository.findAll(pageable)
                .stream().map(DetalhesBairroDto::new).toList();
        return ResponseEntity.ok(bairro);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesBairroDto> get(@PathVariable("id")Long id){
        var bairro = bairroRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesBairroDto(bairro));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesBairroDto> post(@RequestBody @Valid CadastroBairroDto bairroDto,
                                                       UriComponentsBuilder uriBuilder){
        var bairro = new Bairro(bairroDto);
        bairroRepository.save(bairro);
        var uri = uriBuilder.path("bairros/{id}").buildAndExpand(bairro.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesBairroDto(bairro));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        bairroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesBairroDto> put(@PathVariable("id")Long id,
                                                      @RequestBody @Valid AtualizacaoBairroDto dto){
        var bairro = bairroRepository.getReferenceById(id);
        bairro.atualizarInformacoesBairro(dto);
        return ResponseEntity.ok(new DetalhesBairroDto(bairro));
    }
}
