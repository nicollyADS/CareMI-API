package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.CarteirinhaDto.AtualizacaoCarteirinhaDto;
import br.com.mapped.CareMI.dto.CarteirinhaDto.CadastroCarteirinhaDto;
import br.com.mapped.CareMI.dto.CarteirinhaDto.DetalhesCarteirinhaDto;
import br.com.mapped.CareMI.model.Carteirinha;
import br.com.mapped.CareMI.repository.CarteirinhaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("carteirinhas")
public class CarteirinhaController {
    @Autowired
    private CarteirinhaRepository carteirinhaRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesCarteirinhaDto>> get(Pageable pageable){
        var carteirinha = carteirinhaRepository.findAll(pageable)
                .stream().map(DetalhesCarteirinhaDto::new).toList();
        return ResponseEntity.ok(carteirinha);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesCarteirinhaDto> get(@PathVariable("id")Long id){
        var carteirinha = carteirinhaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesCarteirinhaDto(carteirinha));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesCarteirinhaDto> post(@RequestBody @Valid CadastroCarteirinhaDto carteirinhaDto,
                                                       UriComponentsBuilder uriBuilder){
        var carteirinha = new Carteirinha(carteirinhaDto);
        carteirinhaRepository.save(carteirinha);
        var uri = uriBuilder.path("carteirinhas/{id}").buildAndExpand(carteirinha.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesCarteirinhaDto(carteirinha));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        carteirinhaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesCarteirinhaDto> put(@PathVariable("id")Long id,
                                                      @RequestBody @Valid AtualizacaoCarteirinhaDto dto){
        var carteirinha = carteirinhaRepository.getReferenceById(id);
        carteirinha.atualizarInformacoesCarteirinha(dto);
        return ResponseEntity.ok(new DetalhesCarteirinhaDto(carteirinha));
    }
}
