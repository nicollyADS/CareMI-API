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

    @Autowired
    private LogradouroRepository logradouroRepository;

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

    //relacionamentos
    //POST LOGRADOUROS
    @PostMapping("{id}/logradouros")
    @Transactional
    public ResponseEntity<DetalhesLogradouroDto> post(@PathVariable("id") Long id,
                                                      @RequestBody @Valid CadastroLogradouroDto dto,
                                                      UriComponentsBuilder uriBuilder){
        var bairro = bairroRepository.getReferenceById(id);
        var logradouro = new Logradouro(dto, bairro);
        logradouroRepository.save(logradouro);
        var uri = uriBuilder.path("logradouros/{id}").buildAndExpand(logradouro.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesLogradouroDto(logradouro));
    }

}
