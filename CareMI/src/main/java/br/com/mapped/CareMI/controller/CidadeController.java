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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("cidades")
public class CidadeController {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private BairroRepository bairroRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesCidadeDto>> get(Pageable pageable){
        var cidade = cidadeRepository.findAll(pageable)
                .stream().map(DetalhesCidadeDto::new).toList();
        return ResponseEntity.ok(cidade);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesCidadeDto> get(@PathVariable("id")Long id){
        var cidade = cidadeRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesCidadeDto(cidade));
    }


    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        cidadeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesCidadeDto> put(@PathVariable("id")Long id,
                                                      @RequestBody @Valid AtualizacaoCidadeDto dto){
        var cidade = cidadeRepository.getReferenceById(id);
        cidade.atualizarInformacoesCidade(dto);
        return ResponseEntity.ok(new DetalhesCidadeDto(cidade));
    }

    //relacionamentos

    //POST BAIRRO
    @PostMapping("{id}/bairros")
    @Transactional
    public ResponseEntity<DetalhesBairroDto> post(@PathVariable("id") Long id,
                                                  @RequestBody @Valid CadastroBairroDto dto,
                                                  UriComponentsBuilder uriBuilder){
        var cidade = cidadeRepository.getReferenceById(id);
        var bairro = new Bairro(dto, cidade);
        bairroRepository.save(bairro);
        var uri = uriBuilder.path("bairro/{id}").buildAndExpand(bairro.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesBairroDto(bairro));
    }

}
